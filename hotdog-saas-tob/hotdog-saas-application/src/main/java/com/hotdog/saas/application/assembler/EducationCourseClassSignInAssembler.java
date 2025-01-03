package com.hotdog.saas.application.assembler;

import com.hotdog.saas.application.entity.request.education.clazz.signin.DeleteEducationCourseClassSignInRequest;
import com.hotdog.saas.application.entity.request.education.clazz.signin.EducationCourseClassSignInPageRequest;
import com.hotdog.saas.application.entity.request.education.clazz.signin.UpdateEducationCourseClassSignInRequest;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassSignInDTO;
import com.hotdog.saas.domain.model.EducationCourseClassSignIn;
import com.hotdog.saas.domain.model.page.PageResponse;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EducationCourseClassSignInAssembler {

    EducationCourseClassSignInAssembler INSTANCE = Mappers.getMapper(EducationCourseClassSignInAssembler.class);

    EducationCourseClassSignIn convert(EducationCourseClassSignInPageRequest educationCourseClassSignInPageRequest);

    EducationCourseClassSignIn convert(UpdateEducationCourseClassSignInRequest updateEducationCourseClassSignInRequest);

    EducationCourseClassSignIn convert(DeleteEducationCourseClassSignInRequest deleteEducationCourseClassSignInRequest);

    PageResponseDTO<EducationCourseClassSignInDTO> convertPage(PageResponse<List<EducationCourseClassSignIn>> pageResponse);

}
