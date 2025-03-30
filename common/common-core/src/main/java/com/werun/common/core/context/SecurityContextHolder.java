package com.werun.common.core.context;

import com.werun.common.core.constant.SecurityConstants;
import com.werun.common.core.utils.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 获取当前线程变量中的用户id，用户名称，Token等信息
 * 必须在网关用过请求头的方法传入，并经过HeaderInterceptor拦截器设置值
 */
public class SecurityContextHolder {
    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL = new ThreadLocal<>();

    public static void set(String key, Object value) {
        Map<String,Object> map = getLocalMap();
        map.put(key, value==null? "":value);
    }

    public static String get(String key)
    {
        Map<String, Object> map = getLocalMap();
        return StringUtils.toStr(map.getOrDefault(key, StringUtils.EMPTY),"");
    }

    public static <T> T get(String key,Class<T> clazz) {
        Map<String,Object> map = getLocalMap();
        return StringUtils.cast(map.get(key));
    }

    public static Map<String, Object> getLocalMap() {
        Map<String,Object> map = THREAD_LOCAL.get();
        if(map == null) {
            map = new ConcurrentHashMap<String,Object>();
            THREAD_LOCAL.set(map);
        }
        return map;
    }

    public static void setLocalMap(Map<String, Object> threadLocalMap)
    {
        THREAD_LOCAL.set(threadLocalMap);
    }

    public static Long getUserId()
    {   Map<String,Object> map = getLocalMap();
        return Long.valueOf((String) map.get(SecurityConstants.DETAILS_USER_ID));
    }

    public static void setUserId(String account)
    {
        set(SecurityConstants.DETAILS_USER_ID, account);
    }

    public static String getUserName()
    {
        return get(SecurityConstants.DETAILS_USERNAME);
    }

    public static void setUserName(String username)
    {
        set(SecurityConstants.DETAILS_USERNAME, username);
    }
    public static void setEmail(String email){
        set(SecurityConstants.DETAILS_EMAIL, email);
    }
    public static String getEmail(){
        return get(SecurityConstants.DETAILS_EMAIL);
    }
    public static String getUserKey()
    {
        return get(SecurityConstants.USER_KEY);
    }

    public static void setUserKey(String userKey)
    {
        set(SecurityConstants.USER_KEY, userKey);
    }

    public static String getPermission()
    {
        return get(SecurityConstants.ROLE_PERMISSION);
    }

    public static void setPermission(String permissions)
    {
        set(SecurityConstants.ROLE_PERMISSION, permissions);
    }

    public static void remove()
    {
        THREAD_LOCAL.remove();
    }
}
