package com.hotdog.saas.infra.converter;

import com.hotdog.saas.domain.model.EducationCourseClassRecord;
import com.hotdog.saas.infra.entity.EducationCourseClassRecordDO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EducationCourseClassRecordConverter {

    EducationCourseClassRecordConverter INSTANCE = Mappers.getMapper(EducationCourseClassRecordConverter.class);

    EducationCourseClassRecordDO convert2DO(EducationCourseClassRecord educationCourseClassRecord);

    EducationCourseClassRecord convert(EducationCourseClassRecordDO educationCourseClassRecordDO);
}
