package com.tornado4651.lmix.cloud.data.kafka.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author tornado4651
 * @date 2024/6/11 14:55
 */
@Component
public class TestListener2_2 {

    @KafkaListener(topics = {"test-topic"}, id="test_listener_2_2", groupId = "test_group_2")
    public void listen1(String msg) {
        System.out.println("test_listener_2_2: " + msg);
    }

}
