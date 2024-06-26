package com.tornado4651.lmix.data.kafka.message.handler;

import org.springframework.stereotype.Service;

/**
 * @author tornado4651
 * @date 2024/6/21 18:04
 */
@Service
public class SampleTopicHandler implements TopicHandler {

    @Override
    public void handleMessage(String message) {
        // 处理具体的消息逻辑
        System.out.println("Handling message: " + message);
    }
}
