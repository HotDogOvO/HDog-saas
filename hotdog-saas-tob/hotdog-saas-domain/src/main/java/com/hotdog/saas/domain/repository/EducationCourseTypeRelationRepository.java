package com.hotdog.saas.domain.repository;

import com.hotdog.saas.domain.model.EducationCourseTypeRelation;

public interface EducationCourseTypeRelationRepository {

    Integer save(EducationCourseTypeRelation educationCourseTypeRelation);

    EducationCourseTypeRelation findByCourseNo(String courseNo);

    Integer removeByCourseNo(String courseNo);
}
