package com.hotdog.saas.infra.foundation.kafka.consumer;

import com.hotdog.saas.domain.constant.Constants;
import com.hotdog.saas.domain.constant.KafkaConstants;
import com.hotdog.saas.domain.enums.education.CourseClassStatusEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.model.EducationCourseClass;
import com.hotdog.saas.domain.model.message.EducationCourseClassMessage;
import com.hotdog.saas.domain.repository.EducationCourseClassRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationClassStartConsumer extends AbstractKafkaConsumer<EducationCourseClassMessage> {

    private final EducationCourseClassRepository educationCourseClassRepository;

    public EducationClassStartConsumer(EducationCourseClassRepository educationCourseClassRepository) {
        this.educationCourseClassRepository = educationCourseClassRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    @KafkaListener(topics = KafkaConstants.EDUCATION_COURSE_CLASS_START_TOPIC)
    public void educationClassStartListener(String message, Acknowledgment acknowledgment) {
        try {
            log.debug("监听班级开班-kafka消息，请求原串：{}", message);
            EducationCourseClassMessage educationCourseClassMessage = super.decodeMessage(message, EducationCourseClassMessage.class);

            log.info("监听班级开班-kafka消息，解析原串：{}", educationCourseClassMessage);
            EducationCourseClass educationCourseClass = EducationCourseClass.builder()
                    .classNo(educationCourseClassMessage.getClassNo())
                    .status(CourseClassStatusEnum.STARTING.getCode())
                    .operator(Constants.SYSTEM_OPERATOR)
                    .build();
            educationCourseClassRepository.modify(educationCourseClass);

            // 如果是补偿任务，需要更新补偿任务表的状态
            super.checkRetryTask(educationCourseClassMessage);

            log.debug("监听班级开班-kafka消息，开班成功，更新记录：{}", educationCourseClass);
        } catch (Exception e) {
            log.error("监听班级开班-kafka消息，开班异常：{}", e.getMessage(), e);
            throw new BusinessException("kafka 失败");
        } finally {
            acknowledgment.acknowledge();
        }
    }

}
