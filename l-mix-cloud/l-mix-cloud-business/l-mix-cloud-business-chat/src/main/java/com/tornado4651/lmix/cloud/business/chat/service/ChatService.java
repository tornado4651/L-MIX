package com.tornado4651.lmix.cloud.business.chat.service;

import com.tornado4651.lmix.common.constants.ChatConstant;
import com.tornado4651.lmix.data.kafka.message.producer.LMixProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tornado4651
 * @date 2024/6/26 16:46
 */
@Service
public class ChatService {

    @Autowired
    private LMixProducer lMixProducer;

    public void startChat(){

    }

    public void sendMessage(String message){
        lMixProducer.sendMessage(ChatConstant.TOPIC_NAME,message);
    }

    public void stopChat(){

    }
}
