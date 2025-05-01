package com.dailycoder.kafkaDemo.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    @KafkaListener(topics = "test-topic", groupId = "my-group")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }
}
