package com.springlab.commons.dCache.factory;

import com.springlab.commons.dCache.data_store.InMemoryCacheStore;
import com.springlab.commons.dCache.data_store.RedisCacheStore;
import com.springlab.commons.dCache.enums.ProviderType;
import com.springlab.commons.dCache.strategies.CacheStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CacheStrategyFactory {
    private final RedisCacheStore redisCacheStore;
//    private final AerospikeCacheStore aerospikeCacheStore;
    private final InMemoryCacheStore inMemoryCacheStore;

    public CacheStrategyFactory(@Autowired(required = false) RedisCacheStore redisCacheStore, InMemoryCacheStore inMemoryCacheStore) {
        this.redisCacheStore = redisCacheStore;
//        this.aerospikeCacheStore = aerospikeCacheStore;
        this.inMemoryCacheStore = inMemoryCacheStore;
    }
    public CacheStrategy findStrategy(ProviderType providerType) {
        switch (providerType) {
            case REDIS:
                return redisCacheStore.isAvailable() ? redisCacheStore : getFallback();
//            case AEROSPIKE:
//                return aerospikeCacheStore.isAvailable() ? aerospikeCacheStore : getFallback();
            default:
                return inMemoryCacheStore; // Fallback to InMemoryCacheStore
        }
    }

    private CacheStrategy getFallback() {
        // Try all available strategies and the fall back to in memory
        if( redisCacheStore.isAvailable()) return redisCacheStore;
//        if( aerospikeCacheStore.isAvailable()) return aerospikeCacheStore;
        return inMemoryCacheStore;
    }
}
