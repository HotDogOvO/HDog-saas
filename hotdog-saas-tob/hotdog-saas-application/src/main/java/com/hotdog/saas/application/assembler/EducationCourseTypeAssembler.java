package com.hotdog.saas.application.assembler;

import com.hotdog.saas.application.entity.request.education.type.CreateEducationCourseTypeRequest;
import com.hotdog.saas.application.entity.request.education.type.EducationCourseTypeOptionsRequest;
import com.hotdog.saas.application.entity.request.education.type.EducationCourseTypePageRequest;
import com.hotdog.saas.application.entity.request.education.type.UpdateEducationCourseTypeRequest;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseTypeDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseTypeOptionsDTO;
import com.hotdog.saas.domain.model.EducationCourseType;
import com.hotdog.saas.domain.model.page.PageResponse;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EducationCourseTypeAssembler {

    EducationCourseTypeAssembler INSTANCE = Mappers.getMapper(EducationCourseTypeAssembler.class);

    EducationCourseType convert(CreateEducationCourseTypeRequest createEducationCourseTypeRequest);

    EducationCourseType convert(EducationCourseTypePageRequest educationCourseTypePageRequest);

    EducationCourseType convert(UpdateEducationCourseTypeRequest updateEducationCourseTypeRequest);

    EducationCourseType convert(EducationCourseTypeOptionsRequest educationCourseTypeOptionsRequest);

    PageResponseDTO<EducationCourseTypeDTO> convertPage(PageResponse<List<EducationCourseType>> pageResponse);

    List<EducationCourseTypeOptionsDTO> convertOptions(List<EducationCourseType> list);

    EducationCourseTypeDTO convertToDTO(EducationCourseType EducationCourseType);
}
