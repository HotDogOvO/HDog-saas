package com.hotdog.saas.application.processor.education.clazz.signin;

import com.hotdog.saas.application.entity.request.education.clazz.signin.DeleteEducationCourseClassSignInRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.enums.ResultCodeEnum;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseClassSignInDeleteProcessor extends AbstractEducationClassSignInProcessor<DeleteEducationCourseClassSignInRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(DeleteEducationCourseClassSignInRequest request, BaseResponse<Boolean> response) {
        Long id = request.getId();
        super.existsBySignInId(id);
        Integer modifyFlag = educationCourseClassSignInRepository.remove(id, request.getOperator());
        response.setData(checkFlag(modifyFlag));
    }

}
