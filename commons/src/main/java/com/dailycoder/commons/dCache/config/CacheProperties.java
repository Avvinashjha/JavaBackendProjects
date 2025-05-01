package com.dailycoder.commons.dCache.config;
import com.dailycoder.commons.dCache.enums.ProviderType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "cache")
public class CacheProperties {
    private ProviderType provider = ProviderType.REDIS;
    private RedisProperties redis;
    private AerospikeProperties aerospike;

    public static class RedisProperties{
        private String host;
        private int port;
        private String password;

        // Getters and Setters
        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class AerospikeProperties{
        private String host;
        private int port;
        private String namespace;

        // Getters and Setters
        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getNamespace() {
            return namespace;
        }

        public void setNamespace(String namespace) {
            this.namespace = namespace;
        }
    }

    public ProviderType getProvider() {
        return provider;
    }

    public void setProvider(ProviderType provider) {
        this.provider = provider;
    }

    public RedisProperties getRedis() {
        return redis;
    }

    public void setRedis(RedisProperties redis) {
        this.redis = redis;
    }

    public AerospikeProperties getAerospike() {
        return aerospike;
    }

    public void setAerospike(AerospikeProperties aerospike) {
        this.aerospike = aerospike;
    }
}
