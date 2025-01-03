package com.hotdog.saas.test.infra.task;

import com.hotdog.saas.Application;
import com.hotdog.saas.infra.foundation.task.KafkaCompensateTask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class TaskTest {

    @Autowired
    private KafkaCompensateTask kafkaCompensateTask;

    @Test
    public void kafkaCompensateTaskTest() throws InterruptedException {
        kafkaCompensateTask.kafkaCompensateTask();

        Thread.sleep(10000);
    }
}
