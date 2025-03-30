package com.werun.common.security.interceptor;

import com.werun.common.core.constant.SecurityConstants;
import com.werun.common.core.context.SecurityContextHolder;
import com.werun.common.core.utils.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

/**
 * 自定义请求头拦截器，将Header数据封装到线程变量中
 * 此拦截器会自动刷新用户信息有效期
 */
public class HeaderInterceptor implements AsyncHandlerInterceptor {
    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        SecurityContextHolder.setUserId(request.getHeader(SecurityConstants.DETAILS_USER_ID)==null?"":request.getHeader(SecurityConstants.DETAILS_USER_ID));
        SecurityContextHolder.setUserName(request.getHeader(SecurityConstants.DETAILS_USERNAME)==null?"":request.getHeader(SecurityConstants.DETAILS_USERNAME));
        SecurityContextHolder.setEmail(request.getHeader(SecurityConstants.DETAILS_EMAIL)==null?"":request.getHeader(SecurityConstants.DETAILS_EMAIL));
//        SecurityContextHolder.setUserKey(request.getHeader(SecurityConstants.USER_KEY)==null?"":request.getHeader(SecurityConstants.USER_KEY));

        String token = request.getHeader(SecurityConstants.AUTHORIZATION_HEADER);
        if(StringUtils.isNotEmpty(token)){

        }

        return AsyncHandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AsyncHandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
