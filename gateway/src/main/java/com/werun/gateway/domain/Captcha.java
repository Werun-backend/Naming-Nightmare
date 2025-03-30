package com.werun.gateway.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "验证码信息")
public class Captcha {
    @Schema(description = "是否启用验证码")
    private boolean captchaEnabled;
    @Schema(description = "验证码标识，随登录或注册接口返回给后端")
    private String uuid;
    @Schema(description = "基于base64编码的验证码图片")
    private String img;

}
