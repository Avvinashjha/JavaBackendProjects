package com.dailycoder.kafkaDemo.controllers;

import com.dailycoder.kafkaDemo.model.PostDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final KafkaTemplate<String, PostDTO> kafkaTemplate;


    private static final String topic = "new-blog-posts";

    public PostController(KafkaTemplate<String, PostDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/publish")
    public String sendPost(PostDTO post) {
        kafkaTemplate.send(topic, post.getAuthor(), post);
        return "Post sent to Kafka topic";
    }
}
