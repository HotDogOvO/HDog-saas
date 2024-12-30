package com.hotdog.saas.domain.repository;

import com.hotdog.saas.domain.model.EducationCourseClassPerson;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;

import java.util.List;

public interface EducationCourseClassPersonRepository {

    Integer save(EducationCourseClassPerson educationCourseClassPerson);

    PageResponse<List<EducationCourseClassPerson>> listPage(EducationCourseClassPerson educationCourseClassPerson, PageRequest pageRequest);

    List<EducationCourseClassPerson> findByClassNo(String classNo);

    Integer modify(EducationCourseClassPerson educationCourseClassPerson);

    Integer remove(Long id, String operator);
}
