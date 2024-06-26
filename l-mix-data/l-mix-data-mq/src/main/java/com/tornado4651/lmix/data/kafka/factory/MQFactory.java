package com.tornado4651.lmix.data.kafka.factory;

import com.tornado4651.lmix.data.kafka.message.consumer.KafkaConsumerService;
import com.tornado4651.lmix.data.kafka.message.consumer.LMixConsumer;
import com.tornado4651.lmix.data.kafka.message.producer.KafkaProducerService;
import com.tornado4651.lmix.data.kafka.message.producer.LMixProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author tornado4651
 * @date 2024/6/21 17:09
 */
@Component
public class MQFactory {

    @Autowired
    private ApplicationContext applicationContext;

    public LMixProducer createProducer(String type) {
        switch (type) {
            case "kafka":
                return applicationContext.getBean(KafkaProducerService.class);
//            case "rabbitmq":
//                return applicationContext.getBean(RabbitMQProducerServiceImpl.class);
//            case "rocketmq":
//                return applicationContext.getBean(RocketMQProducerServiceImpl.class);
            default:
                throw new IllegalArgumentException("未知 MQ type: " + type);
        }
    }

    public LMixConsumer createConsumer(String type) {
        switch (type.toLowerCase()) {
            case "kafka":
                return applicationContext.getBean(KafkaConsumerService.class);
//            case "rabbitmq":
//                return applicationContext.getBean(RabbitMQConsumerService.class);
//            case "rocketmq":
//                return applicationContext.getBean(RocketMQConsumerService.class);
            default:
                throw new IllegalArgumentException("Unknown MQ type: " + type);
        }
    }

}
