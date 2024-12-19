package com.hotdog.saas.infra.foundation.kafka.producer;

import com.hotdog.saas.domain.foundation.KafkaProducer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractKafkaProducer<T> implements KafkaProducer<T> {

    private final KafkaTemplate<String, T> kafkaTemplate;

    protected AbstractKafkaProducer(KafkaTemplate<String, T> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(T message, String partitionKey) {
        System.out.println(message);
    }
}
