package com.tornado4651.lmix.data.kafka.message;

import com.tornado4651.lmix.data.kafka.message.handler.DefaultMsgHandler;
import com.tornado4651.lmix.data.kafka.message.handler.MsgHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author tornado4651
 * @date 2024/6/7 17:41
 */
@Slf4j
@Component
public class MessageManager {

    @Autowired
    private Map<String, MsgHandler> messageHandlerMap;

    @Autowired
    private DefaultMsgHandler defaultMsgHandler;

    public void execMessage(String topic, Message message) {
        MsgHandler msgHandler = messageHandlerMap.get(topic);
        if (msgHandler == null) {
            defaultMsgHandler.hadleMessage(message);
        }else{
            msgHandler.hadleMessage(message);
        }
    }
}
