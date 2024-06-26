package com.tornado4651.lmix.data.kafka.message.handler;

import org.apache.kafka.common.protocol.Message;

/**
 * L-MIX topic处理器
 * @author tornado4651
 * @date 2024/6/7 15:17
 */
public interface MsgHandler<T> {

    T convert(Message message);


    void validateMsg(T topic);


    void handle(T message);


    default void hadleMessage(Message message) {

        T contertedMessage = convert(message);

        validateMsg(contertedMessage);

        handle(contertedMessage);
    }
}
