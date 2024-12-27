package com.hotdog.saas.application.processor.education.clazz;

import com.hotdog.saas.application.assembler.EducationCourseAssembler;
import com.hotdog.saas.application.assembler.EducationCourseClassAssembler;
import com.hotdog.saas.application.entity.request.education.EducationCoursePageRequest;
import com.hotdog.saas.application.entity.request.education.clazz.EducationCourseClassPageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourse;
import com.hotdog.saas.domain.model.EducationCourseClass;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;

import org.springframework.stereotype.Component;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseClassListProcessor extends AbstractEducationClassProcessor<EducationCourseClassPageRequest, BaseResponse<PageResponseDTO<EducationCourseClassDTO>>> {

    @Override
    public BaseResponse<PageResponseDTO<EducationCourseClassDTO>> initResult() {
        BaseResponse<PageResponseDTO<EducationCourseClassDTO>> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(EducationCourseClassPageRequest request, BaseResponse<PageResponseDTO<EducationCourseClassDTO>> response) {
        request.initPage();
        EducationCourseClass educationCourseClass = EducationCourseClassAssembler.INSTANCE.convert(request);
        PageRequest pageRequest = reqToPage(request);

        PageResponse<List<EducationCourseClass>> listPageResponse = educationCourseClassRepository.listPage(educationCourseClass, pageRequest);
        List<EducationCourseClassDTO> list = listPageResponse.getData().stream().map(super::buildEducationCourseClassDTO).toList();
        PageResponseDTO<EducationCourseClassDTO> educationCourseClassPageResponseDTO = EducationCourseClassAssembler.INSTANCE.convertPage(listPageResponse);
        educationCourseClassPageResponseDTO.setData(list);

        response.setData(educationCourseClassPageResponseDTO);
    }

}
