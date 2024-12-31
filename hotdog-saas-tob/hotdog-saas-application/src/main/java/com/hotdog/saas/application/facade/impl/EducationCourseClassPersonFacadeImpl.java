package com.hotdog.saas.application.facade.impl;

import com.hotdog.saas.application.entity.request.education.clazz.person.CreateEducationCourseClassPersonRequest;
import com.hotdog.saas.application.entity.request.education.clazz.person.DeleteEducationCourseClassPersonRequest;
import com.hotdog.saas.application.entity.request.education.clazz.person.EducationCourseClassPersonPageRequest;
import com.hotdog.saas.application.entity.request.education.clazz.person.UpdateEducationCourseClassPersonRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassPersonDTO;
import com.hotdog.saas.application.facade.EducationCourseClassPersonFacade;
import com.hotdog.saas.application.processor.BaseProcessor;
import com.hotdog.saas.application.processor.education.clazz.person.EducationCourseClassPersonCreateProcessor;
import com.hotdog.saas.application.processor.education.clazz.person.EducationCourseClassPersonDeleteProcessor;
import com.hotdog.saas.application.processor.education.clazz.person.EducationCourseClassPersonListProcessor;
import com.hotdog.saas.application.processor.education.clazz.person.EducationCourseClassPersonUpdateProcessor;

import org.springframework.stereotype.Component;

@Component
public class EducationCourseClassPersonFacadeImpl extends BaseProcessor implements EducationCourseClassPersonFacade {

    private final EducationCourseClassPersonCreateProcessor educationCourseClassPersonCreateProcessor;
    private final EducationCourseClassPersonListProcessor educationCourseClassPersonListProcessor;
    private final EducationCourseClassPersonUpdateProcessor educationCourseClassPersonUpdateProcessor;
    private final EducationCourseClassPersonDeleteProcessor educationCourseClassPersonDeleteProcessor;

    public EducationCourseClassPersonFacadeImpl(EducationCourseClassPersonCreateProcessor educationCourseClassPersonCreateProcessor, EducationCourseClassPersonListProcessor educationCourseClassPersonListProcessor, EducationCourseClassPersonUpdateProcessor educationCourseClassPersonUpdateProcessor, EducationCourseClassPersonDeleteProcessor educationCourseClassPersonDeleteProcessor) {
        this.educationCourseClassPersonCreateProcessor = educationCourseClassPersonCreateProcessor;
        this.educationCourseClassPersonListProcessor = educationCourseClassPersonListProcessor;
        this.educationCourseClassPersonUpdateProcessor = educationCourseClassPersonUpdateProcessor;
        this.educationCourseClassPersonDeleteProcessor = educationCourseClassPersonDeleteProcessor;
    }

    @Override
    public BaseResponse<Boolean> createEducationCourseClassPerson(CreateEducationCourseClassPersonRequest createEducationCourseClassPersonRequest) {
        return this.doBiz(createEducationCourseClassPersonRequest, educationCourseClassPersonCreateProcessor);
    }

    @Override
    public BaseResponse<PageResponseDTO<EducationCourseClassPersonDTO>> educationCourseClassPersonListPage(EducationCourseClassPersonPageRequest educationCourseClassPersonPageRequest) {
        return this.doBiz(educationCourseClassPersonPageRequest, educationCourseClassPersonListProcessor);
    }

    @Override
    public BaseResponse<Boolean> updateEducationCourseClassPerson(UpdateEducationCourseClassPersonRequest updateEducationCourseClassPersonRequest) {
        return this.doBiz(updateEducationCourseClassPersonRequest, educationCourseClassPersonUpdateProcessor);
    }

    @Override
    public BaseResponse<Boolean> deleteEducationCourseClassPerson(DeleteEducationCourseClassPersonRequest deleteEducationCourseClassPersonRequest) {
        return this.doBiz(deleteEducationCourseClassPersonRequest, educationCourseClassPersonDeleteProcessor);
    }
}
