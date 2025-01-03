package com.hotdog.saas.infra.converter;

import com.hotdog.saas.domain.model.EducationCourseClassSignIn;
import com.hotdog.saas.infra.entity.EducationCourseClassSignInDO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EducationCourseClassSignInConverter {

    EducationCourseClassSignInConverter INSTANCE = Mappers.getMapper(EducationCourseClassSignInConverter.class);

    EducationCourseClassSignInDO convert2DO(EducationCourseClassSignIn educationCourseClassSignIn);

    EducationCourseClassSignIn convert(EducationCourseClassSignInDO educationCourseClassSignInDO);

    List<EducationCourseClassSignIn> convert2List(List<EducationCourseClassSignInDO> list);
}
