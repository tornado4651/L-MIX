package com.tornado4651.lmix.data.kafka.message.handler;

/**
 * @author tornado4651
 * @date 2024/6/21 18:04
 */
public interface TopicHandler {
    void handleMessage(String message);
}
