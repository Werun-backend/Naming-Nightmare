package com.werun.user.service;

import com.werun.common.security.entity.LoginUser;
import com.werun.user.DTO.UserDTO;
import com.werun.user.PO.UserPO;

public interface UserService {
    public LoginUser login(String username, String password);

    void register(String email, String password);

    void edit(UserDTO user);
}
