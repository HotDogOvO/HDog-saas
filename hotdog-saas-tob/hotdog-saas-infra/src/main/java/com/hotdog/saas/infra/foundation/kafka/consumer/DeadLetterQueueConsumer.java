package com.hotdog.saas.infra.foundation.kafka.consumer;

import com.hotdog.saas.domain.constant.KafkaConstants;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class DeadLetterQueueConsumer {

    @KafkaListener(topics = KafkaConstants.DEAD_LETTER_QUEUE_TOPIC)
    public void handleDltMessage(ConsumerRecord<?, ?> record) {
        System.out.println("Received dead letter queue message: " + record.value());
    }

}
