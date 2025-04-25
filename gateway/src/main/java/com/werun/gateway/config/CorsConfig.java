package com.werun.gateway.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 配置跨域
 */
@Configuration
public class CorsConfig {
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CorsFilter());
        registrationBean.addUrlPatterns("/*"); // 指定过滤器作用的路径
        return registrationBean;
    }

    public static class CorsFilter implements Filter {
        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;

            // 设置允许的来源
            res.setHeader("Access-Control-Allow-Origin", "*");
            // 设置允许的请求方法
            res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            // 设置允许的请求头
            res.setHeader("Access-Control-Allow-Headers", "*");
            // 设置是否允许发送Cookie
//            res.setHeader("Access-Control-Allow-Credentials", "true");

            // 如果是OPTIONS请求，则直接返回
            if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
                res.setStatus(HttpServletResponse.SC_OK);
                return;
            }

            chain.doFilter(request, response);
        }

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
        }

        @Override
        public void destroy() {
        }
    }
}
