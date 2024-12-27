package com.hotdog.saas.application.processor.education.type;

import com.hotdog.saas.application.assembler.EducationCourseTypeAssembler;
import com.hotdog.saas.application.entity.request.education.type.EducationCourseTypePageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseTypeDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourseType;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;

import org.springframework.stereotype.Component;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseTypeListPageProcessor extends AbstractEducationTypeProcessor<EducationCourseTypePageRequest, BaseResponse<PageResponseDTO<EducationCourseTypeDTO>>> {

    @Override
    public BaseResponse<PageResponseDTO<EducationCourseTypeDTO>> initResult() {
        BaseResponse<PageResponseDTO<EducationCourseTypeDTO>> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(EducationCourseTypePageRequest request, BaseResponse<PageResponseDTO<EducationCourseTypeDTO>> response) {
        request.initPage();
        EducationCourseType educationCourseType = EducationCourseTypeAssembler.INSTANCE.convert(request);
        PageRequest pageRequest = reqToPage(request);

        PageResponse<List<EducationCourseType>> listPageResponse = educationCourseTypeRepository.listPage(educationCourseType, pageRequest);

        PageResponseDTO<EducationCourseTypeDTO> educationCourseTypePageResponseDTO = EducationCourseTypeAssembler.INSTANCE.convertPage(listPageResponse);

        response.setData(educationCourseTypePageResponseDTO);
    }

}
