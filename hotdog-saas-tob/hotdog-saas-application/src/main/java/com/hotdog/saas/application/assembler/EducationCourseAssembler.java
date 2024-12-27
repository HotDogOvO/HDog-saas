package com.hotdog.saas.application.assembler;

import com.hotdog.saas.application.entity.request.education.CreateEducationCourseRequest;
import com.hotdog.saas.application.entity.request.education.EducationCourseOptionsRequest;
import com.hotdog.saas.application.entity.request.education.EducationCoursePageRequest;
import com.hotdog.saas.application.entity.request.education.UpdateEducationCourseRequest;
import com.hotdog.saas.application.entity.request.user.CreateUserRequest;
import com.hotdog.saas.application.entity.request.user.UpdateUserRequest;
import com.hotdog.saas.application.entity.request.user.UserPageRequest;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseOptionsDTO;
import com.hotdog.saas.application.entity.response.user.UserDTO;
import com.hotdog.saas.domain.model.EducationCourse;
import com.hotdog.saas.domain.model.User;
import com.hotdog.saas.domain.model.page.PageResponse;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EducationCourseAssembler {

    EducationCourseAssembler INSTANCE = Mappers.getMapper(EducationCourseAssembler.class);

    EducationCourse convert(CreateEducationCourseRequest createEducationCourseRequest);

    EducationCourse convert(EducationCourseOptionsRequest educationCourseOptionsRequest);

    EducationCourse convert(EducationCoursePageRequest educationCoursePageRequest);

    EducationCourse convert(UpdateEducationCourseRequest updateEducationCourseRequest);

    PageResponseDTO<EducationCourseDTO> convertPage(PageResponse<List<EducationCourse>> pageResponse);

    List<EducationCourseOptionsDTO> convertOptions(List<EducationCourse> list);

    EducationCourseDTO convertToDTO(EducationCourse educationCourse);
}
