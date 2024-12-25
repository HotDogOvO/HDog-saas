package com.hotdog.saas.application.facade;

import com.hotdog.saas.application.entity.request.education.type.CreateEducationCourseTypeRequest;
import com.hotdog.saas.application.entity.request.education.type.DeleteEducationCourseTypeRequest;
import com.hotdog.saas.application.entity.request.education.type.EducationCourseTypeOptionsRequest;
import com.hotdog.saas.application.entity.request.education.type.EducationCourseTypePageRequest;
import com.hotdog.saas.application.entity.request.education.type.UpdateEducationCourseTypeRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseTypeDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseTypeOptionsDTO;

import java.util.List;

public interface EducationCourseTypeFacade {

    BaseResponse<Boolean> createEducationCourseType(CreateEducationCourseTypeRequest createEducationCourseTypeRequest);

    BaseResponse<PageResponseDTO<EducationCourseTypeDTO>> educationCourseTypeListPage(EducationCourseTypePageRequest educationCourseTypePageRequest);

    BaseResponse<List<EducationCourseTypeOptionsDTO>> educationCourseTypeOptions(EducationCourseTypeOptionsRequest educationCourseTypeOptionsRequest);

    BaseResponse<Boolean> updateEducationCourseType(UpdateEducationCourseTypeRequest updateEducationCourseTypeRequest);

    BaseResponse<Boolean> deleteEducationCourseType(DeleteEducationCourseTypeRequest deleteEducationCourseTypeRequest);

}
