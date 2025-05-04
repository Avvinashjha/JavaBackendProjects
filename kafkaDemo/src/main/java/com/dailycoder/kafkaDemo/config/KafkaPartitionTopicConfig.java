package com.dailycoder.kafkaDemo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaPartitionTopicConfig {
    @Bean
    public NewTopic partitionedTopic(){
        return TopicBuilder.name("order-topic")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
