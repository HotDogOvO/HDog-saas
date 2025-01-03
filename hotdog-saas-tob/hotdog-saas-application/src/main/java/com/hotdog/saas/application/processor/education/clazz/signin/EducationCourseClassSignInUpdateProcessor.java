package com.hotdog.saas.application.processor.education.clazz.signin;

import com.hotdog.saas.application.assembler.EducationCourseClassSignInAssembler;
import com.hotdog.saas.application.entity.request.education.clazz.signin.UpdateEducationCourseClassSignInRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourseClassSignIn;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseClassSignInUpdateProcessor extends AbstractEducationClassSignInProcessor<UpdateEducationCourseClassSignInRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(UpdateEducationCourseClassSignInRequest request, BaseResponse<Boolean> response) {
        Long id = request.getId();
        super.existsBySignInId(id);
        EducationCourseClassSignIn educationCourseClassSignIn = EducationCourseClassSignInAssembler.INSTANCE.convert(request);

        Integer modifyFlag = educationCourseClassSignInRepository.modify(educationCourseClassSignIn);
        response.setData(checkFlag(modifyFlag));
    }

}
