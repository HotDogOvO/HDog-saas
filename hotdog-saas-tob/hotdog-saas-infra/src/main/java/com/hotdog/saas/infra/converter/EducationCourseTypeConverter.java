package com.hotdog.saas.infra.converter;

import com.hotdog.saas.domain.model.EducationCourseType;
import com.hotdog.saas.infra.entity.EducationCourseTypeDO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EducationCourseTypeConverter {

    EducationCourseTypeConverter INSTANCE = Mappers.getMapper(EducationCourseTypeConverter.class);

    EducationCourseTypeDO convert2DO(EducationCourseType educationCourseType);

    EducationCourseType convert(EducationCourseTypeDO educationCourseTypeDO);
}
