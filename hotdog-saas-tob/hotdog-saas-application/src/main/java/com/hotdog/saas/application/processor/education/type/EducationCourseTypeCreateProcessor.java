package com.hotdog.saas.application.processor.education.type;

import com.hotdog.saas.application.assembler.EducationCourseAssembler;
import com.hotdog.saas.application.assembler.EducationCourseTypeAssembler;
import com.hotdog.saas.application.entity.request.education.CreateEducationCourseRequest;
import com.hotdog.saas.application.entity.request.education.type.CreateEducationCourseTypeRequest;
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
public class EducationCourseTypeCreateProcessor extends AbstractEducationTypeProcessor<CreateEducationCourseTypeRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(CreateEducationCourseTypeRequest request, BaseResponse<Boolean> response) {
        Long wechatId = request.getWechatId();
        valid(wechatId, request.getName());
        EducationCourseType educationCourseType = EducationCourseTypeAssembler.INSTANCE.convert(request);
        Integer saveFlag = educationCourseTypeRepository.save(educationCourseType);
        response.setData(checkFlag(saveFlag));
    }

    private void valid(Long wechatId, String typeName) {
        super.existsByWechatId(wechatId);
        super.existsByTypeName(typeName, wechatId);
    }
}
