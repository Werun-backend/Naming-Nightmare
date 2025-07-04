package com.werun.user.mapper;

import com.werun.common.core.domain.UserPO;
import com.werun.user.DTO.UserDTO;
import com.werun.user.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface UserMapper {
    /**
     * 根据邮箱和密码查询用户
     * @param email 用户邮箱(作为用户名)
     * @param password 加密后的密码
     * @return 用户信息
     */
    public User selectUserByEmailAndPassword(@Param("email") String email, @Param("password") String password);

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

    /**
     * 根据用户id查询用户
     * @param userId
     * @return 用户信息
     */
    public UserDTO selectUserByUserId(Long userId);

    /**
     * 插入头像
     * @param avatar
     * @param userId
     */
    void uploadAvatar(@Param("avatar") byte[] avatar,@Param("userId") Long userId);

    /**
     * 编辑用户信息
     * @param user
     */
    void editUserMessage(@Param("user") UserDTO user,@Param("userId") Long userId);

    /**
     * 根据用户id查询用户信息
     * @param userId 用户id
     * @return 用户信息
     */
    @Select("select * from user where user_id = #{userId}")
    UserPO selectUserMessage(Long userId);
}
