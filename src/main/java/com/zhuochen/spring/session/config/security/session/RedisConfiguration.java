package com.zhuochen.spring.session.config.security.session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
@Configuration
public class RedisConfiguration {

    @Bean
    public StringRedisTemplate stringRedisTemplate() {
        StringRedisTemplate template = new StringRedisTemplate(connectionFactory());
        // explicitly enable transaction support
//        template.setEnableTransactionSupport(true);
        return template;
    }

    @Bean
    public RedisConnectionFactory connectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate<byte[], byte[]>();
        redisTemplate.setConnectionFactory(connectionFactory());
        return redisTemplate;
    }

//    @Bean
//    public RedisKeyValueTemplate redisKeyValueTemplate() {
//        RedisKeyValueTemplate redisKeyValueTemplate = new RedisKeyValueTemplate();
//
//    }

//    @Bean
//    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
//        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
//                .prefixKeysWith("redis-cache-prefix-")
////                .entryTtl(Duration.ofSeconds(1))
//                .disableCachingNullValues();
//
//        return RedisCacheManager.builder(connectionFactory)
//                .cacheDefaults(defaultCacheConfig())
//                .transactionAware()
//                .build();
//    }
}