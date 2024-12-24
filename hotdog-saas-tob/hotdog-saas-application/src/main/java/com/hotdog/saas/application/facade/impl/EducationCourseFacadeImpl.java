package com.hotdog.saas.application.facade.impl;

import com.hotdog.saas.application.entity.request.education.CreateEducationCourseRequest;
import com.hotdog.saas.application.entity.request.education.DeleteEducationCourseRequest;
import com.hotdog.saas.application.entity.request.education.EducationCoursePageRequest;
import com.hotdog.saas.application.entity.request.education.QueryEducationCourseRequest;
import com.hotdog.saas.application.entity.request.education.UpdateEducationCourseRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseDTO;
import com.hotdog.saas.application.facade.EducationCourseFacade;
import com.hotdog.saas.application.processor.BaseProcessor;

import com.hotdog.saas.application.processor.education.*;
import org.springframework.stereotype.Component;

@Component
public class EducationCourseFacadeImpl extends BaseProcessor implements EducationCourseFacade {

    private final EducationCourseCreateProcessor educationCourseCreateProcessor;
    private final EducationCourseListProcessor educationCourseListProcessor;
    private final EducationCourseDetailProcessor educationCourseDetailProcessor;
    private final EducationCourseDeleteProcessor educationCourseDeleteProcessor;
    private final EducationCourseUpdateProcessor educationCourseUpdateProcessor;

    public EducationCourseFacadeImpl(EducationCourseCreateProcessor educationCourseCreateProcessor, EducationCourseListProcessor educationCourseListProcessor, EducationCourseDetailProcessor educationCourseDetailProcessor, EducationCourseDeleteProcessor educationCourseDeleteProcessor, EducationCourseUpdateProcessor educationCourseUpdateProcessor) {
        this.educationCourseCreateProcessor = educationCourseCreateProcessor;
        this.educationCourseListProcessor = educationCourseListProcessor;
        this.educationCourseDetailProcessor = educationCourseDetailProcessor;
        this.educationCourseDeleteProcessor = educationCourseDeleteProcessor;
        this.educationCourseUpdateProcessor = educationCourseUpdateProcessor;
    }

    @Override
    public BaseResponse<Boolean> createEducationCourse(CreateEducationCourseRequest createEducationCourseRequest) {
        return this.doBiz(createEducationCourseRequest, educationCourseCreateProcessor);
    }

    @Override
    public BaseResponse<PageResponseDTO<EducationCourseDTO>> educationCourseListPage(EducationCoursePageRequest educationCoursePageRequest) {
        return this.doBiz(educationCoursePageRequest, educationCourseListProcessor);
    }

    @Override
    public BaseResponse<EducationCourseDTO> educationCourseDetail(QueryEducationCourseRequest queryEducationCourseRequest) {
        return this.doBiz(queryEducationCourseRequest, educationCourseDetailProcessor);
    }

    @Override
    public BaseResponse<Boolean> updateEducationCourse(UpdateEducationCourseRequest updateEducationCourseRequest) {
        return this.doBiz(updateEducationCourseRequest, educationCourseUpdateProcessor);
    }

    @Override
    public BaseResponse<Boolean> deleteEducationCourse(DeleteEducationCourseRequest deleteEducationCourseRequest) {
        return this.doBiz(deleteEducationCourseRequest, educationCourseDeleteProcessor);
    }
}
