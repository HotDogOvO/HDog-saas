package com.hotdog.saas.application.processor.education.clazz.person;

import com.hotdog.saas.application.entity.request.education.clazz.person.DeleteEducationCourseClassPersonRequest;
import com.hotdog.saas.application.entity.request.education.clazz.schedule.DeleteEducationCourseClassScheduleRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.enums.ResultCodeEnum;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseClassPersonDeleteProcessor extends AbstractEducationClassPersonProcessor<DeleteEducationCourseClassPersonRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(DeleteEducationCourseClassPersonRequest request, BaseResponse<Boolean> response) {
        Integer removeFlag = educationCourseClassPersonRepository.remove(request.getId(), request.getOperator());
        response.setData(checkFlag(removeFlag));
    }

}
