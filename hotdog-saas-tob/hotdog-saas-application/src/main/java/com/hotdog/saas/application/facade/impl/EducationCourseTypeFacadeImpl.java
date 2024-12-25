package com.hotdog.saas.application.facade.impl;

import com.hotdog.saas.application.entity.request.education.type.CreateEducationCourseTypeRequest;
import com.hotdog.saas.application.entity.request.education.type.DeleteEducationCourseTypeRequest;
import com.hotdog.saas.application.entity.request.education.type.EducationCourseTypeOptionsRequest;
import com.hotdog.saas.application.entity.request.education.type.EducationCourseTypePageRequest;
import com.hotdog.saas.application.entity.request.education.type.UpdateEducationCourseTypeRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseTypeDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseTypeOptionsDTO;
import com.hotdog.saas.application.facade.EducationCourseTypeFacade;
import com.hotdog.saas.application.processor.BaseProcessor;
import com.hotdog.saas.application.processor.education.type.EducationCourseTypeCreateProcessor;
import com.hotdog.saas.application.processor.education.type.EducationCourseTypeDeleteProcessor;
import com.hotdog.saas.application.processor.education.type.EducationCourseTypeListPageProcessor;
import com.hotdog.saas.application.processor.education.type.EducationCourseTypeOptionsProcessor;
import com.hotdog.saas.application.processor.education.type.EducationCourseTypeUpdateProcessor;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EducationCourseTypeFacadeImpl extends BaseProcessor implements EducationCourseTypeFacade {

    private final EducationCourseTypeCreateProcessor educationCourseTypeCreateProcessor;
    private final EducationCourseTypeListPageProcessor educationCourseTypeListPageProcessor;
    private final EducationCourseTypeOptionsProcessor educationCourseTypeOptionsProcessor;
    private final EducationCourseTypeUpdateProcessor educationCourseTypeUpdateProcessor;
    private final EducationCourseTypeDeleteProcessor educationCourseTypeDeleteProcessor;

    public EducationCourseTypeFacadeImpl(EducationCourseTypeCreateProcessor educationCourseTypeCreateProcessor, EducationCourseTypeListPageProcessor educationCourseTypeListPageProcessor, EducationCourseTypeOptionsProcessor educationCourseTypeOptionsProcessor, EducationCourseTypeUpdateProcessor educationCourseTypeUpdateProcessor, EducationCourseTypeDeleteProcessor educationCourseTypeDeleteProcessor) {
        this.educationCourseTypeCreateProcessor = educationCourseTypeCreateProcessor;
        this.educationCourseTypeListPageProcessor = educationCourseTypeListPageProcessor;
        this.educationCourseTypeOptionsProcessor = educationCourseTypeOptionsProcessor;
        this.educationCourseTypeUpdateProcessor = educationCourseTypeUpdateProcessor;
        this.educationCourseTypeDeleteProcessor = educationCourseTypeDeleteProcessor;
    }

    @Override
    public BaseResponse<Boolean> createEducationCourseType(CreateEducationCourseTypeRequest createEducationCourseTypeRequest) {
        return this.doBiz(createEducationCourseTypeRequest, educationCourseTypeCreateProcessor);
    }

    @Override
    public BaseResponse<PageResponseDTO<EducationCourseTypeDTO>> educationCourseTypeListPage(EducationCourseTypePageRequest educationCourseTypePageRequest) {
        return this.doBiz(educationCourseTypePageRequest, educationCourseTypeListPageProcessor);
    }

    @Override
    public BaseResponse<List<EducationCourseTypeOptionsDTO>> educationCourseTypeOptions(EducationCourseTypeOptionsRequest educationCourseTypeOptionsRequest) {
        return this.doBiz(educationCourseTypeOptionsRequest, educationCourseTypeOptionsProcessor);
    }

    @Override
    public BaseResponse<Boolean> updateEducationCourseType(UpdateEducationCourseTypeRequest updateEducationCourseTypeRequest) {
        return this.doBiz(updateEducationCourseTypeRequest, educationCourseTypeUpdateProcessor);
    }

    @Override
    public BaseResponse<Boolean> deleteEducationCourseType(DeleteEducationCourseTypeRequest deleteEducationCourseTypeRequest) {
        return this.doBiz(deleteEducationCourseTypeRequest, educationCourseTypeDeleteProcessor);
    }
}
