package com.tornado4651.lmix.data.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author tornado4651
 * @date 2024/6/7 11:11
 */
@Slf4j
@SpringBootApplication
public class LMixDataKafkaApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(LMixDataKafkaApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        springApplication.run(args);
    }

}
