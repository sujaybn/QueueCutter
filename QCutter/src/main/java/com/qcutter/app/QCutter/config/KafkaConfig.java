package com.qcutter.app.QCutter.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic deadLetterTopic() {
        return TopicBuilder.name("orders.DLT")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public DefaultErrorHandler errorHandler(KafkaOperations<Object, Object> template) {
        // 1. Define the Backoff: Wait 2 seconds, try 3 times total
        FixedBackOff backOff = new FixedBackOff(2000L, 2);

        // 2. Define the Recoverer: Sends the failed message to the DLT
        DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(template);

        // 3. Return the Handler: Spring Kafka will automatically use this
        // for any @KafkaListener in the same context.
        return new DefaultErrorHandler(recoverer, backOff);
    }
}