package com.lv.vehicle.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.redis
 * FileName: RedisConfig
 * Author: lv
 * Date: 2021/2/4 22:37
 * Description: 描述信息
 */
@Configuration
public class RedisConfig {

    @Bean(value = "redisTemplate")
    @SuppressWarnings(value = { "unchecked", "rawtypes" })
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        // 实例化并配置jackson序列化器
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);
        // String类型序列化器
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // 实例化设置redis中key(string类型)的序列化方式
        redisTemplate.setKeySerializer(stringRedisSerializer);
        // 设置hash的key采用string的序列化方式
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        // 设置value的序列化器
        // 实例化设置redis中value的序列化方式为Jackson
        redisTemplate.setValueSerializer(serializer);
        // 设置hash的value采用jackson的序列化方式
        redisTemplate.setHashValueSerializer(serializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
