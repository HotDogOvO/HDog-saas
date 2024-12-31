package com.hotdog.saas.application.processor.education;

import com.hotdog.saas.application.assembler.EducationCourseAssembler;
import com.hotdog.saas.application.entity.request.education.EducationCourseOptionsRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.education.EducationCourseOptionsDTO;
import com.hotdog.saas.application.processor.education.type.AbstractEducationTypeProcessor;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourse;

import org.springframework.stereotype.Component;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseOptionsProcessor extends AbstractEducationTypeProcessor<EducationCourseOptionsRequest, BaseResponse<List<EducationCourseOptionsDTO>>> {

    @Override
    public BaseResponse<List<EducationCourseOptionsDTO>> initResult() {
        BaseResponse<List<EducationCourseOptionsDTO>> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(EducationCourseOptionsRequest request, BaseResponse<List<EducationCourseOptionsDTO>> response) {
        super.existsByWechatId(request.getWechatId());
        EducationCourse educationCourse = EducationCourseAssembler.INSTANCE.convert(request);

        List<EducationCourse> list = educationCourseRepository.list(educationCourse);
        List<EducationCourseOptionsDTO> educationCourseOptionsList = EducationCourseAssembler.INSTANCE.convertOptions(list);

        response.setData(educationCourseOptionsList);
    }

}
