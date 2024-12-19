package com.hotdog.saas.domain.repository;

import com.hotdog.saas.domain.enums.kafka.KafkaDeadMessageStatusEnum;
import com.hotdog.saas.domain.model.KafkaDeadMessage;

import java.util.List;

public interface KafkaDeadMessageRepository {

    void save(KafkaDeadMessage kafkaDeadMessage);

    List<KafkaDeadMessage> list(List<KafkaDeadMessageStatusEnum> statusList);
}
