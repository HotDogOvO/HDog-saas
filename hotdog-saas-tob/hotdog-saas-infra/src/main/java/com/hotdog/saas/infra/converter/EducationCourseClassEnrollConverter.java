package com.hotdog.saas.infra.converter;

import com.hotdog.saas.domain.model.EducationCourseClassEnroll;
import com.hotdog.saas.infra.entity.EducationCourseClassEnrollDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EducationCourseClassEnrollConverter {

    EducationCourseClassEnrollConverter INSTANCE = Mappers.getMapper(EducationCourseClassEnrollConverter.class);

    EducationCourseClassEnrollDO convert2DO(EducationCourseClassEnroll educationCourseClassEnroll);

    EducationCourseClassEnroll convert(EducationCourseClassEnrollDO educationCourseClassEnrollDO);

    List<EducationCourseClassEnroll> convert2List(List<EducationCourseClassEnrollDO> list);
}
