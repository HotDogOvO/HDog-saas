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

import org.springframework.stereotype.Component;

@Component
public class EducationCourseFacadeImpl extends BaseProcessor implements EducationCourseFacade {
    @Override
    public BaseResponse<Boolean> createEducationCourse(CreateEducationCourseRequest createEducationCourseRequest) {
        return this.doBiz(createEducationCourseRequest, null);
    }

    @Override
    public BaseResponse<PageResponseDTO<EducationCourseDTO>> educationCourseListPage(EducationCoursePageRequest educationCoursePageRequest) {
        return this.doBiz(educationCoursePageRequest, null);
    }

    @Override
    public BaseResponse<EducationCourseDTO> educationCourseDetail(QueryEducationCourseRequest queryEducationCourseRequest) {
        return this.doBiz(queryEducationCourseRequest, null);
    }

    @Override
    public BaseResponse<Boolean> updateEducationCourse(UpdateEducationCourseRequest updateEducationCourseRequest) {
        return this.doBiz(updateEducationCourseRequest, null);
    }

    @Override
    public BaseResponse<Boolean> deleteEducationCourse(DeleteEducationCourseRequest deleteEducationCourseRequest) {
        return this.doBiz(deleteEducationCourseRequest, null);
    }
}
