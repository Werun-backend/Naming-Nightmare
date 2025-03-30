package com.werun.user.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "登录注册返回类")
public class LoginRequest {
    private String email;
    private String password;
}
