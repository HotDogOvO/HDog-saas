package com.hotdog.saas.domain.repository;

import com.hotdog.saas.domain.enums.education.CourseClassScheduleStatusEnum;
import com.hotdog.saas.domain.model.EducationCourseClassSchedule;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface EducationCourseClassScheduleRepository {

    Long save(EducationCourseClassSchedule educationCourseClassSchedule);

    PageResponse<List<EducationCourseClassSchedule>> listPage(EducationCourseClassSchedule educationCourseClassSchedule, PageRequest pageRequest);

    EducationCourseClassSchedule findById(Long id);

    List<EducationCourseClassSchedule> findByClassNo(String classNo);

    Long exists(Long id);

    Long existsBetweenTime(String classNo, LocalDateTime beginTime, LocalDateTime endTime);

    Integer modify(EducationCourseClassSchedule educationCourseClassSchedule);

    Integer modifyStatusInIdList(List<Long> idList, CourseClassScheduleStatusEnum statusEnum);

    Integer remove(Long id, String operator);
}
