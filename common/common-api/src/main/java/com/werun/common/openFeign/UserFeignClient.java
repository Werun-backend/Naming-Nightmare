package com.werun.common.openFeign;

import com.werun.common.core.domain.UserPO;
import com.werun.common.core.request.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "user")
public interface UserFeignClient {
    @GetMapping("selectUserMessage")
    Result<UserPO> selectUserMessage(@RequestParam("userId") Long userId);
}
