package com.hotdog.saas.infra.converter;

import com.hotdog.saas.domain.model.EducationCourse;
import com.hotdog.saas.domain.model.EducationCourseAttach;
import com.hotdog.saas.infra.entity.EducationCourseAttachDO;
import com.hotdog.saas.infra.entity.EducationCourseDO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EducationCourseAttachConverter {

    EducationCourseAttachConverter INSTANCE = Mappers.getMapper(EducationCourseAttachConverter.class);

    @Mappings({
            @Mapping(source = "operator", target = "creator"),
            @Mapping(source = "operator", target = "updater")
    })
    EducationCourseAttachDO convert2DO(EducationCourseAttach educationCourseAttach);

    List<EducationCourseAttachDO> convert2DOList(List<EducationCourseAttach> list);

    EducationCourseAttach convert(EducationCourseAttachDO educationCourseAttachDO);
}
