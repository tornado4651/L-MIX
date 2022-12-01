package com.tornado4651.lmix.boot.config;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Fastjson2RedisSerializer<T> implements RedisSerializer<T> {

    public static final Charset DEFAULT_CHARACTER = StandardCharsets.UTF_8;

    Class<T> clazz;

    public Fastjson2RedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if(t == null){
            return null;
        }
        return JSON.toJSONString(t).getBytes(DEFAULT_CHARACTER);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if(bytes == null){
            return null;
        }
        String targetString = new String(bytes, DEFAULT_CHARACTER);
        return JSON.parseObject(targetString, clazz);
    }


    protected JavaType getJavaType(Class<T> clazz){
        return TypeFactory.defaultInstance().constructType(clazz);
    }
}
