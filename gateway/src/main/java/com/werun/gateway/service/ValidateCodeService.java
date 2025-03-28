package com.werun.gateway.service;

import com.werun.common.core.exception.CaptchaException;
import com.werun.common.core.request.Result;
import com.werun.gateway.domain.Captcha;

import java.io.IOException;

public interface ValidateCodeService {
    /**
     * 生成验证码
     */
    public Result<Captcha> createCaptcha() throws IOException, CaptchaException;

    /**
     * 校验验证码
     */
    public void checkCaptcha(String code, String uuid) throws CaptchaException;
}
