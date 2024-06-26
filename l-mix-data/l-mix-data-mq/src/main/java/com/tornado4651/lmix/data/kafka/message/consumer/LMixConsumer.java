package com.tornado4651.lmix.data.kafka.message.consumer;

/**
 * @author tornado4651
 * @date 2024/6/21 17:16
 */
public interface LMixConsumer {
    void receiveMessage(String topic, String message);
}
