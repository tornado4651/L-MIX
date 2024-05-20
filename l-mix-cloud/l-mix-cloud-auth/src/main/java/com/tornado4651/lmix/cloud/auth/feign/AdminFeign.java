package com.tornado4651.lmix.cloud.auth.feign;

import com.tornado4651.lmix.cloud.common.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * l-mix-admin服务feign
 * @author tornado4651
 */
@Component
@FeignClient("l-mix-cloud-admin")
public interface AdminFeign {

    @GetMapping("/inner/userInfo")
    UserDTO getUserInfo(@RequestParam("username") String username);
}
