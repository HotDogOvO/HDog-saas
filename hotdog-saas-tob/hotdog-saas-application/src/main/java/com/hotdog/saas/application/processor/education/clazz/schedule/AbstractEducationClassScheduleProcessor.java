package com.hotdog.saas.application.processor.education.clazz.schedule;

import com.hotdog.saas.application.assembler.EducationCourseClassAssembler;
import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassDTO;
import com.hotdog.saas.application.processor.education.AbstractEducationProcessor;
import com.hotdog.saas.application.template.BizProcessorTemplate;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.model.EducationCourse;
import com.hotdog.saas.domain.model.EducationCourseClass;

import java.time.LocalDateTime;
import java.util.Objects;

import io.micrometer.common.util.StringUtils;

public abstract class AbstractEducationClassScheduleProcessor<Req extends BaseRequestParam, Resp extends BaseResponse<?>> extends AbstractEducationProcessor<Req, Resp> implements BizProcessorTemplate<Req, Resp> {

    /**
     * 校验课程表是否存在
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

}
