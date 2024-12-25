package com.hotdog.saas.application.processor.education.type;

import com.hotdog.saas.application.entity.request.education.DeleteEducationCourseRequest;
import com.hotdog.saas.application.entity.request.education.type.DeleteEducationCourseTypeRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.processor.education.AbstractEducationProcessor;
import com.hotdog.saas.domain.enums.ResultCodeEnum;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseTypeDeleteProcessor extends AbstractEducationTypeProcessor<DeleteEducationCourseTypeRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(DeleteEducationCourseTypeRequest request, BaseResponse<Boolean> response) {
        Long typeId = request.getId();
        super.existsByCourseTypeId(typeId);
        Integer removeFlag = educationCourseTypeRepository.remove(typeId, request.getOperator());
        response.setData(checkFlag(removeFlag));
    }

}
