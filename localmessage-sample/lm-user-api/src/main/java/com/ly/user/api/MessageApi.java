package com.ly.user.api;

import com.ly.common.base.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "lm-user", path = "/message")
public interface MessageApi {
    @PostMapping("/confirm/{messageId}")
    ResponseResult confirmMessage(@PathVariable("messageId") Long messageId);
}
