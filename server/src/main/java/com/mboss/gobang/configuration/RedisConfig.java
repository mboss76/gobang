package com.mboss.gobang.configuration;


import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object> redisTemplate=new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        GenericFastJsonRedisSerializer genericFastJsonRedisSerializer=new GenericFastJsonRedisSerializer();
        StringRedisSerializer stringRedisSerializer=new StringRedisSerializer();

        //key采用string序列化
        redisTemplate.setKeySerializer(stringRedisSerializer);
        //hash的key采用string序列化
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        //value序列化采用fastjson
        redisTemplate.setValueSerializer(genericFastJsonRedisSerializer);
        //hash的value序列化采用fastjson
        redisTemplate.setHashValueSerializer(genericFastJsonRedisSerializer);

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}

