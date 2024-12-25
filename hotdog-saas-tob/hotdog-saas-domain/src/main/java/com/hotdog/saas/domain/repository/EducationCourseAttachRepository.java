package com.hotdog.saas.domain.repository;

import com.hotdog.saas.domain.model.EducationCourseAttach;

import java.util.List;

public interface EducationCourseAttachRepository {

    Integer save(EducationCourseAttach educationCourseAttach);

    List<EducationCourseAttach> findByCourseNo(String courseNo);

    Integer remove(Long id,String operator);
}
