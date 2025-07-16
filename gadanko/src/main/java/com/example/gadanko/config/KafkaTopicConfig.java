package com.example.gadanko.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic userActivityTopic() {
        return TopicBuilder.name("java-test")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic grouptopicGeneral() {
        return TopicBuilder.name("general")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic grouptopicArt() {
        return TopicBuilder.name("art")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
