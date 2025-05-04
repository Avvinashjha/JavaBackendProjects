package com.springlab.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {
        "com.dailycoder.server",
        "com.dailycoder.cricInfo",
        "com.dailycoder.commons",
        "com.dailycoder.cacheManager",
        "com.dailycoder.authManager",
        "com.dailycoder.kafkaDemo",
        "com.dailycoder.customMessageQueue",
})

@EnableJpaRepositories(basePackages = {
        "com.dailycoder.authManager.repository",
        "com.dailycoder.customMessageQueue.repository",
        "com.dailycoder.kafkaDemo.repository",
})

@EntityScan(basePackages = {
        "com.dailycoder.authManager.entities",
        "com.dailycoder.kafkaDemo.entities",
        "com.dailycoder.customMessageQueue.entities"
})
@EnableScheduling
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
