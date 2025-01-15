package com.hotdog.saas.application.assembler;

import com.hotdog.saas.application.entity.request.education.attach.EducationCourseAttachRequest;
import com.hotdog.saas.application.entity.response.education.EducationCourseAttachDTO;
import com.hotdog.saas.domain.model.EducationCourseAttach;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EducationCourseAttachAssembler {

    EducationCourseAttachAssembler INSTANCE = Mappers.getMapper(EducationCourseAttachAssembler.class);

    EducationCourseAttach convert(EducationCourseAttachRequest request);

    List<EducationCourseAttach> convert2List(List<EducationCourseAttachRequest> list);

    @Mappings({
            @Mapping(source = "id", target = "attachId")
    })
    EducationCourseAttachDTO convert2DTO(EducationCourseAttach educationCourseAttach);

    List<EducationCourseAttachDTO> convert2DTOList(List<EducationCourseAttach> list);
}
