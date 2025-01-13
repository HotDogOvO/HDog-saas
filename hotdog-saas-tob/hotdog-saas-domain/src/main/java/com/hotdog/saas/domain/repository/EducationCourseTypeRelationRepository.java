package com.hotdog.saas.domain.repository;

import com.hotdog.saas.domain.model.EducationCourseTypeRelation;

import java.util.List;

public interface EducationCourseTypeRelationRepository {

    Long save(EducationCourseTypeRelation educationCourseTypeRelation);

    EducationCourseTypeRelation findByCourseNo(String courseNo);

    List<EducationCourseTypeRelation> findByTypeId(Long typeId);

    Integer removeByCourseNo(String courseNo);
}
