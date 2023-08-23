package com.tornado4651.lmix.cloud.admin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * l-mix-api feign组件
 * @author tornado4651
 * @date 2023/6/21 18:03
 */
@FeignClient(value = "l-mix-api")
public interface ApiFeign {
    @GetMapping("/hello/test")
    String test(@RequestParam("username") String username);
}
