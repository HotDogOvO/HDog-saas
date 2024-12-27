package com.hotdog.saas.infra.foundation.task.education;

import com.alibaba.fastjson2.JSONObject;
import com.hotdog.saas.domain.constant.KafkaConstants;
import com.hotdog.saas.domain.enums.education.CourseClassStatusEnum;
import com.hotdog.saas.domain.enums.kafka.KafkaDeadMessageStatusEnum;
import com.hotdog.saas.domain.foundation.KafkaProducer;
import com.hotdog.saas.domain.model.EducationCourseClass;
import com.hotdog.saas.domain.model.KafkaDeadMessage;
import com.hotdog.saas.domain.repository.EducationCourseClassRepository;
import com.hotdog.saas.domain.repository.KafkaDeadMessageRepository;
import com.hotdog.saas.domain.utils.ConcurrentUtils;
import com.hotdog.saas.domain.utils.DateUtils;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * 教育类 - 班级开班定时任务
 * @author hotdog
 * @date 2024/12/27 17:39
 */
@Slf4j
@Component
public class EducationClassStartTask {

    private final KafkaProducer<String> kafkaProducer;
    private final EducationCourseClassRepository educationCourseClassRepository;

    public EducationClassStartTask(KafkaProducer<String> kafkaProducer, EducationCourseClassRepository educationCourseClassRepository) {
        this.kafkaProducer = kafkaProducer;
        this.educationCourseClassRepository = educationCourseClassRepository;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void educationClassStartTask() {
        LocalDateTime now = DateUtils.now();
        List<EducationCourseClass> educationCourseClassList = educationCourseClassRepository.findBeforeStartTimeAndStatus(now, CourseClassStatusEnum.WAITING_START);
        // 异步执行消息重试
        ConcurrentUtils.async(() -> educationCourseClassList.forEach(this::pushToKafka));
    }

    private void pushToKafka(EducationCourseClass educationCourseClass) {
        try {
            String jsonString = JSONObject.toJSONString(educationCourseClass);
            kafkaProducer.publish(KafkaConstants.EDUCATION_COURSE_CLASS_START_TOPIC, jsonString);
        } catch (Exception e) {
            log.error("班级开班定时任务异常，classNo: {}, 原因是：{}", educationCourseClass.getClassNo(), e.getMessage(), e);
        }
    }
}
