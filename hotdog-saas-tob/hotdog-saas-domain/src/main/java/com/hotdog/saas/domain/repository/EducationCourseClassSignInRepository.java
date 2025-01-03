package com.hotdog.saas.domain.repository;

import com.hotdog.saas.domain.model.EducationCourseClassSignIn;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;

import java.util.List;

public interface EducationCourseClassSignInRepository {

    PageResponse<List<EducationCourseClassSignIn>> listPage(EducationCourseClassSignIn educationCourseClassSignIn, PageRequest pageRequest);

    Long exists(Long id);

    Integer modify(EducationCourseClassSignIn educationCourseClassSignIn);

    Integer remove(Long id, String operator);

}
