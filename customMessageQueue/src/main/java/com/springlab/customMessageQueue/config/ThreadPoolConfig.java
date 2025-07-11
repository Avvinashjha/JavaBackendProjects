package com.springlab.customMessageQueue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ThreadPoolConfig {

    @Bean
    public ExecutorService taskExecutor() {
        return Executors.newFixedThreadPool(5); // 5 threads in a pool
    }
}
