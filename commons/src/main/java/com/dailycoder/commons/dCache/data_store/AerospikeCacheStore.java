package com.dailycoder.commons.dCache.data_store;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.WritePolicy;
import com.dailycoder.commons.dCache.config.CacheProperties;
import com.dailycoder.commons.dCache.strategies.CacheStrategy;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

public class AerospikeCacheStore implements CacheStrategy {
    private final AerospikeClient client;
    private final CacheProperties properties;

    public AerospikeCacheStore(AerospikeClient client, CacheProperties properties) {
        this.client = client;
        this.properties = properties;
    }


    @Override
    public boolean hasKey(String key) throws RuntimeException {
        return client.get(null, makeKey(key)) != null;
    }

    @Override
    public void saveWithoutTTK(String key, Object value) throws RuntimeException {
        client.put(null, makeKey(key), new Bin("value", value));
    }

    @Override
    public void save(String key, Object value, long ttl, TimeUnit timeUnit) throws RuntimeException {
        WritePolicy policy = new WritePolicy();
        policy.expiration = (int) timeUnit.toSeconds(ttl);
        client.put(policy, makeKey(key), new Bin("value", value));
    }

    @Override
    public boolean remove(String key) throws RuntimeException {
        return client.delete(null, makeKey(key));
    }

    @Override
    public Object get(String key) throws RuntimeException {
        Record record = client.get(null, makeKey(key));
        return record != null ? record.getValue("value") : null;
    }

    @Override
    public boolean isAvailable() throws RuntimeException {
        try {
            return client.isConnected();
        }catch (Exception e){
            return false;
        }
    }

    private Key makeKey(String key){
        return new Key(properties.getAerospike().getNamespace(),"cache" ,key);
    }
}
