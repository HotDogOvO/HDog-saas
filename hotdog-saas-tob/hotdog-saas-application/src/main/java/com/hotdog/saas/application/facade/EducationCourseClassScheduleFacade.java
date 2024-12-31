package com.hotdog.saas.application.facade;

import com.hotdog.saas.application.entity.request.education.clazz.schedule.CalendarEducationCourseClassScheduleRequest;
import com.hotdog.saas.application.entity.request.education.clazz.schedule.CreateEducationCourseClassScheduleRequest;
import com.hotdog.saas.application.entity.request.education.clazz.schedule.DeleteEducationCourseClassScheduleRequest;
import com.hotdog.saas.application.entity.request.education.clazz.schedule.EducationCourseClassSchedulePageRequest;
import com.hotdog.saas.application.entity.request.education.clazz.schedule.UpdateEducationCourseClassScheduleRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassScheduleCalendarDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassScheduleDTO;

import java.util.List;

public interface EducationCourseClassScheduleFacade {

    BaseResponse<Boolean> createEducationCourseClassSchedule(CreateEducationCourseClassScheduleRequest createEducationCourseClassScheduleRequest);

    BaseResponse<PageResponseDTO<EducationCourseClassScheduleDTO>> educationCourseClassScheduleListPage(EducationCourseClassSchedulePageRequest educationCourseClassSchedulePageRequest);

    BaseResponse<List<EducationCourseClassScheduleCalendarDTO>> educationCourseClassScheduleCalendar(CalendarEducationCourseClassScheduleRequest calendarEducationCourseClassScheduleRequest);

    BaseResponse<Boolean> updateEducationCourseClassSchedule(UpdateEducationCourseClassScheduleRequest updateEducationCourseClassScheduleRequest);

    BaseResponse<Boolean> deleteEducationCourseClassSchedule(DeleteEducationCourseClassScheduleRequest deleteEducationCourseClassScheduleRequest);

}
