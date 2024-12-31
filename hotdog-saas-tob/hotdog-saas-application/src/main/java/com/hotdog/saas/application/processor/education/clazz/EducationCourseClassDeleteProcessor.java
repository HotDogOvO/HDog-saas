package com.hotdog.saas.application.processor.education.clazz;

import com.hotdog.saas.application.entity.request.education.clazz.DeleteEducationCourseClassRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.enums.ResultCodeEnum;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseClassDeleteProcessor extends AbstractEducationClassProcessor<DeleteEducationCourseClassRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(DeleteEducationCourseClassRequest request, BaseResponse<Boolean> response) {
        super.existsByClassNo(request.getClassNo());
        Integer removeFlag = educationCourseClassRepository.remove(request.getClassNo(), request.getOperator());
        response.setData(checkFlag(removeFlag));
    }

}
