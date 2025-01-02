package com.hotdog.saas.application.assembler;

import com.hotdog.saas.application.entity.request.education.clazz.trail.AssignEducationCourseClassTrailRequest;
import com.hotdog.saas.application.entity.request.education.clazz.trail.DeleteEducationCourseClassTrailRequest;
import com.hotdog.saas.application.entity.request.education.clazz.trail.EducationCourseClassTrailPageRequest;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassTrailDTO;
import com.hotdog.saas.domain.model.EducationCourseClassTrail;
import com.hotdog.saas.domain.model.page.PageResponse;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EducationCourseClassTrailAssembler {

    EducationCourseClassTrailAssembler INSTANCE = Mappers.getMapper(EducationCourseClassTrailAssembler.class);

    EducationCourseClassTrail convert(EducationCourseClassTrailPageRequest educationCourseClassTrailPageRequest);

    EducationCourseClassTrail convert(AssignEducationCourseClassTrailRequest assignEducationCourseClassTrailRequest);

    EducationCourseClassTrail convert(DeleteEducationCourseClassTrailRequest deleteEducationCourseClassTrailRequest);

    PageResponseDTO<EducationCourseClassTrailDTO> convertPage(PageResponse<List<EducationCourseClassTrail>> pageResponse);

}
