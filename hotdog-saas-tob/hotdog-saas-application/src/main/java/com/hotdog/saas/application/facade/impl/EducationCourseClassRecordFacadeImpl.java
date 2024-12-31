package com.hotdog.saas.application.facade.impl;

import com.hotdog.saas.application.entity.request.education.clazz.record.EducationCourseClassRecordPageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassRecordDTO;
import com.hotdog.saas.application.facade.EducationCourseClassRecordFacade;
import com.hotdog.saas.application.processor.BaseProcessor;
import com.hotdog.saas.application.processor.education.clazz.record.EducationCourseClassRecordListProcessor;

import org.springframework.stereotype.Component;

@Component
public class EducationCourseClassRecordFacadeImpl extends BaseProcessor implements EducationCourseClassRecordFacade {

    private final EducationCourseClassRecordListProcessor educationCourseClassRecordListProcessor;

    public EducationCourseClassRecordFacadeImpl(EducationCourseClassRecordListProcessor educationCourseClassRecordListProcessor) {
        this.educationCourseClassRecordListProcessor = educationCourseClassRecordListProcessor;
    }

    @Override
    public BaseResponse<PageResponseDTO<EducationCourseClassRecordDTO>> educationCourseClassPersonListPage(EducationCourseClassRecordPageRequest educationCourseClassRecordPageRequest) {
        return this.doBiz(educationCourseClassRecordPageRequest, educationCourseClassRecordListProcessor);
    }
}
