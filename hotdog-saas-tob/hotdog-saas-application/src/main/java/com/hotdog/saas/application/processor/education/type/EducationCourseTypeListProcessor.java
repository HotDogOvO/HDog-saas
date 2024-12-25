package com.hotdog.saas.application.processor.education.type;

import com.hotdog.saas.application.assembler.EducationCourseAssembler;
import com.hotdog.saas.application.assembler.EducationCourseTypeAssembler;
import com.hotdog.saas.application.entity.request.education.EducationCoursePageRequest;
import com.hotdog.saas.application.entity.request.education.type.EducationCourseTypeListRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseTypeDTO;
import com.hotdog.saas.application.processor.education.AbstractEducationProcessor;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourse;
import com.hotdog.saas.domain.model.EducationCourseType;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;

import org.springframework.stereotype.Component;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseTypeListProcessor extends AbstractEducationTypeProcessor<EducationCourseTypeListRequest, BaseResponse<List<EducationCourseTypeDTO>>> {

    @Override
    public BaseResponse<List<EducationCourseTypeDTO>> initResult() {
        BaseResponse<List<EducationCourseTypeDTO>> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(EducationCourseTypeListRequest request, BaseResponse<List<EducationCourseTypeDTO>> response) {
        EducationCourseType educationCourseType = EducationCourseTypeAssembler.INSTANCE.convert(request);

        List<EducationCourseType> list = educationCourseTypeRepository.list(educationCourseType);
        List<EducationCourseTypeDTO> educationCourseDTOList = EducationCourseTypeAssembler.INSTANCE.convertList(list);

        response.setData(educationCourseDTOList);
    }

}
