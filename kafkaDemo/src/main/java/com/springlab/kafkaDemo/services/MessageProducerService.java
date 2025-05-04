package com.springlab.kafkaDemo.services;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String TOPIC  = "message-topic";

    public MessageProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message){
        kafkaTemplate.send(TOPIC, message);
    }
}
