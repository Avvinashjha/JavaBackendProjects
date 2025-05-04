package com.springlab.customMessageQueue.controller;

import com.springlab.customMessageQueue.service.MessageQueueService;
import com.springlab.customMessageQueue.utils.MessageQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customQueue/messages")
public class MessageController {

    @Autowired
    private MessageQueue messageQueue;
    @Autowired
    private MessageQueueService messageQueueService;

    @PostMapping("/inMemory")
    public String sendMessage(@RequestBody String message) {
        messageQueue.produce(message);
        return "Message sent to custom queue";
    }
    @PostMapping("/h2")
    public String sendMessageV2(@RequestBody String message){
        messageQueueService.produce(message);
        return "Message sent to h2 db";
    }
}
