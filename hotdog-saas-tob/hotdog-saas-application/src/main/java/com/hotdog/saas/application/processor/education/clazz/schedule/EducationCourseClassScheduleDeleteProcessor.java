package com.hotdog.saas.application.processor.education.clazz.schedule;

import com.hotdog.saas.application.entity.request.education.clazz.schedule.DeleteEducationCourseClassScheduleRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.enums.ResultCodeEnum;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseClassScheduleDeleteProcessor extends AbstractEducationClassScheduleProcessor<DeleteEducationCourseClassScheduleRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(DeleteEducationCourseClassScheduleRequest request, BaseResponse<Boolean> response) {
        Long id = request.getId();
        super.existsByClassScheduleId(id);
        Integer removeFlag = educationCourseClassScheduleRepository.remove(id, request.getOperator());

        super.removeCourseTaskDelayQueue(id);
        response.setData(checkFlag(removeFlag));
    }

}
