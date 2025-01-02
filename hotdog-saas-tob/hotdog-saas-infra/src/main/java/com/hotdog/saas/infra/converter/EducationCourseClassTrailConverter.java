package com.hotdog.saas.infra.converter;

import com.hotdog.saas.domain.model.EducationCourseClassTrail;
import com.hotdog.saas.infra.entity.EducationCourseClassTrailDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface EducationCourseClassTrailConverter {

    EducationCourseClassTrailConverter INSTANCE = Mappers.getMapper(EducationCourseClassTrailConverter.class);

    EducationCourseClassTrailDO convert2DO(EducationCourseClassTrail educationCourseClassTrail);

    EducationCourseClassTrail convert(EducationCourseClassTrailDO educationCourseClassTrailDO);

    List<EducationCourseClassTrail> convert2List(List<EducationCourseClassTrailDO> list);
}
