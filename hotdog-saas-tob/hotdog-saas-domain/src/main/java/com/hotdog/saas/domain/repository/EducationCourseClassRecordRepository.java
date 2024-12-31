package com.hotdog.saas.domain.repository;

import com.hotdog.saas.domain.model.EducationCourseClassRecord;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;

import java.util.List;

public interface EducationCourseClassRecordRepository {

    Integer batchSave(List<EducationCourseClassRecord> list);

    PageResponse<List<EducationCourseClassRecord>> listPage(EducationCourseClassRecord educationCourseClassRecord, PageRequest pageRequest);

}
