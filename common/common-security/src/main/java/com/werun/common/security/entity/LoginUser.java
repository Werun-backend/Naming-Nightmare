package com.werun.common.security.entity;


import lombok.*;

/**
 * 登录用户信息
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser {

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 用户名id
     */
    private Long userid;
    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户名
     */
    private String username;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;
}
