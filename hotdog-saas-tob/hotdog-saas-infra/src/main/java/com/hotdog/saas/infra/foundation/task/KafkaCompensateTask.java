package com.hotdog.saas.infra.foundation.task;

import com.hotdog.saas.domain.enums.kafka.KafkaDeadMessageStatusEnum;
import com.hotdog.saas.domain.foundation.KafkaProducer;
import com.hotdog.saas.domain.model.KafkaDeadMessage;
import com.hotdog.saas.domain.repository.KafkaDeadMessageRepository;
import com.hotdog.saas.domain.utils.ConcurrentUtils;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * kafka补偿定时任务
 * @author hotdog
 * @date 2024/12/19 17:38
 */
@Slf4j
@Component
public class KafkaCompensateTask {

    private final KafkaDeadMessageRepository kafkaDeadMessageRepository;
    private final KafkaProducer<String> kafkaProducer;

    public KafkaCompensateTask(KafkaDeadMessageRepository kafkaDeadMessageRepository, KafkaProducer<String> kafkaProducer) {
        this.kafkaDeadMessageRepository = kafkaDeadMessageRepository;
        this.kafkaProducer = kafkaProducer;
    }

    @Scheduled(cron = "0 */10 * * * ?")
    public void kafkaCompensateTask() {
        List<KafkaDeadMessage> list = kafkaDeadMessageRepository.list(KafkaDeadMessageStatusEnum.needRetryStatus());
        ConcurrentUtils.async(() -> list.forEach(kafkaDeadMessage ->
                kafkaProducer.publish(kafkaDeadMessage.getKafkaTopic(), kafkaDeadMessage.getKafkaValue())
        ));
    }
}
