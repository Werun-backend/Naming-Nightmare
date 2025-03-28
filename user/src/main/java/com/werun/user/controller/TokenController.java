package com.werun.user.controller;

import com.werun.common.core.request.Result;
import com.werun.common.security.entity.LoginUser;
import com.werun.common.security.service.TokenService;
import com.werun.user.request.LoginRequest;
import com.werun.user.response.LoginResponse;
import com.werun.user.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TokenController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    //todo 1.login
    public Result<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        // 用户登录
        LoginUser userInfo = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        // 获取登录token
        Map<String, Object> token = tokenService.createToken(userInfo);
        LoginResponse loginResponse = new LoginResponse((String) token.get("access_token"),(String) token.get("expires_in"));
        return Result.ok(loginResponse);
    }
    //todo 2.logout
    //todo 3.register
    //todo 4.refresh
    //todo 5.用户编辑自己的信息

}
