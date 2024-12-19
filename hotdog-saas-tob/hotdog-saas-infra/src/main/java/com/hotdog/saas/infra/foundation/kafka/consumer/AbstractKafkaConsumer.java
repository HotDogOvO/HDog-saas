package com.hotdog.saas.infra.foundation.kafka.consumer;

import com.alibaba.fastjson2.JSONObject;

import com.hotdog.saas.domain.enums.kafka.KafkaDeadMessageStatusEnum;
import com.hotdog.saas.domain.model.message.BaseMessage;
import com.hotdog.saas.domain.repository.KafkaDeadMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Component
public abstract class AbstractKafkaConsumer<T> {

    @Autowired
    private KafkaDeadMessageRepository kafkaDeadMessageRepository;

    protected T decodeMessage(String message, Class<T> clazz) {
        return JSONObject.parseObject(message, clazz);
    }

    @Transactional(rollbackFor = Exception.class)
    protected void checkRetryTask(BaseMessage message) {
        // 如果是补偿任务，需要更新补偿任务表的状态
        if (isRetryTask(message.getIsRetry())) {
            log.info("监听canal-kafka消息，请求原串：{}", message);
            updateKafkaDeadMessage(message.getMessageId());
        }
    }

    private boolean isRetryTask(Boolean isRetry) {
        return Objects.nonNull(isRetry) && isRetry;
    }

    private void updateKafkaDeadMessage(Long messageId) {
        if (Objects.isNull(messageId)) {
            return;
        }
        kafkaDeadMessageRepository.update(messageId, KafkaDeadMessageStatusEnum.RETRY_SUCCESS);
    }
}
