package com.hotdog.saas.domain.repository;

import com.hotdog.saas.domain.model.EducationCourseClass;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;
import java.util.List;

public interface EducationCourseClassRepository {

    Integer save(EducationCourseClass educationCourseClass);

    PageResponse<List<EducationCourseClass>> listPage(EducationCourseClass educationCourseClass, PageRequest pageRequest);

    EducationCourseClass findByClassNo(String classNo);

    List<EducationCourseClass> findByCourseNo(String courseNo);

    Long exists(String classNo);

    Long existsByName(String name, Long wechatId);

    Integer modify(EducationCourseClass educationCourseClass);

    Integer remove(String classNo, String operator);

}
