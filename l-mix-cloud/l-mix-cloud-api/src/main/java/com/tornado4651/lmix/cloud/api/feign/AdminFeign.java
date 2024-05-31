package com.tornado4651.lmix.cloud.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * l-mix-api feign组件
 * @author tornado4651
 * @date 2023/6/21 18:03
 */
@FeignClient(value = "l-mix-cloud-admin")
public interface AdminFeign {
    @GetMapping("/usesr/currentUser")
    String currentUser();

    @GetMapping("/user/adminInfo")
    String adminInfo();

    @GetMapping("/user/publicInfo")
    String publicInfo();


}
