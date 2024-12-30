package com.hotdog.saas.infra.converter;

import com.hotdog.saas.domain.model.EducationCourseClassPerson;
import com.hotdog.saas.infra.entity.EducationCourseClassPersonDO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EducationCourseClassPersonConverter {

    EducationCourseClassPersonConverter INSTANCE = Mappers.getMapper(EducationCourseClassPersonConverter.class);

    EducationCourseClassPersonDO convert2DO(EducationCourseClassPerson educationCourseClassPerson);

    EducationCourseClassPerson convert(EducationCourseClassPersonDO educationCourseClassPersonDO);

}
