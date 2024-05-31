package com.tornado4651.lmix.cloud.gateway.config;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * @author tornado4651
 * @date 2023/8/28 00:13
 */
@Configuration
public class CorsConfig {

    /**
     * Order 和 ResfreshScope够解决前端重定向请求以及登出请求时产生的CORS error问题
     */
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @RefreshScope
    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        //支持所有方法
        config.addAllowedMethod("*");
        //跨域处理 允许所有的域
        config.addAllowedOrigin("*");
        //支持所有请求头
        config.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        //匹配所有请求
        source.registerCorsConfiguration("/**", config);
        return new CorsWebFilter(source);
    }

}
