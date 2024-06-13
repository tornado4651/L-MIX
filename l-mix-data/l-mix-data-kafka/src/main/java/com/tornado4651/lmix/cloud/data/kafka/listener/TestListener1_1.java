package com.tornado4651.lmix.cloud.data.kafka.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author tornado4651
 * @date 2024/6/11 14:55
 */
@Component
public class TestListener1_1 {

    @KafkaListener(topics = {"test-topic"}, id="test_listener_1_1", groupId = "test_group_1")
    public void listen1(String msg) {
        String s = "sdfsdf";
        s.indexOf("adsfasfd");
        System.out.println("test_listener_1_1: " + msg);
    }

}
