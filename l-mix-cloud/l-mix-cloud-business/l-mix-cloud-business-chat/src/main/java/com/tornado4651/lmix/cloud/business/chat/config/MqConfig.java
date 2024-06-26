package com.tornado4651.lmix.cloud.business.chat.config;

import com.tornado4651.lmix.common.constants.ChatConstant;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tornado4651
 * @date 2024/6/26 17:47
 */
@Configuration
public class MqConfig {
    @Bean
    public NewTopic newTopic(){
        return new NewTopic(ChatConstant.TOPIC_NAME, 2, (short) 1);
    }
}
