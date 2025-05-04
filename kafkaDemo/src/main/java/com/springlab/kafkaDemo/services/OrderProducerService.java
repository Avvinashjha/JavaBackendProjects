package com.springlab.kafkaDemo.services;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String TOPIC  = "order-topic";

    public OrderProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(String message, String key){
        kafkaTemplate.send(TOPIC, key, message);
        System.out.printf("Sent [%s] -> %s%n", key, message);
    }
}
