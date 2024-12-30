package com.hotdog.saas.application.facade.impl;

import com.hotdog.saas.application.entity.request.education.clazz.schedule.CreateEducationCourseClassScheduleRequest;
import com.hotdog.saas.application.entity.request.education.clazz.schedule.DeleteEducationCourseClassScheduleRequest;
import com.hotdog.saas.application.entity.request.education.clazz.schedule.EducationCourseClassSchedulePageRequest;
import com.hotdog.saas.application.entity.request.education.clazz.schedule.UpdateEducationCourseClassScheduleRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassScheduleDTO;
import com.hotdog.saas.application.facade.EducationCourseClassScheduleFacade;
import com.hotdog.saas.application.processor.BaseProcessor;
import com.hotdog.saas.application.processor.education.clazz.schedule.EducationCourseClassScheduleCreateProcessor;
import com.hotdog.saas.application.processor.education.clazz.schedule.EducationCourseClassScheduleDeleteProcessor;
import com.hotdog.saas.application.processor.education.clazz.schedule.EducationCourseClassScheduleListProcessor;
import com.hotdog.saas.application.processor.education.clazz.schedule.EducationCourseClassScheduleUpdateProcessor;

import org.springframework.stereotype.Component;

@Component
public class EducationCourseClassScheduleFacadeImpl extends BaseProcessor implements EducationCourseClassScheduleFacade {

    private final EducationCourseClassScheduleCreateProcessor educationCourseClassScheduleCreateProcessor;
    private final EducationCourseClassScheduleListProcessor educationCourseClassScheduleListProcessor;
    private final EducationCourseClassScheduleDeleteProcessor educationCourseClassScheduleDeleteProcessor;
    private final EducationCourseClassScheduleUpdateProcessor educationCourseClassScheduleUpdateProcessor;

    public EducationCourseClassScheduleFacadeImpl(EducationCourseClassScheduleCreateProcessor educationCourseClassScheduleCreateProcessor, EducationCourseClassScheduleListProcessor educationCourseClassScheduleListProcessor, EducationCourseClassScheduleDeleteProcessor educationCourseClassScheduleDeleteProcessor, EducationCourseClassScheduleUpdateProcessor educationCourseClassScheduleUpdateProcessor) {
        this.educationCourseClassScheduleCreateProcessor = educationCourseClassScheduleCreateProcessor;
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
    public BaseResponse<Boolean> updateEducationCourseClassSchedule(UpdateEducationCourseClassScheduleRequest updateEducationCourseClassScheduleRequest) {
        return this.doBiz(updateEducationCourseClassScheduleRequest, educationCourseClassScheduleUpdateProcessor);
    }

    @Override
    public BaseResponse<Boolean> deleteEducationCourseClassSchedule(DeleteEducationCourseClassScheduleRequest deleteEducationCourseClassScheduleRequest) {
        return this.doBiz(deleteEducationCourseClassScheduleRequest, educationCourseClassScheduleDeleteProcessor);
    }
}
