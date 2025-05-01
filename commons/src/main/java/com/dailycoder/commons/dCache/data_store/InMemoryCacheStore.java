package com.dailycoder.commons.dCache.data_store;
import com.dailycoder.commons.dCache.strategies.CacheStrategy;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
public class InMemoryCacheStore implements CacheStrategy {
    private final Map<String, Object> cache = new ConcurrentHashMap<>();
    @Override
    public boolean hasKey(String key) throws RuntimeException {
        return cache.containsKey(key);
    }

    @Override
    public void saveWithoutTTK(String key, Object value) throws RuntimeException {
        cache.put(key,value);
    }

    @Override
    public void save(String key, Object value, long ttl, TimeUnit timeUnit) throws RuntimeException {
        cache.put(key, value);
        //TTL logic
        new Thread(() -> {
            try {
                timeUnit.sleep(ttl);
                cache.remove(key);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    @Override
    public boolean remove(String key) throws RuntimeException {
        return cache.remove(key) != null;
    }

    @Override
    public Object get(String key) throws RuntimeException {
        return cache.get(key);
    }

    @Override
    public boolean isAvailable() throws RuntimeException {
        return true; // Always available in memory
    }
}
