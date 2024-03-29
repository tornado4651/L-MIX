package com.tornado4651.lmix.cloud.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 网关服务
 */
@EnableDiscoveryClient
@SpringBootApplication
public class LMixCloudGatewayAppication {
    public static void main( String[] args ) {
        SpringApplication.run(LMixCloudGatewayAppication.class, args);

    }
}
