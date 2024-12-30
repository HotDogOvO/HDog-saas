package com.hotdog.saas.application.processor.education.clazz.schedule;

import com.hotdog.saas.application.assembler.EducationCourseClassAssembler;
import com.hotdog.saas.application.assembler.EducationCourseClassScheduleAssembler;
import com.hotdog.saas.application.entity.request.education.clazz.CreateEducationCourseClassRequest;
import com.hotdog.saas.application.entity.request.education.clazz.schedule.CreateEducationCourseClassScheduleRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourse;
import com.hotdog.saas.domain.model.EducationCourseClass;
import com.hotdog.saas.domain.model.EducationCourseClassSchedule;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseClassScheduleCreateProcessor extends AbstractEducationClassScheduleProcessor<CreateEducationCourseClassScheduleRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(CreateEducationCourseClassScheduleRequest request, BaseResponse<Boolean> response) {
        super.existsBetweenTime(request.getClassNo(), request.getClassBeginTime(), request.getClassEndTime());
        EducationCourseClassSchedule educationCourseClassSchedule = EducationCourseClassScheduleAssembler.INSTANCE.convert(request);
        Integer saveFlag = educationCourseClassScheduleRepository.save(educationCourseClassSchedule);
        response.setData(checkFlag(saveFlag));
    }

}
