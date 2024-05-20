package com.tornado4651.lmix.cloud.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;

/**
 * @author tornado4651
 * @date 2024/5/16 09:51
 */
public class LMixCommonApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(LMixCommonApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        springApplication.run(args);
    }
}
