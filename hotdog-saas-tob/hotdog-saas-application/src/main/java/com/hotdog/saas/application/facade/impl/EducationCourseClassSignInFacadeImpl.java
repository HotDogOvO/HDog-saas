package com.hotdog.saas.application.facade.impl;

import com.hotdog.saas.application.entity.request.education.clazz.signin.DeleteEducationCourseClassSignInRequest;
import com.hotdog.saas.application.entity.request.education.clazz.signin.EducationCourseClassSignInPageRequest;
import com.hotdog.saas.application.entity.request.education.clazz.signin.UpdateEducationCourseClassSignInRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassSignInDTO;
import com.hotdog.saas.application.facade.EducationCourseClassSignInFacade;
import com.hotdog.saas.application.processor.BaseProcessor;
import com.hotdog.saas.application.processor.education.clazz.signin.EducationCourseClassSignInDeleteProcessor;
import com.hotdog.saas.application.processor.education.clazz.signin.EducationCourseClassSignInListProcessor;
import com.hotdog.saas.application.processor.education.clazz.signin.EducationCourseClassSignInUpdateProcessor;

import org.springframework.stereotype.Component;

@Component
public class EducationCourseClassSignInFacadeImpl extends BaseProcessor implements EducationCourseClassSignInFacade {

    private final EducationCourseClassSignInListProcessor educationCourseClassSignInListProcessor;
    private final EducationCourseClassSignInUpdateProcessor educationCourseClassSignInUpdateProcessor;
    private final EducationCourseClassSignInDeleteProcessor educationCourseClassSignInDeleteProcessor;

    public EducationCourseClassSignInFacadeImpl(EducationCourseClassSignInListProcessor educationCourseClassSignInListProcessor, EducationCourseClassSignInUpdateProcessor educationCourseClassSignInUpdateProcessor, EducationCourseClassSignInDeleteProcessor educationCourseClassSignInDeleteProcessor) {
        this.educationCourseClassSignInListProcessor = educationCourseClassSignInListProcessor;
        this.educationCourseClassSignInUpdateProcessor = educationCourseClassSignInUpdateProcessor;
        this.educationCourseClassSignInDeleteProcessor = educationCourseClassSignInDeleteProcessor;
    }

    @Override
    public BaseResponse<PageResponseDTO<EducationCourseClassSignInDTO>> educationCourseClassSignInListPage(EducationCourseClassSignInPageRequest educationCourseClassSignInPageRequest) {
        return this.doBiz(educationCourseClassSignInPageRequest, educationCourseClassSignInListProcessor);
    }

    @Override
    public BaseResponse<Boolean> updateEducationCourseClassSignIn(UpdateEducationCourseClassSignInRequest updateEducationCourseClassSignInRequest) {
        return this.doBiz(updateEducationCourseClassSignInRequest, educationCourseClassSignInUpdateProcessor);
    }

    @Override
    public BaseResponse<Boolean> deleteEducationCourseClassSignIn(DeleteEducationCourseClassSignInRequest deleteEducationCourseClassSignInRequest) {
        return this.doBiz(deleteEducationCourseClassSignInRequest, educationCourseClassSignInDeleteProcessor);
    }
}
