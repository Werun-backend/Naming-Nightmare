package com.werun.gateway.filter;

import com.werun.common.core.constant.CacheConstants;
import com.werun.common.core.constant.SecurityConstants;
import com.werun.common.core.utils.JwtUtils;
import com.werun.common.core.utils.ServletUtils;
import com.werun.common.core.utils.StringUtils;
import com.werun.gateway.config.properties.IgnoreWhiteProperties;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 网关鉴权
 */
@Component
@Slf4j
public class AuthFilter implements GlobalFilter, Ordered {
    // 排除过滤的 uri 地址，nacos自行添加
    @Autowired
    private IgnoreWhiteProperties ignoreWhite;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder requestBuilder = request.mutate();
        String url = request.getURI().getPath();
        //跳过不需要验证的路径
        if(StringUtils.matches(url,ignoreWhite.getWhites())){
            return chain.filter(exchange);
        }
        String token = getToken(request);
        if (StringUtils.isEmpty(token))
        {
            return unauthorizedResponse(exchange, "令牌不能为空");
        }
        Claims claims = JwtUtils.parseToken(token);
        if (claims == null)
        {
            return unauthorizedResponse(exchange, "令牌已过期或验证不正确！");
        }
        String userkey = JwtUtils.getUserKey(claims);
        boolean isLogin = Boolean.TRUE.equals(stringRedisTemplate.hasKey(CacheConstants.LOGIN_TOKEN_KEY+userkey));
        if(!isLogin){
            return unauthorizedResponse(exchange, "登录状态已过期");
        }
        String userid = JwtUtils.getUserId(claims);
        String username = JwtUtils.getUserName(claims);
        String email = JwtUtils.getEmail(claims);
        if (StringUtils.isEmpty(userid))
        {
            return unauthorizedResponse(exchange, "令牌验证失败");
        }
        // 设置用户信息到请求
        addHeader(requestBuilder, SecurityConstants.USER_KEY, userkey);
        addHeader(requestBuilder, SecurityConstants.DETAILS_USER_ID, userid);
        addHeader(requestBuilder, SecurityConstants.DETAILS_USERNAME, username);
        addHeader(requestBuilder,SecurityConstants.DETAILS_EMAIL,email);

        // 内部请求来源参数清除
        removeHeader(requestBuilder, SecurityConstants.FROM_SOURCE);
        return chain.filter(exchange.mutate().request(requestBuilder.build()).build());
    }

    @Override
    public int getOrder() {
        return -200;
    }

    /**
     * 获取请求token
     */
    private String getToken(ServerHttpRequest request)
    {
        String token = request.getHeaders().getFirst(SecurityConstants.AUTHORIZATION_HEADER);
//        // 如果前端设置了令牌前缀，则裁剪掉前缀
//        if (StringUtils.isNotEmpty(token) && token.startsWith("Bearer"))
//        {
//            token = token.replaceFirst("Bearer", StringUtils.EMPTY);
//        }
        return token;
    }

    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange, String msg)
    {
        log.error("[鉴权异常处理]请求路径:{},错误信息:{}", exchange.getRequest().getPath(), msg);
        return ServletUtils.webFluxResponseWriter(exchange.getResponse(), msg, HttpStatus.UNAUTHORIZED.value());
    }

    private void addHeader(ServerHttpRequest.Builder mutate, String name, Object value)
    {
        if (value == null)
        {
            return;
        }
        String valueStr = value.toString();
        String valueEncode = ServletUtils.urlEncode(valueStr);
        mutate.header(name, valueEncode);
    }

    private void removeHeader(ServerHttpRequest.Builder mutate, String name)
    {
        mutate.headers(httpHeaders -> httpHeaders.remove(name)).build();
    }
}
