package com.springlab.kafkaDemo.controllers;

import com.springlab.kafkaDemo.services.OrderProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafkaDemo/orders")
public class OrderController {
    private final OrderProducerService orderProducerService;

    public OrderController(OrderProducerService orderProducerService) {
        this.orderProducerService = orderProducerService;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestParam String message, @RequestParam String user){
        orderProducerService.sendMessage(message, user);
        return ResponseEntity.ok("Message sent to kafka");
    }
}
