package com.hotdog.saas.infra.foundation.kafka.consumer;

import com.alibaba.fastjson2.JSONObject;
import com.hotdog.saas.domain.constant.KafkaConstants;
import com.hotdog.saas.domain.enums.kafka.KafkaDeadMessageStatusEnum;
import com.hotdog.saas.domain.model.KafkaDeadMessage;
import com.hotdog.saas.domain.repository.KafkaDeadMessageRepository;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DeadLetterQueueConsumer {

    private final KafkaDeadMessageRepository kafkaDeadMessageRepository;

    public DeadLetterQueueConsumer(KafkaDeadMessageRepository kafkaDeadMessageRepository) {
        this.kafkaDeadMessageRepository = kafkaDeadMessageRepository;
    }

//    @KafkaListener(topics = KafkaConstants.DEAD_LETTER_QUEUE_TOPIC)
    public void handleDltMessage(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        try{
            KafkaDeadMessage kafkaDeadMessage = KafkaDeadMessage.builder()
                    .kafkaTopic(getOriginalTopic(record.headers()))
                    .kafkaPartition(String.valueOf(record.partition()))
                    .status(KafkaDeadMessageStatusEnum.NOT_RETRY.getCode())
                    .kafkaKey(record.key())
                    .kafkaValue(record.value())
                    .build();

            kafkaDeadMessageRepository.save(kafkaDeadMessage);

            acknowledgment.acknowledge();
        } catch (Exception e){
            log.error("死信队列消息，操作死信队列数据异常：{}", e.getMessage(), e);
        }
    }

    /**
     * 获取原始topic
     * @param headers kafka header
     * @return 原始topic
     */
    private String getOriginalTopic(Headers headers) {
        if (headers != null) {
            for (Header header : headers) {
                if ("kafka_dlt-original-topic".equals(header.key())) {
                    return new String(header.value(), StandardCharsets.UTF_8);
                }
            }
        }
        return StringUtils.EMPTY;
    }
}
