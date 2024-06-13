package com.tornado4651.lmix.cloud.data.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author tornado4651
 * @date 2024/5/16 09:51
 */
@SpringBootApplication
public class LMixDataMybatisApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(LMixDataMybatisApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        springApplication.run(args);
    }
}
