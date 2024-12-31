package com.hotdog.saas.application.assembler;

import com.hotdog.saas.application.entity.request.education.clazz.person.CreateEducationCourseClassPersonRequest;
import com.hotdog.saas.application.entity.request.education.clazz.person.EducationCourseClassPersonPageRequest;
import com.hotdog.saas.application.entity.request.education.clazz.person.UpdateEducationCourseClassPersonRequest;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassPersonDTO;
import com.hotdog.saas.domain.model.EducationCourseClassPerson;
import com.hotdog.saas.domain.model.page.PageResponse;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EducationCourseClassPersonAssembler {

    EducationCourseClassPersonAssembler INSTANCE = Mappers.getMapper(EducationCourseClassPersonAssembler.class);

    EducationCourseClassPerson convert(CreateEducationCourseClassPersonRequest createEducationCourseClassPersonRequest);

    EducationCourseClassPerson convert(EducationCourseClassPersonPageRequest EducationCourseClassPersonPageRequest);

    EducationCourseClassPerson convert(UpdateEducationCourseClassPersonRequest updateEducationCourseClassPersonRequest);

    PageResponseDTO<EducationCourseClassPersonDTO> convertPage(PageResponse<List<EducationCourseClassPerson>> pageResponse);

    List<EducationCourseClassPersonDTO> convertList(List<EducationCourseClassPerson> EducationCourseClassPersonList);

}
