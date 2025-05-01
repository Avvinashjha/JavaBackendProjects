package com.dailycoder.commons.dCache.manager;

import com.dailycoder.commons.dCache.config.CacheProperties;
import com.dailycoder.commons.dCache.factory.CacheStrategyFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CacheManager {
    private final CacheProperties properties;
    private final CacheStrategyFactory strategyFactory;

    public CacheManager(CacheProperties properties, CacheStrategyFactory cacheStrategyFactory) {
        this.properties = properties;
        this.strategyFactory = cacheStrategyFactory;
    }

    public boolean hasKey(String key) {
        return strategyFactory.findStrategy(properties.getProvider()).hasKey(key);
    }

    public void saveWithoutTTK(String key, Object value) {
        strategyFactory.findStrategy(properties.getProvider()).saveWithoutTTK(key, value);
    }

    public void save(String key, Object value, long ttl, TimeUnit timeUnit) {
        strategyFactory.findStrategy(properties.getProvider()).save(key, value, ttl, timeUnit);
    }

    public boolean remove(String key) {
        return strategyFactory.findStrategy(properties.getProvider()).remove(key);
    }

    public Object get(String key) {
        return strategyFactory.findStrategy(properties.getProvider()).get(key);
    }
    public boolean isAvailable() {
        return strategyFactory.findStrategy(properties.getProvider()).isAvailable();
    }
}
