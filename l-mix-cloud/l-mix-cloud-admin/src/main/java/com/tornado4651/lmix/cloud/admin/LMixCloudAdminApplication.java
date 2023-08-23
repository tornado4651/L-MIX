package com.tornado4651.lmix.cloud.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hello world!
 *
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class LMixCloudAdminApplication {
    public static void main( String[] args ) {
        SpringApplication.run(LMixCloudAdminApplication.class, args);
    }
}
