package com.werun.common.security.aspect;

import com.werun.common.security.annotation.InnerAuth;
import com.werun.common.core.constant.SecurityConstants;
import com.werun.common.core.exception.InnerAuthException;
import com.werun.common.core.utils.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class InnerAuthAspect implements Ordered {
    @Around("@annotation(innerAuth)")
    public Object innerAround(ProceedingJoinPoint joinPoint, InnerAuth innerAuth) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        String source = requestAttributes.getRequest().getHeader(SecurityConstants.FROM_SOURCE);
        // 内部请求验证
        if (!StringUtils.equals(SecurityConstants.INNER, source))
        {
            throw new InnerAuthException("没有内部访问权限，不允许访问");
        }
        String userid = requestAttributes.getRequest().getHeader(SecurityConstants.DETAILS_USER_ID);
        String username = requestAttributes.getRequest().getHeader(SecurityConstants.DETAILS_USERNAME);
        // 用户信息验证
        if (innerAuth.isUser() && (StringUtils.isEmpty(userid) || StringUtils.isEmpty(username)))
        {
            throw new InnerAuthException("没有设置用户信息，不允许访问 ");
        }
        return joinPoint.proceed();
    }

    @Override
    public int getOrder() {//其实无所谓，因为我没写权限认证aop
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }
}
