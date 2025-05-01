package com.dailycoder.kafkaDemo.services.consumer;

import com.dailycoder.kafkaDemo.model.PostDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PostConsumer {
    private static final String topic = "new-blog-posts";
    private static final String groupId = "blog-posts-consumer";
    @KafkaListener(topics =topic, groupId = groupId)
    public void consume(PostDTO postDTO) {
        System.out.printf("Consumed on topic=%s group=%s: %s%n", topic, groupId, postDTO);
    }

}
