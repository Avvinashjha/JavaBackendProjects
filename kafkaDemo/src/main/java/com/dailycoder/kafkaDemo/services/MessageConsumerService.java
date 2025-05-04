package com.dailycoder.kafkaDemo.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumerService {
    @KafkaListener(topics = "message-topic", groupId = "message-group")
    public void consume(String message){
        System.out.println("Consumed Message: "+ message);
    }
}
