package com.tornado4651.lmix.mq.test;

import com.alibaba.fastjson2.JSONObject;
import com.tornado4651.lmix.data.kafka.LMixDataKafkaApplication;
import com.tornado4651.lmix.data.kafka.message.producer.LMixProducer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;

/**
 * @author tornado4651
 * @date 2024/6/24 15:25
 */
@SpringBootTest
@ContextConfiguration(classes = LMixDataKafkaApplication.class)
public class MqTest {

    @Resource
    private LMixProducer lMixProducer;

    @Test
    public void test() {
        JSONObject message = new JSONObject();
        message.put("msg", "hello world2222");
        lMixProducer.sendMessage("delay-topic", message.toJSONString());
    }
}
