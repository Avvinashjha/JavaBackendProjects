package com.springlab.commons.dCache.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class CacheConfig {
    private static final Logger log = LoggerFactory.getLogger(CacheConfig.class);
    @Autowired
    private CacheProperties properties;
    @Bean
    @ConditionalOnProperty(prefix = "cache", name = "provider", havingValue = "REDIS")
    public RedisConnectionFactory redisConnectionFactory(){
        if(properties.getRedis() == null){
            throw new IllegalStateException("Redis configuration is not set");
        }
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(properties.getRedis().getHost());
        config.setPort(properties.getRedis().getPort());
        if(properties.getRedis().getPassword() != null){
            config.setPassword(properties.getRedis().getPassword());
        }
        return new LettuceConnectionFactory(config);
    }


    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }

//    @Bean
//    @ConditionalOnProperty(prefix = "cache.aerospike", name = "host")
//    public AerospikeClient aerospikeClient() {
//        // Validate configuration
//        if (properties.getAerospike() == null) {
//            throw new IllegalStateException("Aerospike configuration is missing");
//        }
//
//        log.info("Creating Aerospike client for {}:{}",
//                properties.getAerospike().getHost(),
//                properties.getAerospike().getPort());
//
//        return new AerospikeClient(
//                properties.getAerospike().getHost(),
//                properties.getAerospike().getPort()
//        );
//    }

}
