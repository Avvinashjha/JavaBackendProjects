package com.dailycoder.kafkaDemo.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumerService {
    @KafkaListener(topics = "order-topic", groupId = "order-group")
    public void consume(String message, @Header(KafkaHeaders.RECEIVED_PARTITION) String partition){
        System.out.printf("Partition: [%s] Consumed Order:[%s] ",partition,message);
    }
}
