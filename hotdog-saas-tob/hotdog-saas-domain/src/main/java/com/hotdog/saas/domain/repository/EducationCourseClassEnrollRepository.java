package com.hotdog.saas.domain.repository;

import com.hotdog.saas.domain.model.EducationCourseClassEnroll;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;

import java.util.List;

public interface EducationCourseClassEnrollRepository {

    PageResponse<List<EducationCourseClassEnroll>> listPage(EducationCourseClassEnroll educationCourseClassEnroll, PageRequest pageRequest);

    EducationCourseClassEnroll findById(Long id);

    Long exists(Long id);

    Integer modify(EducationCourseClassEnroll educationCourseClassEnroll);

    Integer remove(Long id, String operator);

}
