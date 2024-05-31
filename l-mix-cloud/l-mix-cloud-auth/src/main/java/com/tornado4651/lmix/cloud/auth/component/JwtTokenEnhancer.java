package com.tornado4651.lmix.cloud.auth.component;

import com.tornado4651.lmix.cloud.auth.domain.SecurityUserDetail;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * JWT内容增强器
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        SecurityUserDetail securityUserDetail = (SecurityUserDetail) authentication.getPrincipal();
        Map<String, Object> info = new HashMap<>();
        //把用户信息设置到JWT中
        info.put("id", securityUserDetail.getId());
        info.put("username", securityUserDetail.getUsername());
        info.put("nickname", securityUserDetail.getNickname());
        info.put("status", securityUserDetail.getStatus());
        info.put("telephone", securityUserDetail.getTelephone());
        info.put("birthday", securityUserDetail.getBirthday());
        info.put("avatar", securityUserDetail.getAvatar());
        info.put("gender", securityUserDetail.getGender());

        info.put("auths", securityUserDetail.getAuths());
        info.put("roles", securityUserDetail.getRoles());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;
    }
}

