package com.hotdog.saas.infra.converter;

import com.hotdog.saas.domain.model.EducationCourseClass;
import com.hotdog.saas.infra.entity.EducationCourseClassDO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EducationCourseClassConverter {

    EducationCourseClassConverter INSTANCE = Mappers.getMapper(EducationCourseClassConverter.class);

    EducationCourseClassDO convert2DO(EducationCourseClass educationCourseClass);

    EducationCourseClass convert(EducationCourseClassDO educationCourseClassDO);

    List<EducationCourseClass> convert2List(List<EducationCourseClassDO> list);
}
