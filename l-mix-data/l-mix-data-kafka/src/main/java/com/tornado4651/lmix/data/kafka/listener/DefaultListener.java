package com.tornado4651.lmix.data.kafka.listener;

import com.tornado4651.lmix.data.kafka.message.MessageManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author tornado4651
 * @date 2024/6/7 11:38
 */
@Slf4j
@Component
public class DefaultListener  {

    @Autowired
    MessageManager messageManager;

    @KafkaListener(id = "${spring.kafka.listener.client-id:lmix-default-listener}",
            topics = "${spring.kafka.listener.default-topic:lmix-default-topic}",
            groupId = "${spring.kafka.consumer.group-id:lmix-default-group}")
    public void listen(String topic, Message message) {
        messageManager.execMessage(topic, message);
    }
}
