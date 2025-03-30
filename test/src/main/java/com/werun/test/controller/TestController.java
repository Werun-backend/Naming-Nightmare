package com.werun.test.controller;

import com.werun.common.core.context.SecurityContextHolder;
import com.werun.common.core.request.Result;
import com.werun.common.security.entity.LoginUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("get")
    public Result<LoginUser> test() {
        LoginUser user = new LoginUser();
        user.setEmail(SecurityContextHolder.getEmail());
        user.setUserid(SecurityContextHolder.getUserId());
        return Result.ok(user);
    }
}
