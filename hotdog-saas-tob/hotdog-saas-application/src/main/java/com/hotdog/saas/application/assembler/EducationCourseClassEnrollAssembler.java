package com.hotdog.saas.application.assembler;

import com.hotdog.saas.application.entity.request.education.clazz.enroll.DeleteEducationCourseClassEnrollRequest;
import com.hotdog.saas.application.entity.request.education.clazz.enroll.EducationCourseClassEnrollPageRequest;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassEnrollDTO;
import com.hotdog.saas.domain.model.EducationCourseClassEnroll;
import com.hotdog.saas.domain.model.page.PageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface EducationCourseClassEnrollAssembler {

    EducationCourseClassEnrollAssembler INSTANCE = Mappers.getMapper(EducationCourseClassEnrollAssembler.class);

    EducationCourseClassEnroll convert(EducationCourseClassEnrollPageRequest educationCourseClassEnrollPageRequest);

    EducationCourseClassEnroll convert(DeleteEducationCourseClassEnrollRequest deleteEducationCourseClassEnrollRequest);

    PageResponseDTO<EducationCourseClassEnrollDTO> convertPage(PageResponse<List<EducationCourseClassEnroll>> pageResponse);
}
