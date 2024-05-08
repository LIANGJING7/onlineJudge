package com.jing.onlinejudgeuserservice;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.Resource;
import java.time.Duration;

public class MycConfig {
    @Bean
    @Resource
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        // 第一套缓存配置策略
        RedisCacheConfiguration cacheConfig1 = RedisCacheConfiguration.defaultCacheConfig()
                // 设置过期时间 10 分钟
                .entryTtl(Duration.ofMinutes(10))
                // 设置key名前缀
                .prefixCacheNameWith("sys:cache:")
                // 禁止缓存 null 值
                .disableCachingNullValues()
                // 设置 key 序列化
                .serializeKeysWith(keyPair())
                // 设置 value 序列化
                .serializeValuesWith(valuePair());

        // 第二套缓存配置策略
        RedisCacheConfiguration cacheConfig2 = RedisCacheConfiguration.defaultCacheConfig()
                // 设置过期时间 30 秒
                .entryTtl(Duration.ofSeconds(30))
                .prefixCacheNameWith("sys:cache:")
                .disableCachingNullValues()
                .serializeKeysWith(keyPair())
                .serializeValuesWith(valuePair());

        // 将两套缓存策略添加到缓存管理器中，并设定好策略名称。
        return RedisCacheManager.builder(redisConnectionFactory)
                // 将上面的两套配置，注册到缓存管理器中，并定义名称
                .withCacheConfiguration("user", cacheConfig1)
                .withCacheConfiguration("admin", cacheConfig2)
                .build();
    }

    /**
     * 配置缓存框架中，键的序列化方式
     * @return StringRedisSerializer
     */
    private RedisSerializationContext.SerializationPair<String> keyPair() {
        return RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.string());
    }

    /**
     * 配置缓存框架中，值的序列化方式
     * @return GenericJackson2JsonRedisSerializer
     */
    private RedisSerializationContext.SerializationPair<Object> valuePair() {
        return RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json());
    }
}
