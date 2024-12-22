package com.hotdog.saas.domain.foundation;

public interface KafkaProducer<T> {

    void publish(String topic, T message);
}
