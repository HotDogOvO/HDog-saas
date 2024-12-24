package com.hotdog.saas.application.processor.education;

import com.hotdog.saas.application.assembler.EducationCourseAssembler;
import com.hotdog.saas.application.assembler.UserAssembler;
import com.hotdog.saas.application.entity.request.education.EducationCoursePageRequest;
import com.hotdog.saas.application.entity.request.user.UserPageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseDTO;
import com.hotdog.saas.application.entity.response.user.UserDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourse;
import com.hotdog.saas.domain.model.Role;
import com.hotdog.saas.domain.model.User;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;

import org.springframework.stereotype.Component;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationListProcessor extends AbstractEducationProcessor<EducationCoursePageRequest, BaseResponse<PageResponseDTO<EducationCourseDTO>>> {

    @Override
    public BaseResponse<PageResponseDTO<EducationCourseDTO>> initResult() {
        BaseResponse<PageResponseDTO<EducationCourseDTO>> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(EducationCoursePageRequest request, BaseResponse<PageResponseDTO<EducationCourseDTO>> response) {
        request.initPage();
        EducationCourse educationCourse = EducationCourseAssembler.INSTANCE.convert(request);
        PageRequest pageRequest = reqToPage(request);

        PageResponse<List<EducationCourse>> listPageResponse = educationCourseRepository.listPage(educationCourse, pageRequest);

        PageResponseDTO<EducationCourseDTO> educationCoursePageResponseDTO = EducationCourseAssembler.INSTANCE.convertPage(listPageResponse);

        response.setData(educationCoursePageResponseDTO);
    }

}
