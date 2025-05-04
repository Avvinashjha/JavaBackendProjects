package com.springlab.cacheManager.services;
import com.dailycoder.commons.dCache.manager.CacheManager;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CacheService {
    public static final Logger log = LoggerFactory.getLogger(CacheService.class);
    @Autowired
    private CacheManager cacheManager;

    @PostConstruct
    public void init(){
        log.info("Cache provider: {}", cacheManager.isAvailable() ? "Available" : "Unavailable");
    }

    public boolean hasKey(String key) {
        return cacheManager.hasKey(key);
    }

    public void saveWithoutTTK(String key, Object value) {
        cacheManager.saveWithoutTTK(key, value);
    }

    public void save(String key, Object value, long ttl, TimeUnit timeUnit) {
        cacheManager.save(key, value, ttl, timeUnit);
    }

    public boolean remove(String key) {
        return cacheManager.remove(key);
    }

    public Object get(String key) {
        return cacheManager.get(key);
    }

    public boolean isAvailable() {
        return cacheManager.isAvailable();
    }

}
