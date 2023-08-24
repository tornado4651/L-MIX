package com.tornado4651.lmix.cloud.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 认证服务
 */
@EnableDiscoveryClient
@SpringBootApplication
public class LMixCloudAuthApplication {
    public static void main( String[] args ) {
        SpringApplication.run(LMixCloudAuthApplication.class, args);
    }
}
