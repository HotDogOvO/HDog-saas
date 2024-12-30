package com.hotdog.saas.infra.converter;

import com.hotdog.saas.domain.model.EducationCourseClassSchedule;
import com.hotdog.saas.infra.entity.EducationCourseClassScheduleDO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EducationCourseClassScheduleConverter {

    EducationCourseClassScheduleConverter INSTANCE = Mappers.getMapper(EducationCourseClassScheduleConverter.class);

    EducationCourseClassScheduleDO convert2DO(EducationCourseClassSchedule educationCourseClassSchedule);

    EducationCourseClassSchedule convert(EducationCourseClassScheduleDO educationCourseClassScheduleDO);

    List<EducationCourseClassSchedule> convert2List(List<EducationCourseClassScheduleDO> list);

}
