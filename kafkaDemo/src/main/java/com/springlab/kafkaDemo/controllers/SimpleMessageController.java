package com.springlab.kafkaDemo.controllers;

import com.springlab.kafkaDemo.services.MessageProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafkaDemo/message")
public class SimpleMessageController {
    private final MessageProducerService producerService;

    public SimpleMessageController(MessageProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestParam String message){
        producerService.sendMessage(message);
        return ResponseEntity.ok("Message sent to kafka");
    }
}
