package com.hotdog.saas.application.processor.education.type;

import com.hotdog.saas.application.assembler.EducationCourseTypeAssembler;
import com.hotdog.saas.application.entity.request.education.type.EducationCourseTypeOptionsRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.education.EducationCourseTypeOptionsDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourseType;

import org.springframework.stereotype.Component;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseTypeOptionsProcessor extends AbstractEducationTypeProcessor<EducationCourseTypeOptionsRequest, BaseResponse<List<EducationCourseTypeOptionsDTO>>> {

    @Override
    public BaseResponse<List<EducationCourseTypeOptionsDTO>> initResult() {
        BaseResponse<List<EducationCourseTypeOptionsDTO>> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(EducationCourseTypeOptionsRequest request, BaseResponse<List<EducationCourseTypeOptionsDTO>> response) {
        super.existsByWechatId(request.getWechatId());
        EducationCourseType educationCourseType = EducationCourseTypeAssembler.INSTANCE.convert(request);

        List<EducationCourseType> list = educationCourseTypeRepository.list(educationCourseType);
        List<EducationCourseTypeOptionsDTO> educationCourseTypeOptionsList = EducationCourseTypeAssembler.INSTANCE.convertOptions(list);

        response.setData(educationCourseTypeOptionsList);
    }

}
