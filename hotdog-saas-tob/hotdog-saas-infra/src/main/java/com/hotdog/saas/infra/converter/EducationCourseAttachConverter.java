package com.hotdog.saas.infra.converter;

import com.hotdog.saas.domain.model.EducationCourse;
import com.hotdog.saas.domain.model.EducationCourseAttach;
import com.hotdog.saas.infra.entity.EducationCourseAttachDO;
import com.hotdog.saas.infra.entity.EducationCourseDO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EducationCourseAttachConverter {

    EducationCourseAttachConverter INSTANCE = Mappers.getMapper(EducationCourseAttachConverter.class);

    EducationCourseAttachDO convert2DO(EducationCourseAttach educationCourseAttach);

    EducationCourseAttach convert(EducationCourseAttachDO educationCourseAttachDO);
}
