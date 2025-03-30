package com.werun.user.server;

import com.werun.common.security.entity.LoginUser;

public interface UserService {
    public LoginUser login(String username, String password);

    void register(String email, String password);
}
