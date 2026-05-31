package com.kishan.mongo.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;
    @Disabled
    @Test
    void testRedis(){
        redisTemplate.opsForValue().set("email","k@gmail.com");
        Object email = redisTemplate.opsForValue().get("email");
        int a=1;
    }
}
