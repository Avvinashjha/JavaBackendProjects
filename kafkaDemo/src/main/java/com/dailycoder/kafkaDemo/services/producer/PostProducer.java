package com.dailycoder.kafkaDemo.services.producer;

import com.dailycoder.kafkaDemo.model.PostDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PostProducer {

    private static final String topic = "new-blog-posts";

    private final KafkaTemplate<String, PostDTO> kafkaTemplate;

    public PostProducer(KafkaTemplate<String, PostDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPost(PostDTO post) {
        kafkaTemplate.send(topic, post.getAuthor(), post);
    }
}
