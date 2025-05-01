package com.dailycoder.commons.dCache.data_store;
import com.dailycoder.commons.dCache.strategies.CacheStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisCacheStore implements CacheStrategy {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisCacheStore(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean hasKey(String key) throws RuntimeException {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    @Override
    public void saveWithoutTTK(String key, Object value) throws RuntimeException {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void save(String key, Object value, long ttl, TimeUnit timeUnit) throws RuntimeException {
        redisTemplate.opsForValue().set(key, value, ttl, timeUnit);
    }

    @Override
    public boolean remove(String key) throws RuntimeException {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

    @Override
    public Object get(String key) throws RuntimeException {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean isAvailable() throws RuntimeException {
        try {
            return "PONG".equals(redisTemplate.getConnectionFactory().getConnection().ping());
        }catch (Exception e){
            return false;
        }
    }
}
