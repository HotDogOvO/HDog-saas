package com.hotdog.saas.application.processor.education;

import com.hotdog.saas.application.assembler.EducationCourseAssembler;
import com.hotdog.saas.application.entity.request.education.UpdateEducationCourseRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourse;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseUpdateProcessor extends AbstractEducationProcessor<UpdateEducationCourseRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(UpdateEducationCourseRequest request, BaseResponse<Boolean> response) {
        super.exists(request.getCourseNo());
        // 保存
        EducationCourse educationCourse = EducationCourseAssembler.INSTANCE.convert(request);
        educationCourse.setTenantId(getTenantId());
        Integer modifyFlag = educationCourseRepository.modify(educationCourse);
        response.setData(checkFlag(modifyFlag));
    }

}
