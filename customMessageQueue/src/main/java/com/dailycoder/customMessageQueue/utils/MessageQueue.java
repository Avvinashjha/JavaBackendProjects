package com.dailycoder.customMessageQueue.utils;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class MessageQueue {
    private ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

    public void produce(String message) {
        System.out.println(queue);
        queue.add(message);
    }

    public String consume(){
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
