package com.dailycoder.kafkaDemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaProducerController {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "test-topic";

    @PostMapping("/publish")
    public String sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
        return "Message sent to Kafka topic";
    }

}
