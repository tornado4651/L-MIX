package com.tornado4651.lmix.cloud.auth.controller;

import com.tornado4651.lmix.cloud.auth.domain.Oauth2TokenDto;
import com.tornado4651.lmix.cloud.auth.holder.LoginUserHolder;
import com.tornado4651.lmix.common.bean.CommonResult;
import com.tornado4651.lmix.common.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

/**
 * 自定义Oauth2获取令牌接口
 */
@RestController
@RequestMapping("/oauth")
public class AuthController {

    @Autowired
    private TokenEndpoint tokenEndpoint;

    /**
     * Oauth2登录认证
     */
    @PostMapping(value = "/token")
    public CommonResult postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .tokenHead("Bearer ").build();
        return CommonResult.success(oauth2TokenDto);
    }

    /**
     * 登录用户信息查询
     */
    @GetMapping(value = "/loginUserInfo")
    public CommonResult getLoginUserInfo() {
        UserDTO loginUserInfo = LoginUserHolder.getCurrentUser();
        return CommonResult.success(loginUserInfo);
    }
}
