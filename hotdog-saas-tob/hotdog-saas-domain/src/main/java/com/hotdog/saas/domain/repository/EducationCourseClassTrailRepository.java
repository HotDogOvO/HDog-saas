package com.hotdog.saas.domain.repository;

import com.hotdog.saas.domain.model.EducationCourseClassTrail;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;
import java.util.List;

public interface EducationCourseClassTrailRepository {

    PageResponse<List<EducationCourseClassTrail>> listPage(EducationCourseClassTrail educationCourseClassTrail, PageRequest pageRequest);

    List<EducationCourseClassTrail> findByClassNo(String classNo);

    List<EducationCourseClassTrail> findByCourseNo(String courseNo);

    Long exists(Long id);

    Integer modify(EducationCourseClassTrail educationCourseClassTrail);

    Integer remove(Long id, String operator);

}