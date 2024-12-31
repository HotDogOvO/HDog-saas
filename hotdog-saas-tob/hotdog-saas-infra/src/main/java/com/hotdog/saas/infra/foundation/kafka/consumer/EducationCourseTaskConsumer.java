package com.hotdog.saas.infra.foundation.kafka.consumer;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.hotdog.saas.domain.constant.Constants;
import com.hotdog.saas.domain.constant.KafkaConstants;
import com.hotdog.saas.domain.constant.RedisConstants;
import com.hotdog.saas.domain.enums.education.CourseClassScheduleStatusEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.foundation.RedisCacheService;
import com.hotdog.saas.domain.model.EducationCourseClassRecord;
import com.hotdog.saas.domain.model.EducationCourseClassSchedule;
import com.hotdog.saas.domain.model.message.EducationCourseMessage;
import com.hotdog.saas.domain.repository.EducationCourseClassRecordRepository;
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
    private final EducationCourseClassRecordRepository educationCourseClassRecordRepository;
    private final RedisCacheService redisCacheService;

    public EducationCourseTaskConsumer(EducationCourseClassScheduleRepository educationCourseClassScheduleRepository, EducationCourseClassRecordRepository educationCourseClassRecordRepository, RedisCacheService redisCacheService) {
        this.educationCourseClassScheduleRepository = educationCourseClassScheduleRepository;
        this.educationCourseClassRecordRepository = educationCourseClassRecordRepository;
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

    /**
     * 课程上课任务
     *
     * @param startScheduleIdList 上课任务ID
     */
    private void execStart(List<Long> startScheduleIdList) {
        if (CollectionUtils.isEmpty(startScheduleIdList)) {
            return;
        }
        educationCourseClassScheduleRepository.modifyStatusInIdList(startScheduleIdList, CourseClassScheduleStatusEnum.STARTING);
    }

    /**
     * 课程下课任务
     *
     * @param endScheduleIdList 下课任务ID
     */
    private void execEnd(List<Long> endScheduleIdList) {
        if (CollectionUtils.isEmpty(endScheduleIdList)) {
            return;
        }
        // 修改课程状态
        educationCourseClassScheduleRepository.modifyStatusInIdList(endScheduleIdList, CourseClassScheduleStatusEnum.FINISH);

        // 构建授课记录
        List<EducationCourseClassSchedule> scheduleList = educationCourseClassScheduleRepository.findInIdList(endScheduleIdList);
        List<EducationCourseClassRecord> recordList = scheduleList.stream().map(scheduleClass -> EducationCourseClassRecord.builder()
                .classNo(scheduleClass.getClassNo())
                .classBeginTime(scheduleClass.getClassBeginTime())
                .classEndTime(scheduleClass.getClassEndTime())
                .teacher("teacher") // todo 课程签到
                .studentCount(0) // todo 课程签到
                .operator(Constants.SYSTEM_OPERATOR)
                .build()).toList();

        educationCourseClassRecordRepository.batchSave(recordList);
    }

    /**
     * 移除课程表监听任务
     *
     * @param timestamp 任务时间戳
     */
    private void removeTask(Long timestamp) {
        redisCacheService.zDeleteRange(RedisConstants.EDUCATION_COURSE_START_TASK_PREFIX, 0L, timestamp);
        redisCacheService.zDeleteRange(RedisConstants.EDUCATION_COURSE_END_TASK_PREFIX, 0L, timestamp);
    }
}
