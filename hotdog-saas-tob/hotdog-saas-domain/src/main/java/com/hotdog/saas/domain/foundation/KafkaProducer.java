package com.hotdog.saas.domain.foundation;

public interface KafkaProducer<T> {

    void publish(T message, String partitionKey);
}
