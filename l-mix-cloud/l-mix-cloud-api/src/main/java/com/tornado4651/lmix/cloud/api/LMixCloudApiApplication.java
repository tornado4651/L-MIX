package com.tornado4651.lmix.cloud.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class LMixCloudApiApplication {
    public static void main( String[] args ) {
        SpringApplication.run(LMixCloudApiApplication.class, args);
    }
}
