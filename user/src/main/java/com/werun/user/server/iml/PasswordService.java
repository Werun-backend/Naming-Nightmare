package com.werun.user.server.iml;

import com.werun.common.core.constant.CacheConstants;
import com.werun.common.core.exception.ServiceException;
import com.werun.common.core.request.Result;
import com.werun.common.core.utils.StringUtils;
import com.werun.user.domain.User;
import com.werun.user.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 用户密码输错次数处理
 */
@Service
public class PasswordService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private int maxRetryCount = CacheConstants.PASSWORD_MAX_RETRY_COUNT;

    private Long lockTime = CacheConstants.PASSWORD_LOCK_TIME;

    /**
     * 登录账户密码错误次数缓存键名
     *
     * @param username 用户名
     * @return 缓存键key
     */
    private String getCacheKey(String username)
    {
        return CacheConstants.PWD_ERR_CNT_KEY + username;
    }

    public void validate(User user, String password){
        String email = user.getEmail();
        Integer retryCount ;
        if(!stringRedisTemplate.hasKey(getCacheKey(email))){
            retryCount=0;
        }
        else {
            retryCount = Integer.valueOf(stringRedisTemplate.opsForValue().get(getCacheKey(email)));
        }

        if(retryCount>maxRetryCount){
           throw new ServiceException(String.format("密码输入错误%s次，帐户锁定%s分钟", maxRetryCount, lockTime));
        }

        if(!matches(user,password)){
            retryCount = retryCount + 1;
            stringRedisTemplate.opsForValue().set(getCacheKey(email), retryCount.toString(), lockTime, TimeUnit.MINUTES);
            throw new ServiceException("用户不存在/密码错误");
        }
        else {
            clearLoginRecordCache(email);
        }


    }

    public boolean matches(User user, String rawPassword)
    {
        return SecurityUtils.matchesPassword(rawPassword, user.getPassword());
    }
    public void clearLoginRecordCache(String loginName)
    {
        if (stringRedisTemplate.hasKey(getCacheKey(loginName)))
        {
            stringRedisTemplate.delete(getCacheKey(loginName));
        }
    }
}
