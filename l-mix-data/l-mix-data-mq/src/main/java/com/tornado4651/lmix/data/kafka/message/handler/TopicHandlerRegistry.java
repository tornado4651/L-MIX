package com.tornado4651.lmix.data.kafka.message.handler;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tornado4651
 * @date 2024/6/21 18:03
 */
@Component
public class TopicHandlerRegistry {

    private final Map<String, TopicHandler> handlers = new HashMap<>();

    public void registerHandler(String topic, TopicHandler handler) {
        handlers.put(topic, handler);
    }

    public TopicHandler getHandler(String topic) {
        return handlers.getOrDefault(topic, message -> {
            throw new IllegalArgumentException("No handler found for topic: " + topic);
        });
    }
}
