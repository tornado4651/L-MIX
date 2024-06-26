package com.tornado4651.lmix.data.kafka.config;

import com.tornado4651.lmix.data.kafka.factory.MQFactory;
import com.tornado4651.lmix.data.kafka.message.consumer.LMixConsumer;
import com.tornado4651.lmix.data.kafka.message.producer.LMixProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tornado4651
 * @date 2024/6/21 17:55
 */
@Configuration
public class MQConfig {
    @Value("${lmix.mq.type}")
    private String mqType;

    private final MQFactory mqFactory;

    public MQConfig(MQFactory mqFactory) {
        this.mqFactory = mqFactory;
    }

    @Bean(name = "lMixProducer")
    public LMixProducer lMixProducer() {
        return mqFactory.createProducer(mqType);
    }

    @Bean(name = "lMixProducer")
    public LMixConsumer lMixConsumer() {
        return mqFactory.createConsumer(mqType);
    }
}
