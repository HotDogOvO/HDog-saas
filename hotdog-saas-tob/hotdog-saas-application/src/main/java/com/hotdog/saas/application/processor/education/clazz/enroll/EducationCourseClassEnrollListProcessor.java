package com.hotdog.saas.application.processor.education.clazz.enroll;

import com.hotdog.saas.application.assembler.EducationCourseClassEnrollAssembler;
import com.hotdog.saas.application.entity.request.education.clazz.enroll.EducationCourseClassEnrollPageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassEnrollDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourseClassEnroll;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;

import org.springframework.stereotype.Component;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseClassEnrollListProcessor extends AbstractEducationClassEnrollProcessor<EducationCourseClassEnrollPageRequest, BaseResponse<PageResponseDTO<EducationCourseClassEnrollDTO>>> {

    @Override
    public BaseResponse<PageResponseDTO<EducationCourseClassEnrollDTO>> initResult() {
        BaseResponse<PageResponseDTO<EducationCourseClassEnrollDTO>> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(EducationCourseClassEnrollPageRequest request, BaseResponse<PageResponseDTO<EducationCourseClassEnrollDTO>> response) {
        request.initPage();

        EducationCourseClassEnroll educationCourseClassEnroll = EducationCourseClassEnrollAssembler.INSTANCE.convert(request);
        PageRequest pageRequest = reqToPage(request);

        PageResponse<List<EducationCourseClassEnroll>> listPageResponse = educationCourseClassEnrollRepository.listPage(educationCourseClassEnroll, pageRequest);

        PageResponseDTO<EducationCourseClassEnrollDTO> list = EducationCourseClassEnrollAssembler.INSTANCE.convertPage(listPageResponse);

        response.setData(list);
    }

}
