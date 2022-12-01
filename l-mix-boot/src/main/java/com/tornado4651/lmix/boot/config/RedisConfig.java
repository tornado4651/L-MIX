package com.tornado4651.lmix.boot.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, Object> template = new RedisTemplate<>();

        //配置序列化方式
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        FastjsonRedisSerializer<Object> serializer = new FastjsonRedisSerializer<>(Object.class);
//        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper obm=new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        obm.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会抛出异常
        obm.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
//        serializer.setObjectMapper(obm);
        // 配置RedisTemplate
        // key
        template.setKeySerializer(stringRedisSerializer);
        // value
        template.setValueSerializer(serializer);
        // Hash Key
        template.setHashKeySerializer(stringRedisSerializer);
        // Hash Value
        template.setHashValueSerializer(serializer);
        // 连接工厂
        template.setConnectionFactory(factory);
        template.afterPropertiesSet();
        return template;
    }
}
