package com.hotdog.saas.application.facade.impl;

import com.hotdog.saas.application.entity.request.education.clazz.trail.AssignEducationCourseClassTrailRequest;
import com.hotdog.saas.application.entity.request.education.clazz.trail.DeleteEducationCourseClassTrailRequest;
import com.hotdog.saas.application.entity.request.education.clazz.trail.EducationCourseClassTrailPageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassTrailDTO;
import com.hotdog.saas.application.facade.EducationCourseClassTrailFacade;
import com.hotdog.saas.application.processor.BaseProcessor;
import com.hotdog.saas.application.processor.education.clazz.trail.EducationCourseClassTrailAssignProcessor;
import com.hotdog.saas.application.processor.education.clazz.trail.EducationCourseClassTrailDeleteProcessor;
import com.hotdog.saas.application.processor.education.clazz.trail.EducationCourseClassTrailListProcessor;

import org.springframework.stereotype.Component;

@Component
public class EducationCourseClassTrailFacadeImpl extends BaseProcessor implements EducationCourseClassTrailFacade {

    private final EducationCourseClassTrailListProcessor educationCourseClassTrailListProcessor;
    private final EducationCourseClassTrailAssignProcessor educationCourseClassTrailAssignProcessor;
    private final EducationCourseClassTrailDeleteProcessor educationCourseClassTrailDeleteProcessor;

    public EducationCourseClassTrailFacadeImpl(EducationCourseClassTrailListProcessor educationCourseClassTrailListProcessor, EducationCourseClassTrailAssignProcessor educationCourseClassTrailAssignProcessor, EducationCourseClassTrailDeleteProcessor educationCourseClassTrailDeleteProcessor) {
        this.educationCourseClassTrailListProcessor = educationCourseClassTrailListProcessor;
        this.educationCourseClassTrailAssignProcessor = educationCourseClassTrailAssignProcessor;
        this.educationCourseClassTrailDeleteProcessor = educationCourseClassTrailDeleteProcessor;
    }

    @Override
    public BaseResponse<PageResponseDTO<EducationCourseClassTrailDTO>> educationCourseClassTrailListPage(EducationCourseClassTrailPageRequest educationCourseClassTrailPageRequest) {
        return this.doBiz(educationCourseClassTrailPageRequest, educationCourseClassTrailListProcessor);
    }

    @Override
    public BaseResponse<Boolean> assignEducationCourseClassTrail(AssignEducationCourseClassTrailRequest assignEducationCourseClassTrailRequest) {
        return this.doBiz(assignEducationCourseClassTrailRequest, educationCourseClassTrailAssignProcessor);
    }

    @Override
    public BaseResponse<Boolean> deleteEducationCourseClassTrail(DeleteEducationCourseClassTrailRequest deleteEducationCourseClassTrailRequest) {
        return this.doBiz(deleteEducationCourseClassTrailRequest, educationCourseClassTrailDeleteProcessor);
    }
}
