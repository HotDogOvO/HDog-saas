package com.hotdog.saas.domain.repository;

import com.hotdog.saas.domain.model.EducationCourseType;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;

import java.util.List;

public interface EducationCourseTypeRepository {

    Long save(EducationCourseType educationCourseType);

    PageResponse<List<EducationCourseType>> listPage(EducationCourseType educationCourseType, PageRequest pageRequest);

    List<EducationCourseType> list(EducationCourseType educationCourseType);

    EducationCourseType findById(Long id);

    Long exists(Long id);

    Long existByTypeName(String typeName, Long wechatId);

    Integer modify(EducationCourseType educationCourseType);

    Integer remove(Long id, String operator);
}
