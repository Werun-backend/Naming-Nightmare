package com.werun.user.service.iml;

import com.werun.common.core.domain.UserPO;
import com.werun.common.core.exception.ServiceException;
import com.werun.common.core.utils.StringUtils;
import com.werun.common.security.entity.LoginUser;
import com.werun.user.DTO.UserDTO;
import com.werun.user.domain.User;
import com.werun.user.mapper.UserMapper;
import com.werun.user.service.UserService;
import com.werun.user.utils.EmailUtil;
import com.werun.user.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceIml implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordService passwordService;

    @Override
    public LoginUser login(String email, String password) {
        // 邮箱或密码为空 错误
        if (StringUtils.isAnyBlank(email, password))
        {
            throw new ServiceException("邮箱/密码必须填写");
        }

        // 邮箱格式 错误
        if(!EmailUtil.isValidEmail(email)){
            throw new ServiceException("邮箱格式错误");
        }

        // 查询用户信息
        User userResult = userMapper.selectUserByEmail(email);


        passwordService.validate(userResult, password);
        LoginUser loginUser = new LoginUser();
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setUserid(userResult.getUserId());
        loginUser.setEmail(userResult.getEmail());
        return loginUser;
    }

    /**
     * 注册
     */
    public void register(String email, String password)
    {
        // 用户名或密码为空 错误
        if (StringUtils.isAnyBlank(email, password))
        {
            throw new ServiceException("用户/密码必须填写");
        }
        // 邮箱格式 错误
        if(!EmailUtil.isValidEmail(email)){
            throw new ServiceException("邮箱格式错误");
        }
        // 注册用户信息
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(SecurityUtils.encryptPassword(password));
        //判断用户是否存在
        User user = userMapper.selectUserByEmail(email);
        if(user != null){
            throw new ServiceException("用户已存在");
        }
        userMapper.insertUser(newUser);
    }

    /**
     * 编辑个人信息
     * @param user
     */
    @Override
    public void edit(UserDTO user) {
        Long userId = SecurityUtils.getUserId();
        userMapper.editUserMessage(user,userId);
    }

    @Override
    public UserPO selectUserMessage(Long userId) {
        UserPO userPO = userMapper.selectUserMessage(userId);
        return userPO;
    }


}
