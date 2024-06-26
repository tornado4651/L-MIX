package com.tornado4651.lmix.data.kafka.message.producer;

import org.springframework.stereotype.Component;

/**
 * 通用消息发送者
 * @author tornado4651
 * @date 2024/6/21 15:05
 */
@Component
public interface LMixProducer {

    void sendMessage(String topic, Object message);

}
