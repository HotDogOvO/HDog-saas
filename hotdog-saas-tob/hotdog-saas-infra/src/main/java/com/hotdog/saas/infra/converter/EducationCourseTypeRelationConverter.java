package com.hotdog.saas.infra.converter;

import com.hotdog.saas.domain.model.EducationCourseTypeRelation;
import com.hotdog.saas.infra.entity.EducationCourseTypeRelationDO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EducationCourseTypeRelationConverter {

    EducationCourseTypeRelationConverter INSTANCE = Mappers.getMapper(EducationCourseTypeRelationConverter.class);

    EducationCourseTypeRelationDO convert2DO(EducationCourseTypeRelation educationCourseTypeRelation);

    EducationCourseTypeRelation convert(EducationCourseTypeRelationDO educationCourseTypeRelationDO);
}
