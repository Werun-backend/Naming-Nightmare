package com.werun.user.service;

import com.werun.common.core.domain.UserPO;
import com.werun.common.security.entity.LoginUser;
import com.werun.user.DTO.UserDTO;

public interface UserService {
    public LoginUser login(String username, String password);

    void register(String email, String password);

    void edit(UserDTO user);

    UserPO selectUserMessage(Long userId);


}
