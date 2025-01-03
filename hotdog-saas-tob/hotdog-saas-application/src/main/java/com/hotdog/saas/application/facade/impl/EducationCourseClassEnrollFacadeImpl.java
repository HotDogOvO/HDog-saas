package com.hotdog.saas.application.facade.impl;

import com.hotdog.saas.application.entity.request.education.clazz.enroll.AssignEducationCourseClassEnrollRequest;
import com.hotdog.saas.application.entity.request.education.clazz.enroll.DeleteEducationCourseClassEnrollRequest;
import com.hotdog.saas.application.entity.request.education.clazz.enroll.EducationCourseClassEnrollPageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassEnrollDTO;
import com.hotdog.saas.application.facade.EducationCourseClassEnrollFacade;
import com.hotdog.saas.application.processor.BaseProcessor;
import com.hotdog.saas.application.processor.education.clazz.enroll.EducationCourseClassEnrollAssignProcessor;
import com.hotdog.saas.application.processor.education.clazz.enroll.EducationCourseClassEnrollDeleteProcessor;
import com.hotdog.saas.application.processor.education.clazz.enroll.EducationCourseClassEnrollListProcessor;

import org.springframework.stereotype.Component;

@Component
public class EducationCourseClassEnrollFacadeImpl extends BaseProcessor implements EducationCourseClassEnrollFacade {

    private final EducationCourseClassEnrollListProcessor educationCourseClassEnrollListProcessor;
    private final EducationCourseClassEnrollAssignProcessor educationCourseClassEnrollAssignProcessor;
    private final EducationCourseClassEnrollDeleteProcessor educationCourseClassEnrollDeleteProcessor;

    public EducationCourseClassEnrollFacadeImpl(EducationCourseClassEnrollListProcessor educationCourseClassEnrollListProcessor, EducationCourseClassEnrollAssignProcessor educationCourseClassEnrollAssignProcessor, EducationCourseClassEnrollDeleteProcessor educationCourseClassEnrollDeleteProcessor) {
        this.educationCourseClassEnrollListProcessor = educationCourseClassEnrollListProcessor;
        this.educationCourseClassEnrollAssignProcessor = educationCourseClassEnrollAssignProcessor;
        this.educationCourseClassEnrollDeleteProcessor = educationCourseClassEnrollDeleteProcessor;
    }

    @Override
    public BaseResponse<PageResponseDTO<EducationCourseClassEnrollDTO>> educationCourseClassEnrollListPage(EducationCourseClassEnrollPageRequest educationCourseClassEnrollPageRequest) {
        return this.doBiz(educationCourseClassEnrollPageRequest, educationCourseClassEnrollListProcessor);
    }

    @Override
    public BaseResponse<Boolean> assignEducationCourseClassEnroll(AssignEducationCourseClassEnrollRequest assignEducationCourseClassEnrollRequest) {
        return this.doBiz(assignEducationCourseClassEnrollRequest, educationCourseClassEnrollAssignProcessor);
    }

    @Override
    public BaseResponse<Boolean> deleteEducationCourseClassEnroll(DeleteEducationCourseClassEnrollRequest deleteEducationCourseClassEnrollRequest) {
        return this.doBiz(deleteEducationCourseClassEnrollRequest, educationCourseClassEnrollDeleteProcessor);
    }
}
