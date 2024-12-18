package com.hotdog.saas.infra.consumer;

import com.hotdog.saas.domain.enums.log.LogOperationEnum;
import com.hotdog.saas.domain.model.message.CanalLogMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class CanalOperationLogConsumer extends AbstractKafkaConsumer<CanalLogMessage> {

    @KafkaListener(topics = "canal")
    public void operationLogListener(String message, Acknowledgment acknowledgment) {
        try {
            CanalLogMessage canalLogMessage = super.decodeMessage(message, CanalLogMessage.class);
            LogOperationEnum logOperationEnum = LogOperationEnum.getByCode(canalLogMessage.getType());
            switch (logOperationEnum) {
                case INSERT -> executeInsert();
                case UPDATE -> executeUpdate();
                case DELETE -> executeDelete();
            }

            System.out.println("Received message: " + canalLogMessage);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            acknowledgment.acknowledge();
        }
    }

    private void executeInsert() {

    }

    private void executeUpdate() {

    }

    private void executeDelete() {

    }

}
