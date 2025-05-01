package com.dailycoder.cacheManager.dto;
import lombok.Data;
import java.util.concurrent.TimeUnit;

@Data
public class CacheRequest {
    private String key;
    private Object value;
    private Long ttl;
    private TimeUnit timeUnit = TimeUnit.SECONDS;

    public CacheRequest() {
    }
    public CacheRequest(String key, Object value, Long ttl, TimeUnit timeUnit) {
        this.key = key;
        this.value = value;
        this.ttl = ttl;
        this.timeUnit = timeUnit;
    }

    public CacheRequest(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Long getTtl() {
        return ttl;
    }

    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }
}
