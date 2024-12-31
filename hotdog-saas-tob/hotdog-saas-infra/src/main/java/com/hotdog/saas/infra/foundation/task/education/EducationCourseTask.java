package com.hotdog.saas.infra.foundation.task.education;

import com.alibaba.fastjson2.JSONObject;
import com.hotdog.saas.domain.constant.Constants;
import com.hotdog.saas.domain.constant.KafkaConstants;
import com.hotdog.saas.domain.constant.RedisConstants;
import com.hotdog.saas.domain.foundation.KafkaProducer;
import com.hotdog.saas.domain.foundation.RedisCacheService;
import com.hotdog.saas.domain.model.message.EducationCourseMessage;
import com.hotdog.saas.domain.utils.ConcurrentUtils;
import com.hotdog.saas.domain.utils.DateUtils;

import org.redisson.api.RLock;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * 教育类 - 课程定时任务（上课 & 下课）
 *
 * @author hotdog
 * @date 2024/12/31 11:21
 */
@Slf4j
@Component
public class EducationCourseTask {

    private final KafkaProducer<String> kafkaProducer;
    private final RedisCacheService redisCacheService;

    public EducationCourseTask(KafkaProducer<String> kafkaProducer, RedisCacheService redisCacheService) {
        this.kafkaProducer = kafkaProducer;
        this.redisCacheService = redisCacheService;
    }

    /**
     * 课程（上课 & 下课）任务，每分钟执行
     * 1. 从redis的zset中读取集合
     * 2. 将当前时间前的score过滤
     * 3. 发送kafka，由消费者进行操作
     */
//    @Scheduled(cron = "0 * * * * ?")
    public void educationClassStartTask() {
        RLock lock = redisCacheService.getLock(RedisConstants.EDUCATION_COURSE_TASK_LOCK_KEY);
        Long now = DateUtils.getTimestamp();
        try {
            lock.lock(Constants.LOCK_TIME_SECONDS, TimeUnit.SECONDS);
            List<Long> startScheduleIdList = redisCacheService.zGetRange(RedisConstants.EDUCATION_COURSE_START_TASK_PREFIX, 0L, now);
            List<Long> endScheduleIdList = redisCacheService.zGetRange(RedisConstants.EDUCATION_COURSE_END_TASK_PREFIX, 0L, now);
            ConcurrentUtils.async(() -> pushToKafka(startScheduleIdList, endScheduleIdList, now));
        } catch (Exception e) {
            log.error("课程定时任务异常，当前时间：{}, 原因是：{}", now, e.getMessage(), e);
        } finally {
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
    }

    private void pushToKafka(List<Long> startScheduleIdList, List<Long> endScheduleIdList, Long nowTimestamp) {
        EducationCourseMessage educationCourseStartMessage = EducationCourseMessage.builder()
                .startScheduleIdList(startScheduleIdList)
                .endScheduleIdList(endScheduleIdList)
                .timestamp(nowTimestamp)
                .build();
        String message = JSONObject.toJSONString(educationCourseStartMessage);
        kafkaProducer.publish(KafkaConstants.EDUCATION_COURSE_TASK_TOPIC, message);
    }
}
