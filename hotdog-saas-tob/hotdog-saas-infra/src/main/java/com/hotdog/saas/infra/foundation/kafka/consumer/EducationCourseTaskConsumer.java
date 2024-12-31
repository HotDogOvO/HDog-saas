package com.hotdog.saas.infra.foundation.kafka.consumer;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.hotdog.saas.domain.constant.KafkaConstants;
import com.hotdog.saas.domain.constant.RedisConstants;
import com.hotdog.saas.domain.enums.education.CourseClassScheduleStatusEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.foundation.RedisCacheService;
import com.hotdog.saas.domain.model.message.EducationCourseMessage;
import com.hotdog.saas.domain.repository.EducationCourseClassScheduleRepository;
import com.hotdog.saas.domain.utils.ConcurrentUtils;

import org.apache.commons.compress.utils.Lists;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseTaskConsumer extends AbstractKafkaConsumer<List<Long>> {

    private final EducationCourseClassScheduleRepository educationCourseClassScheduleRepository;
    private final RedisCacheService redisCacheService;

    public EducationCourseTaskConsumer(EducationCourseClassScheduleRepository educationCourseClassScheduleRepository, RedisCacheService redisCacheService) {
        this.educationCourseClassScheduleRepository = educationCourseClassScheduleRepository;
        this.redisCacheService = redisCacheService;
    }

    @Transactional(rollbackFor = Exception.class)
    @KafkaListener(topics = KafkaConstants.EDUCATION_COURSE_TASK_TOPIC)
    public void educationCourseTaskListener(String message, Acknowledgment acknowledgment) {
        try {
            log.debug("监听课程定时任务-kafka消息，请求原串：{}", message);

            EducationCourseMessage educationCourseStartMessage = JSON.parseObject(message, EducationCourseMessage.class);
            log.info("监听课程定时任务-kafka消息，解析原串：{}", educationCourseStartMessage);

            List<Long> startScheduleIdList = educationCourseStartMessage.getStartScheduleIdList();
            List<Long> endScheduleIdList = educationCourseStartMessage.getEndScheduleIdList();

            // 如果课程同时上课和下课，则跳过上课状态
            startScheduleIdList.removeAll(endScheduleIdList);

            // 异步执行课程修改
            List<Runnable> tasks = Lists.newArrayList();
            tasks.add(() -> execStart(startScheduleIdList));
            tasks.add(() -> execEnd(endScheduleIdList));
            tasks.add(() -> removeTask(educationCourseStartMessage.getTimestamp()));
            ConcurrentUtils.asyncRunJoin(tasks);

            // 如果是补偿任务，需要更新补偿任务表的状态
            super.checkRetryTask(educationCourseStartMessage);

            log.debug("监听课程定时任务-kafka消息，开班成功，上课ID集合：{}, 下课ID集合：{}", startScheduleIdList, endScheduleIdList);
        } catch (Exception e) {
            log.error("监听课程定时任务-kafka消息，开班异常：{}", e.getMessage(), e);
            throw new BusinessException("kafka 失败");
        } finally {
            acknowledgment.acknowledge();
        }
    }

    private void execStart(List<Long> startScheduleIdList){
        if(CollectionUtils.isEmpty(startScheduleIdList)){
            return;
        }
        educationCourseClassScheduleRepository.modifyStatusInIdList(startScheduleIdList, CourseClassScheduleStatusEnum.STARTING);
    }

    private void execEnd(List<Long> endScheduleIdList){
        if(CollectionUtils.isEmpty(endScheduleIdList)){
            return;
        }
        educationCourseClassScheduleRepository.modifyStatusInIdList(endScheduleIdList, CourseClassScheduleStatusEnum.FINISH);
    }

    private void removeTask(Long timestamp){
        redisCacheService.zDeleteRange(RedisConstants.EDUCATION_COURSE_START_TASK_PREFIX, 0L, timestamp);
        redisCacheService.zDeleteRange(RedisConstants.EDUCATION_COURSE_END_TASK_PREFIX, 0L, timestamp);
    }
}
