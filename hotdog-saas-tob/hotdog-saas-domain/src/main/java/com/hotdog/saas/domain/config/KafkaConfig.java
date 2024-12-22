package com.hotdog.saas.domain.config;

import com.hotdog.saas.domain.constant.KafkaConstants;

import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.backoff.FixedBackOff;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaConfig {

    @Value("${spring.kafka.max-attempts:5000}")
    private Long maxAttempts;

    @Value("${spring.kafka.interval:5}")
    private Long interval;

    @Bean
    public KafkaListenerContainerFactory<?> kafkaListenerContainerFactory(ConsumerFactory<String, String> consumerFactory,
                                                                          KafkaTemplate<String, String> kafkaTemplate) {

        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();

        // 自定义死信队列
        DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(
                kafkaTemplate,
                (record, ex) -> {
                    log.error("消息消费失败，发送至死信队列: topic={} partition={}", record.topic(), record.partition());
                    return new TopicPartition(KafkaConstants.DEAD_LETTER_QUEUE_TOPIC, record.partition());
                });

        // 自定义失败重试次数
        factory.setCommonErrorHandler(new DefaultErrorHandler(recoverer,
                new FixedBackOff(interval, maxAttempts)
        ));

        // 手动ack
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);

        factory.setConsumerFactory(consumerFactory);

        return factory;
    }
}
