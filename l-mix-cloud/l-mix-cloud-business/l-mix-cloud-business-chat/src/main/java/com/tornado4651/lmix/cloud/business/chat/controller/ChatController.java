package com.tornado4651.lmix.cloud.business.chat.controller;

import com.tornado4651.lmix.common.bean.CommonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tornado4651
 * @date 2024/6/26 15:56
 */
@RestController
@RequestMapping("/chat")
public class ChatController {

    @PostMapping("startChat")
    public CommonResult startChat() {

        return CommonResult.success();
    }


    @PostMapping("sendMessage")
    public CommonResult sendMessage() {

        return CommonResult.success();
    }

    @PostMapping("endChat")
    public CommonResult endChat() {

        return CommonResult.success();
    }
}
