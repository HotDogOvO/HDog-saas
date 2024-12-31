package com.hotdog.saas.application.processor.education.clazz.schedule;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.processor.education.clazz.AbstractEducationClassProcessor;
import com.hotdog.saas.application.template.BizProcessorTemplate;
import com.hotdog.saas.domain.constant.RedisConstants;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.utils.DateUtils;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class AbstractEducationClassScheduleProcessor<Req extends BaseRequestParam, Resp extends BaseResponse<?>> extends AbstractEducationClassProcessor<Req, Resp> implements BizProcessorTemplate<Req, Resp> {

    /**
     * 校验课程表是否存在
     *
     * @param id 课程表ID
     */
    protected void existsByClassScheduleId(Long id) {
        if (Objects.isNull(id)) {
            return;
        }
        Long nameCount = educationCourseClassScheduleRepository.exists(id);
        if (nameCount == 0) {
            throw new BusinessException(ResultCodeEnum.FAIL, "课程表不存在");
        }
    }

    /**
     * 校验时间段内是否有重复的课程表
     *
     * @param classNo   班级编号
     * @param startTime 课程开始时间
     * @param endTime   课程结束时间
     */
    protected void existsBetweenTime(String classNo, LocalDateTime startTime, LocalDateTime endTime) {
        if (Objects.isNull(startTime) || Objects.isNull(endTime)) {
            return;
        }
        Long nameCount = educationCourseClassScheduleRepository.existsBetweenTime(classNo, startTime, endTime);
        if (nameCount > 0) {
            throw new BusinessException(ResultCodeEnum.FAIL, "时间段内有重复的课程");
        }
    }

    /**
     * 任务放入课程队列
     *
     * @param id             课程表ID
     * @param classBeginTime 上课时间
     * @param classEndTime   下课时间
     */
    protected void pushCourseTaskDelayQueue(Long id, LocalDateTime classBeginTime, LocalDateTime classEndTime) {
        // 上课队列
        redisCacheService.zSet(RedisConstants.EDUCATION_COURSE_START_TASK_PREFIX, id, DateUtils.getTimestamp(classBeginTime));
        // 下课队列
        redisCacheService.zSet(RedisConstants.EDUCATION_COURSE_END_TASK_PREFIX, id, DateUtils.getTimestamp(classEndTime));
    }

    /**
     * 任务移出课程队列
     *
     * @param id 课程表ID
     */
    protected void removeCourseTaskDelayQueue(Long id) {
        // 上课队列
        redisCacheService.zDelete(RedisConstants.EDUCATION_COURSE_START_TASK_PREFIX, id);
        // 下课队列
        redisCacheService.zDelete(RedisConstants.EDUCATION_COURSE_END_TASK_PREFIX, id);
    }

}
