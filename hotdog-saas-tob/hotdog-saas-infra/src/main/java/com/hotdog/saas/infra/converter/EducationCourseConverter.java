package com.hotdog.saas.infra.converter;

import com.hotdog.saas.domain.model.EducationCourse;
import com.hotdog.saas.domain.model.User;
import com.hotdog.saas.infra.entity.EducationCourseDO;
import com.hotdog.saas.infra.entity.UserDO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EducationCourseConverter {

    EducationCourseConverter INSTANCE = Mappers.getMapper(EducationCourseConverter.class);

    EducationCourseDO convert2DO(EducationCourse educationCourse);

    EducationCourse convert(EducationCourseDO educationCourseDO);
}
