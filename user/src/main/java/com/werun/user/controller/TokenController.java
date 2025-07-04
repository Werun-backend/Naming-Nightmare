package com.werun.user.controller;


import com.werun.common.core.domain.UserPO;
import com.werun.common.core.request.Result;
import com.werun.common.core.utils.JwtUtils;
import com.werun.common.core.utils.StringUtils;
import com.werun.common.security.entity.LoginUser;
import com.werun.common.security.service.TokenService;
import com.werun.user.DTO.UserDTO;
import com.werun.user.mapper.UserMapper;
import com.werun.user.request.LoginRequest;
import com.werun.user.request.RegisterRequest;
import com.werun.user.response.LoginResponse;
import com.werun.user.service.UserService;
import com.werun.user.utils.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@RestController
public class TokenController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserMapper userMapper;

    //todo 1.login
    @PostMapping("login")
    @Operation(summary = "登录", description = "登录")
    public Result<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        // 用户登录
        LoginUser userInfo = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        // 获取登录token
        Map<String, Object> token = tokenService.createToken(userInfo);
        LoginResponse loginResponse = new LoginResponse((String) token.get("access_token"), token.get("expires_in").toString());
        return Result.ok(loginResponse);
    }

    //todo 2.logout
    @DeleteMapping("logout")
    @Operation(summary = "登出", description = "登出")
    public Result<?> logout(HttpServletRequest request) {
        String token = SecurityUtils.getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            String username = JwtUtils.getUserName(token);
            // 删除用户缓存记录
            tokenService.deleteUserCache(token);

        }
        return Result.ok();
    }

    //todo 3.register
    @PostMapping("register")
    @Operation(summary = "注册", description = "注册")
    public Result<?> register(@RequestBody RegisterRequest registerBody) {
        // 用户注册
        userService.register(registerBody.getEmail(), registerBody.getPassword());

        return Result.ok();
    }

    //todo 4.refresh   \
    @PostMapping("refresh")
    @Operation(summary = "刷新token", description = "刷新token")
    public Result<?> refresh(HttpServletRequest request) {
        LoginUser loginUser = tokenService.getLoginUser(SecurityUtils.getToken(request));
        if (StringUtils.isNotNull(loginUser)) {
            // 刷新令牌有效期
            tokenService.refreshToken(loginUser);
            return Result.ok();
        }
        return Result.ok();
    }

    //todo 5.用户编辑自己的信息
    @PostMapping("edit")
    @Operation(summary = "编辑个人信息", description = "编辑个人信息")
    public Result<?> edit(@RequestBody UserDTO user) {
        userService.edit(user);
        return Result.ok();
    }

    @GetMapping("selectUserMessage")
    @Operation(summary = "查询个人信息", description = "查询个人信息")
    public Result<UserPO> selectUserMessage(@RequestParam(required = false) Long userId) {
        if(userId == null){
            UserPO userPO = userService.selectUserMessage(SecurityUtils.getUserId());
            return Result.ok(userPO);
        }else if(userId != null){
            UserPO userPO = userService.selectUserMessage(userId);
            return Result.ok(userPO);
        }
        return Result.fail();
    }

    @PostMapping("uploadAvatar")
    @Operation(summary = "上传头像", description = "上传头像")
    public Result<?> uploadAvatar(@RequestParam MultipartFile picture) throws IOException {
        //插入图片
        Long userId = SecurityUtils.getUserId();
        byte[] pictureData = picture.getBytes();
        userMapper.uploadAvatar(pictureData,userId);
        return Result.ok("upload successfully!");
    }

    /**
     * 头像展示
     * @return
     */
    @GetMapping("/postPicture")
    @Operation(summary = "预览头像", description = "根据ID获取头像")
    public ResponseEntity<byte[]> previewPicture() {
        Long userId = SecurityUtils.getUserId();
        UserPO userPO = userMapper.selectUserMessage(userId);
        if (userPO == null || userPO.getAvatar() == null) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(userPO.getAvatar(), headers, HttpStatus.OK);
    }
}
