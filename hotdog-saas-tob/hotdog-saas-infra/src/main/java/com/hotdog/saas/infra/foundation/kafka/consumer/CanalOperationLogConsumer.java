package com.hotdog.saas.infra.foundation.kafka.consumer;

import com.hotdog.saas.domain.constant.Constants;
import com.hotdog.saas.domain.enums.log.LogOperationEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.model.OperationLog;
import com.hotdog.saas.domain.model.message.CanalLogMessage;
import com.hotdog.saas.domain.repository.OperationLogRepository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class CanalOperationLogConsumer extends AbstractKafkaConsumer<CanalLogMessage> {

    private final OperationLogRepository operationLogRepository;

    public CanalOperationLogConsumer(OperationLogRepository operationLogRepository) {
        this.operationLogRepository = operationLogRepository;
    }

    @Transactional(rollbackFor = Exception.class)
//    @KafkaListener(topics = KafkaConstants.CANAL_OPERATION_TOPIC)
    public void operationLogListener(String message, Acknowledgment acknowledgment) {
        try {
            log.debug("监听canal-kafka消息，请求原串：{}", message);
            CanalLogMessage canalLogMessage = super.decodeMessage(message, CanalLogMessage.class);

            if (!checkTable(canalLogMessage.getTable())) {
                return;
            }

            LogOperationEnum logOperationEnum = LogOperationEnum.getByAction(canalLogMessage.getType());
            List<OperationLog> operationLogList = logOperationEnum.execute(canalLogMessage);

            log.info("监听canal-kafka消息，解析原串：{}", operationLogList);
            Integer saveCount = operationLogRepository.batchSave(operationLogList);

            // 如果是补偿任务，需要更新补偿任务表的状态
            super.checkRetryTask(canalLogMessage);

            log.debug("监听canal-kafka消息，操作日志记录完成，记录条数：{}，日志：{}", saveCount, operationLogList);
        } catch (Exception e) {
            log.error("监听canal-kafka消息，操作日志记录异常：{}", e.getMessage(), e);
            throw new BusinessException("kafka 失败");
        } finally {
            acknowledgment.acknowledge();
        }
    }

    /**
     * 是否需要保存操作日志
     *
     * @param tableName 表名
     * @return boolean
     */
    private boolean checkTable(String tableName) {
        if (StringUtils.isEmpty(tableName)) {
            return false;
        }
        return Constants.NEED_RECORD_OPERATION_LOG_TABLE.contains(tableName);
    }

}
