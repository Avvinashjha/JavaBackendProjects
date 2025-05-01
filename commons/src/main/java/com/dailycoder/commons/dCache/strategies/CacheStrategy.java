package com.dailycoder.commons.dCache.strategies;

import java.util.concurrent.TimeUnit;

public interface CacheStrategy {
    boolean hasKey(String key) throws RuntimeException;
    void saveWithoutTTK(String key, Object value) throws RuntimeException;
    void save(String key, Object value, long ttl, TimeUnit timeUnit) throws RuntimeException;
    boolean remove(String key) throws RuntimeException;
    Object get(String key) throws RuntimeException;
    boolean isAvailable() throws RuntimeException;
}
