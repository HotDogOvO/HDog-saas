package com.hotdog.saas.application.assembler;

import com.hotdog.saas.application.entity.request.education.clazz.CreateEducationCourseClassRequest;
import com.hotdog.saas.application.entity.request.education.clazz.EducationCourseClassPageRequest;
import com.hotdog.saas.application.entity.request.education.clazz.UpdateEducationCourseClassRequest;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassDTO;
import com.hotdog.saas.domain.model.EducationCourseClass;
import com.hotdog.saas.domain.model.page.PageResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EducationCourseClassAssembler {

    EducationCourseClassAssembler INSTANCE = Mappers.getMapper(EducationCourseClassAssembler.class);

    EducationCourseClass convert(CreateEducationCourseClassRequest createEducationCourseClassRequest);

    EducationCourseClass convert(EducationCourseClassPageRequest educationCourseClassPageRequest);

    EducationCourseClass convert(UpdateEducationCourseClassRequest updateEducationCourseClassRequest);

    PageResponseDTO<EducationCourseClassDTO> convertPage(PageResponse<List<EducationCourseClass>> pageResponse);

    @Mappings({
            @Mapping(source = "name", target = "className")
    })
    EducationCourseClassDTO convertToDTO(EducationCourseClass educationCourseClass);
}
