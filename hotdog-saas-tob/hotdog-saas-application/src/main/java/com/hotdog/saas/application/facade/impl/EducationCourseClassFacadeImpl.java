package com.hotdog.saas.application.facade.impl;

import com.hotdog.saas.application.entity.request.education.clazz.CreateEducationCourseClassRequest;
import com.hotdog.saas.application.entity.request.education.clazz.DeleteEducationCourseClassRequest;
import com.hotdog.saas.application.entity.request.education.clazz.EducationCourseClassOptionsRequest;
import com.hotdog.saas.application.entity.request.education.clazz.QueryEducationCourseClassRequest;
import com.hotdog.saas.application.entity.request.education.clazz.EducationCourseClassPageRequest;
import com.hotdog.saas.application.entity.request.education.clazz.StartEducationCourseClassRequest;
import com.hotdog.saas.application.entity.request.education.clazz.UpdateEducationCourseClassRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassOptionsDTO;
import com.hotdog.saas.application.facade.EducationCourseClassFacade;
import com.hotdog.saas.application.processor.BaseProcessor;
import com.hotdog.saas.application.processor.education.clazz.EducationCourseClassCreateProcessor;
import com.hotdog.saas.application.processor.education.clazz.EducationCourseClassDeleteProcessor;
import com.hotdog.saas.application.processor.education.clazz.EducationCourseClassDetailProcessor;
import com.hotdog.saas.application.processor.education.clazz.EducationCourseClassListProcessor;
import com.hotdog.saas.application.processor.education.clazz.EducationCourseClassOptionsProcessor;
import com.hotdog.saas.application.processor.education.clazz.EducationCourseClassStartProcessor;
import com.hotdog.saas.application.processor.education.clazz.EducationCourseClassUpdateProcessor;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EducationCourseClassFacadeImpl extends BaseProcessor implements EducationCourseClassFacade {

    private final EducationCourseClassCreateProcessor educationCourseClassCreateProcessor;
    private final EducationCourseClassListProcessor educationCourseClassListProcessor;
    private final EducationCourseClassOptionsProcessor educationCourseClassOptionsProcessor;
    private final EducationCourseClassDetailProcessor educationCourseClassDetailProcessor;
    private final EducationCourseClassDeleteProcessor educationCourseClassDeleteProcessor;
    private final EducationCourseClassUpdateProcessor educationCourseClassUpdateProcessor;
    private final EducationCourseClassStartProcessor educationCourseClassStartProcessor;

    public EducationCourseClassFacadeImpl(EducationCourseClassCreateProcessor educationCourseClassCreateProcessor, EducationCourseClassListProcessor educationCourseClassListProcessor, EducationCourseClassOptionsProcessor educationCourseClassOptionsProcessor, EducationCourseClassDetailProcessor educationCourseClassDetailProcessor, EducationCourseClassDeleteProcessor educationCourseClassDeleteProcessor, EducationCourseClassUpdateProcessor educationCourseClassUpdateProcessor, EducationCourseClassStartProcessor educationCourseClassStartProcessor) {
        this.educationCourseClassCreateProcessor = educationCourseClassCreateProcessor;
        this.educationCourseClassListProcessor = educationCourseClassListProcessor;
        this.educationCourseClassOptionsProcessor = educationCourseClassOptionsProcessor;
        this.educationCourseClassDetailProcessor = educationCourseClassDetailProcessor;
        this.educationCourseClassDeleteProcessor = educationCourseClassDeleteProcessor;
        this.educationCourseClassUpdateProcessor = educationCourseClassUpdateProcessor;
        this.educationCourseClassStartProcessor = educationCourseClassStartProcessor;
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
    public BaseResponse<List<EducationCourseClassOptionsDTO>> educationCourseClassOptions(EducationCourseClassOptionsRequest educationCourseClassOptionsRequest) {
        return this.doBiz(educationCourseClassOptionsRequest, educationCourseClassOptionsProcessor);
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
    public BaseResponse<Boolean> startEducationCourseClass(StartEducationCourseClassRequest startEducationCourseClassRequest) {
        return this.doBiz(startEducationCourseClassRequest, educationCourseClassStartProcessor);
    }

    @Override
    public BaseResponse<Boolean> deleteEducationCourseClass(DeleteEducationCourseClassRequest deleteEducationCourseClassRequest) {
        return this.doBiz(deleteEducationCourseClassRequest, educationCourseClassDeleteProcessor);
    }
}
