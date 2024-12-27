package com.hotdog.saas.domain.repository;

import com.hotdog.saas.domain.model.EducationCourseAttach;

import java.util.List;

public interface EducationCourseAttachRepository {

    Integer batchSave(List<EducationCourseAttach> list);

    List<EducationCourseAttach> findByCourseNo(String courseNo);

    Integer batchRemove(List<Long> idList, String operator);
}
