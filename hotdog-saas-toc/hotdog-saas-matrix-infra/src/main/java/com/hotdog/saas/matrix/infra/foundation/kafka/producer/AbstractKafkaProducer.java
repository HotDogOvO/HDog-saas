package com.hotdog.saas.matrix.infra.foundation.kafka.producer;

import com.hotdog.saas.matrix.domain.foundation.KafkaProducer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class AbstractKafkaProducer<T> implements KafkaProducer<T> {

    private final KafkaTemplate<String, T> kafkaTemplate;

    protected AbstractKafkaProducer(KafkaTemplate<String, T> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(String topic, T message) {
        kafkaTemplate.send(topic, message);
    }
}
