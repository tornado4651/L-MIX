package com.tornado4651.lmix.data.kafka.message.consumer;

import com.tornado4651.lmix.data.kafka.message.handler.TopicHandlerRegistry;
import org.springframework.kafka.annotation.KafkaListener;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

/**
 * @author tornado4651
 * @date 2024/6/21 17:54
 */
//@Service
public class KafkaConsumerService implements LMixConsumer{

    private final TopicHandlerRegistry topicHandlerRegistry;

    public KafkaConsumerService(TopicHandlerRegistry topicHandlerRegistry) {
        this.topicHandlerRegistry = topicHandlerRegistry;
    }


    @Override
    @KafkaListener(topics = "#{'${lmix.mq.consumer.topics}'.split(',')}", groupId = "${lmix.mq.consumer.group_id}")
    public void receiveMessage(String topic, String message) {
        topicHandlerRegistry.getHandler(topic).handleMessage(message);
    }


    private static final String DIGITS = "0123456789";

    public static String generateRandomNumber(int n) {
    SecureRandom RANDOM = new SecureRandom();
        if (n <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }

        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = RANDOM.nextInt(DIGITS.length());
            sb.append(DIGITS.charAt(index));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws InterruptedException {
        // 测试
        int n = 6; // 生成6位随机数字字符串
        Set<String> cache = new HashSet<>();
        for (int i = 0; i < 1000000; i++) {
            String s = generateRandomNumber(n);
            if(cache.contains(s)) {
                System.out.println("重复了！"+i+"   "+s);
                return;
            }else{
                cache.add(s);
                Thread.sleep(500);
            }
        }
        System.out.println("没有重复！");
    }
}
