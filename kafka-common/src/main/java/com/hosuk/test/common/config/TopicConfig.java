package com.hosuk.test.common.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class TopicConfig {

    private final Environment environment;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> config = new HashMap<String, Object>();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, environment.getProperty("spring.kafka.bootstrap-servers"));
        return new KafkaAdmin(config);
    }


    @Bean
    public NewTopic sendTopic() {
        return TopicBuilder.name(environment.getProperty("send.topic-name"))
                .partitions(1)
                .replicas(3)
                .build();
    }

    @Bean
    public NewTopic exchangeTopic() {
        return TopicBuilder.name(environment.getProperty("exchange.topic-name"))
                .partitions(1)
                .replicas(3)
                .build();
    }




}