package com.hotdog.saas.application.processor.education.type;

import com.hotdog.saas.application.assembler.EducationCourseAssembler;
import com.hotdog.saas.application.assembler.EducationCourseTypeAssembler;
import com.hotdog.saas.application.entity.request.education.UpdateEducationCourseRequest;
import com.hotdog.saas.application.entity.request.education.type.UpdateEducationCourseTypeRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.processor.education.AbstractEducationProcessor;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourse;
import com.hotdog.saas.domain.model.EducationCourseType;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseTypeUpdateProcessor extends AbstractEducationTypeProcessor<UpdateEducationCourseTypeRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(UpdateEducationCourseTypeRequest request, BaseResponse<Boolean> response) {
        super.existsByCourseTypeId(request.getId());
        EducationCourseType educationCourseType = EducationCourseTypeAssembler.INSTANCE.convert(request);
        Integer modifyFlag = educationCourseTypeRepository.modify(educationCourseType);
        response.setData(checkFlag(modifyFlag));
    }

}
