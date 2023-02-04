package com.liwell.cinema.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/29
 */
@Configuration
public class RedisTemplateConfig {

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);

        redisTemplate.setDefaultSerializer(genericJackson2JsonRedisSerializer);
        redisTemplate.setEnableDefaultSerializer(true);

//        Jackson2JsonRedisSerializer<Object> jacksonSeial = new Jackson2JsonRedisSerializer<>(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jacksonSeial.setObjectMapper(om);
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//
//        redisTemplate.setKeySerializer(stringRedisSerializer);
//        redisTemplate.setValueSerializer(jacksonSeial);
//        redisTemplate.setHashKeySerializer(stringRedisSerializer);
//        redisTemplate.setHashValueSerializer(jacksonSeial);
//
//        redisTemplate.setDefaultSerializer(jacksonSeial);
//        redisTemplate.setEnableDefaultSerializer(true);

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}
