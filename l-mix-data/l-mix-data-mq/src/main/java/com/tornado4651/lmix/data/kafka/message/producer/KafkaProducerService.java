package com.tornado4651.lmix.data.kafka.message.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author tornado4651
 * @date 2024/6/21 17:40
 */
@Service
public class KafkaProducerService implements LMixProducer{

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(String topic, Object message) {
        kafkaTemplate.send(topic, message);
    }
}
