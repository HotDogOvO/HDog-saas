package com.hotdog.saas.matrix.domain.foundation;

public interface KafkaProducer<T> {

    void publish(String topic, T message);
}
