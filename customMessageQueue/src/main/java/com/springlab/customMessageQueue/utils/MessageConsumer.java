package com.springlab.customMessageQueue.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {
    @Autowired
    private MessageQueue messageQueue;

    @Scheduled(fixedRate = 1000)
    public void consume() {
        while(!messageQueue.isEmpty()) {
            String message = messageQueue.consume();
            System.out.println("Consumed: " + message);
        }
    }
}
