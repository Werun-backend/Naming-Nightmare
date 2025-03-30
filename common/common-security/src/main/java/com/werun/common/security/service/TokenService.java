package com.werun.common.security.service;


import com.alibaba.fastjson2.JSONObject;
import com.werun.common.core.constant.CacheConstants;
import com.werun.common.core.constant.SecurityConstants;
import com.werun.common.core.utils.IdUtils;
import com.werun.common.core.utils.JwtUtils;
import com.werun.common.core.utils.StringUtils;
import com.werun.common.security.entity.LoginUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TokenService {
    private final StringRedisTemplate stringRedisTemplate;


    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private final static long expireTime = CacheConstants.EXPIRATION;

    private final static String ACCESS_TOKEN = CacheConstants.LOGIN_TOKEN_KEY;

    private final static Long MILLIS_MINUTE_TEN = CacheConstants.REFRESH_TIME * MILLIS_MINUTE;

    public Map<String,Object> createToken(LoginUser loginUser) {
        String token = IdUtils.fastUUID();
        Long userId = loginUser.getUserid();
        String userName = loginUser.getUsername();
        loginUser.setToken(token);
        loginUser.setUserid(userId);
        loginUser.setUsername(userName);
        loginUser.setExpireTime(expireTime);
        refreshToken(loginUser);

        //存储jwt信息
        Map<String,Object> map = new HashMap<>();
        map.put(SecurityConstants.USER_KEY,token);
        map.put(SecurityConstants.DETAILS_USER_ID,userId);
        map.put(SecurityConstants.DETAILS_USERNAME,userName);
        map.put(SecurityConstants.DETAILS_EMAIL,loginUser.getEmail());

        //返回接口信息
        Map<String,Object> rspMap = new HashMap<>();
        rspMap.put("access_token", JwtUtils.createToken(map));
        rspMap.put("expires_in", expireTime);
        return rspMap;
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser(String token)
    {
        try
        {
            if (StringUtils.isNotEmpty(token))
            {
                String userkey = JwtUtils.getUserKey(token);
                String s = stringRedisTemplate.opsForValue().get(getTokenKey(userkey));
                JSONObject login = JSONObject.parseObject(s);
                if (login != null) {
                    return login.toJavaObject(LoginUser.class);
                }
            }
        }
        catch (Exception e)
        {
            log.error("获取用户信息异常'{}'", e.getMessage());
        }
        return null;
    }


    public void refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(loginUser.getToken());
        stringRedisTemplate.opsForValue().set(userKey, JSONObject.toJSONString(loginUser), expireTime, TimeUnit.MINUTES);
        }

    private String getTokenKey(String token)
    {
        return ACCESS_TOKEN + token;
    }

    public void deleteUserCache(String token) {
        if (StringUtils.isNotEmpty(token))
        {
            String userkey = JwtUtils.getUserKey(token);
            stringRedisTemplate.delete(getTokenKey(userkey));
        }
    }
}
