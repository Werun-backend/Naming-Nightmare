package com.werun.user.mapper;

import com.werun.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    /**
     * 根据邮箱和密码查询用户
     * @param email 用户邮箱(作为用户名)
     * @param password 加密后的密码
     * @return 用户信息
     */
    public  User selectUserByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    /**
     * 插入新用户
     * @param user 用户对象
     * @return 影响的行数
     */
    public int insertUser(User user);

    /**
     * 根据邮箱查询用户
     * @param email 用户邮箱
     * @return 用户信息
     */
    public User selectUserByEmail(String email);
}
