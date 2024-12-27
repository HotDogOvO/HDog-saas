package com.hotdog.saas.application.facade.impl;

import com.hotdog.saas.application.entity.request.education.clazz.CreateEducationCourseClassRequest;
import com.hotdog.saas.application.entity.request.education.clazz.DeleteEducationCourseClassRequest;
import com.hotdog.saas.application.entity.request.education.clazz.QueryEducationCourseClassRequest;
import com.hotdog.saas.application.entity.request.education.clazz.EducationCourseClassPageRequest;
import com.hotdog.saas.application.entity.request.education.clazz.UpdateEducationCourseClassRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassDTO;
import com.hotdog.saas.application.facade.EducationCourseClassFacade;
import com.hotdog.saas.application.processor.BaseProcessor;
import com.hotdog.saas.application.processor.education.clazz.EducationCourseClassCreateProcessor;
import com.hotdog.saas.application.processor.education.clazz.EducationCourseClassDeleteProcessor;
import com.hotdog.saas.application.processor.education.clazz.EducationCourseClassDetailProcessor;
import com.hotdog.saas.application.processor.education.clazz.EducationCourseClassListProcessor;
import com.hotdog.saas.application.processor.education.clazz.EducationCourseClassUpdateProcessor;

import org.springframework.stereotype.Component;

@Component
public class EducationCourseClassFacadeImpl extends BaseProcessor implements EducationCourseClassFacade {

    private final EducationCourseClassCreateProcessor educationCourseClassCreateProcessor;
    private final EducationCourseClassListProcessor educationCourseClassListProcessor;
    private final EducationCourseClassDetailProcessor educationCourseClassDetailProcessor;
    private final EducationCourseClassDeleteProcessor educationCourseClassDeleteProcessor;
    private final EducationCourseClassUpdateProcessor educationCourseClassUpdateProcessor;

    public EducationCourseClassFacadeImpl(EducationCourseClassCreateProcessor educationCourseClassCreateProcessor, EducationCourseClassListProcessor educationCourseClassListProcessor, EducationCourseClassDetailProcessor educationCourseClassDetailProcessor, EducationCourseClassDeleteProcessor educationCourseClassDeleteProcessor, EducationCourseClassUpdateProcessor educationCourseClassUpdateProcessor) {
        this.educationCourseClassCreateProcessor = educationCourseClassCreateProcessor;
        this.educationCourseClassListProcessor = educationCourseClassListProcessor;
        this.educationCourseClassDetailProcessor = educationCourseClassDetailProcessor;
        this.educationCourseClassDeleteProcessor = educationCourseClassDeleteProcessor;
        this.educationCourseClassUpdateProcessor = educationCourseClassUpdateProcessor;
    }

    @Override
    public BaseResponse<Boolean> createEducationCourseClass(CreateEducationCourseClassRequest createEducationCourseClassRequest) {
        return this.doBiz(createEducationCourseClassRequest, educationCourseClassCreateProcessor);
    }

    @Override
    public BaseResponse<PageResponseDTO<EducationCourseClassDTO>> educationCourseClassListPage(EducationCourseClassPageRequest educationCourseClassPageRequest) {
        return this.doBiz(educationCourseClassPageRequest, educationCourseClassListProcessor);
    }

    @Override
    public BaseResponse<EducationCourseClassDTO> educationCourseClassDetail(QueryEducationCourseClassRequest queryEducationCourseClassRequest) {
        return this.doBiz(queryEducationCourseClassRequest, educationCourseClassDetailProcessor);
    }

    @Override
    public BaseResponse<Boolean> updateEducationCourseClass(UpdateEducationCourseClassRequest updateEducationCourseClassRequest) {
        return this.doBiz(updateEducationCourseClassRequest, educationCourseClassUpdateProcessor);
    }

    @Override
    public BaseResponse<Boolean> deleteEducationCourseClass(DeleteEducationCourseClassRequest deleteEducationCourseClassRequest) {
        return this.doBiz(deleteEducationCourseClassRequest, educationCourseClassDeleteProcessor);
    }
}
