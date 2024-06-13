package com.tornado4651.lmix.data.kafka.message.handler;

import org.apache.kafka.common.protocol.Message;
import org.springframework.stereotype.Component;

/**
 * @author tornado4651
 * @date 2024/6/7 17:48
 */
@Component
public class DefaultMsgHandler implements MsgHandler<String> {

    @Override
    public String convert(Message message) {
        return null;
    }

    @Override
    public void validateMsg(String topic) {

    }

    @Override
    public void handle(String message) {

    }
}
