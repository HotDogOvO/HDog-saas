package com.hotdog.saas.application.facade.impl;

import com.hotdog.saas.application.entity.request.education.clazz.schedule.CalendarEducationCourseClassScheduleRequest;
import com.hotdog.saas.application.entity.request.education.clazz.schedule.CreateEducationCourseClassScheduleRequest;
import com.hotdog.saas.application.entity.request.education.clazz.schedule.DeleteEducationCourseClassScheduleRequest;
import com.hotdog.saas.application.entity.request.education.clazz.schedule.EducationCourseClassScheduleOptionsRequest;
import com.hotdog.saas.application.entity.request.education.clazz.schedule.EducationCourseClassSchedulePageRequest;
import com.hotdog.saas.application.entity.request.education.clazz.schedule.UpdateEducationCourseClassScheduleRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassScheduleCalendarDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassScheduleDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassScheduleOptionsDTO;
import com.hotdog.saas.application.facade.EducationCourseClassScheduleFacade;
import com.hotdog.saas.application.processor.BaseProcessor;
import com.hotdog.saas.application.processor.education.clazz.schedule.EducationCourseClassScheduleCalendarProcessor;
import com.hotdog.saas.application.processor.education.clazz.schedule.EducationCourseClassScheduleCreateProcessor;
import com.hotdog.saas.application.processor.education.clazz.schedule.EducationCourseClassScheduleDeleteProcessor;
import com.hotdog.saas.application.processor.education.clazz.schedule.EducationCourseClassScheduleListProcessor;
import com.hotdog.saas.application.processor.education.clazz.schedule.EducationCourseClassScheduleOptionsProcessor;
import com.hotdog.saas.application.processor.education.clazz.schedule.EducationCourseClassScheduleUpdateProcessor;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EducationCourseClassScheduleFacadeImpl extends BaseProcessor implements EducationCourseClassScheduleFacade {

    private final EducationCourseClassScheduleCreateProcessor educationCourseClassScheduleCreateProcessor;
    private final EducationCourseClassScheduleCalendarProcessor educationCourseClassScheduleCalendarProcessor;
    private final EducationCourseClassScheduleOptionsProcessor educationCourseClassScheduleOptionsProcessor;
    private final EducationCourseClassScheduleListProcessor educationCourseClassScheduleListProcessor;
    private final EducationCourseClassScheduleDeleteProcessor educationCourseClassScheduleDeleteProcessor;
    private final EducationCourseClassScheduleUpdateProcessor educationCourseClassScheduleUpdateProcessor;

    public EducationCourseClassScheduleFacadeImpl(EducationCourseClassScheduleCreateProcessor educationCourseClassScheduleCreateProcessor, EducationCourseClassScheduleCalendarProcessor educationCourseClassScheduleCalendarProcessor, EducationCourseClassScheduleOptionsProcessor educationCourseClassScheduleOptionsProcessor, EducationCourseClassScheduleListProcessor educationCourseClassScheduleListProcessor, EducationCourseClassScheduleDeleteProcessor educationCourseClassScheduleDeleteProcessor, EducationCourseClassScheduleUpdateProcessor educationCourseClassScheduleUpdateProcessor) {
        this.educationCourseClassScheduleCreateProcessor = educationCourseClassScheduleCreateProcessor;
        this.educationCourseClassScheduleCalendarProcessor = educationCourseClassScheduleCalendarProcessor;
        this.educationCourseClassScheduleOptionsProcessor = educationCourseClassScheduleOptionsProcessor;
        this.educationCourseClassScheduleListProcessor = educationCourseClassScheduleListProcessor;
        this.educationCourseClassScheduleDeleteProcessor = educationCourseClassScheduleDeleteProcessor;
        this.educationCourseClassScheduleUpdateProcessor = educationCourseClassScheduleUpdateProcessor;
    }

    @Override
    public BaseResponse<Boolean> createEducationCourseClassSchedule(CreateEducationCourseClassScheduleRequest createEducationCourseClassScheduleRequest) {
        return this.doBiz(createEducationCourseClassScheduleRequest, educationCourseClassScheduleCreateProcessor);
    }

    @Override
    public BaseResponse<PageResponseDTO<EducationCourseClassScheduleDTO>> educationCourseClassScheduleListPage(EducationCourseClassSchedulePageRequest educationCourseClassSchedulePageRequest) {
        return this.doBiz(educationCourseClassSchedulePageRequest, educationCourseClassScheduleListProcessor);
    }

    @Override
    public BaseResponse<List<EducationCourseClassScheduleCalendarDTO>> educationCourseClassScheduleCalendar(CalendarEducationCourseClassScheduleRequest calendarEducationCourseClassScheduleRequest) {
        return this.doBiz(calendarEducationCourseClassScheduleRequest, educationCourseClassScheduleCalendarProcessor);
    }

    @Override
    public BaseResponse<List<EducationCourseClassScheduleOptionsDTO>> educationCourseClassScheduleOptions(EducationCourseClassScheduleOptionsRequest educationCourseClassScheduleOptionsRequest) {
        return this.doBiz(educationCourseClassScheduleOptionsRequest, educationCourseClassScheduleOptionsProcessor);
    }

    @Override
    public BaseResponse<Boolean> updateEducationCourseClassSchedule(UpdateEducationCourseClassScheduleRequest updateEducationCourseClassScheduleRequest) {
        return this.doBiz(updateEducationCourseClassScheduleRequest, educationCourseClassScheduleUpdateProcessor);
    }

    @Override
    public BaseResponse<Boolean> deleteEducationCourseClassSchedule(DeleteEducationCourseClassScheduleRequest deleteEducationCourseClassScheduleRequest) {
        return this.doBiz(deleteEducationCourseClassScheduleRequest, educationCourseClassScheduleDeleteProcessor);
    }
}
