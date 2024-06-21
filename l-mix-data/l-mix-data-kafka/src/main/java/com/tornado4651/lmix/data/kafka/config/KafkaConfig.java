package com.tornado4651.lmix.data.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author tornado4651
 * @date 2024/6/11 14:57
 */
@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic testTopic() {
        return new NewTopic("test-topic", 2, (short) 1);
    }
}
