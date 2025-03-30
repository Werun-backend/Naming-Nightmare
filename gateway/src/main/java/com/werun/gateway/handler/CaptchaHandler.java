package com.werun.gateway.handler;

import com.werun.common.core.exception.CaptchaException;
import com.werun.common.core.request.Result;
import com.werun.gateway.domain.Captcha;
import com.werun.gateway.service.ValidateCodeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.function.server.HandlerFunction;
import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 * 对/code接口，颁发验证码
 */
@Component

public class CaptchaHandler implements HandlerFunction<ServerResponse> {
    @Autowired
    private ValidateCodeService validateCodeService;

    @Override
    public Mono<ServerResponse> handle(ServerRequest request) {
        Result<Captcha> r;
        try {
            r = validateCodeService.createCaptcha();
        } catch (CaptchaException | IOException e) {
           return Mono.error(e);
        }
        return ServerResponse.status(HttpStatus.OK).body(BodyInserters.fromValue(r));
    }
}
