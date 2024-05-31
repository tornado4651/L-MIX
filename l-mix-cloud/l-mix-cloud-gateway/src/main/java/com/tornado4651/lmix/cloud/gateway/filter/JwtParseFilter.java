package com.tornado4651.lmix.cloud.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.nimbusds.jose.JWSObject;
import com.tornado4651.lmix.cloud.common.constants.AuthConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Base64;

/**
 * 将登录用户的JWT转化成用户信息的全局过滤器
 */
@Component
@Slf4j
public class JwtParseFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        try {
            String token = exchange .getRequest().getHeaders().getFirst(AuthConstant.AUTHORIZATION_HEADER);
            // 没有token， 直接放行
            if (StrUtil.isEmpty(token)) {
                return chain.filter(exchange);
            }
            String realToken = token.replace("Bearer ", "");
            JWSObject jwsObject = JWSObject.parse(realToken);
            String loginUserInfoStr = jwsObject.getPayload().toString();

            //从token中解析用户信息并设置到Header中去
            String encodedLoginUserInfoStr = Base64.getEncoder().encodeToString(loginUserInfoStr.getBytes(StandardCharsets.UTF_8));
            exchange = exchange
                    .mutate()
                    .request(req -> req.headers(headers -> headers.set(AuthConstant.AUTHORIZATION_INFO_HEADER, encodedLoginUserInfoStr)))
                    .build();

        } catch (ParseException e) {
            log.error("JWT信息转换用户信息时出错！原因：{}",e.getMessage());
            e.printStackTrace();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
