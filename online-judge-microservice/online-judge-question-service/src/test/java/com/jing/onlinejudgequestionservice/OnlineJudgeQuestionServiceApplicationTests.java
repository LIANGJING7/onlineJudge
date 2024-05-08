package com.jing.onlinejudgequestionservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import javax.annotation.Resource;
import java.util.Properties;

@SpringBootTest
class OnlineJudgeQuestionServiceApplicationTests {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testRedisConnection() {
        Object value = redisTemplate.opsForValue().get("test");
        if (value != null) {
            System.out.println("Redis 连接成功");
        } else {
            System.out.println("Redis 连接失败");
        }
    }
}
