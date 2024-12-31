package com.hotdog.saas.domain.repository;

import com.hotdog.saas.domain.model.EducationCourse;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;

import java.util.List;

public interface EducationCourseRepository {

    Long save(EducationCourse educationCourse);

    PageResponse<List<EducationCourse>> listPage(EducationCourse educationCourse, PageRequest pageRequest);

    List<EducationCourse> list(EducationCourse educationCourse);

    EducationCourse findByCourseNo(String courseNo);

    Long exists(String courseNo, Long tenantId);

    Long existsByName(String name, Long tenantId);

    Integer modify(EducationCourse educationCourse);

    Integer remove(String courseNo, String operator);

}
