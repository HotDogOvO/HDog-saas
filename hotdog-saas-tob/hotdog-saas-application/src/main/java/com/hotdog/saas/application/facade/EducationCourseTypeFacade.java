package com.hotdog.saas.application.facade;

import com.hotdog.saas.application.entity.request.education.CreateEducationCourseRequest;
import com.hotdog.saas.application.entity.request.education.DeleteEducationCourseRequest;
import com.hotdog.saas.application.entity.request.education.EducationCoursePageRequest;
import com.hotdog.saas.application.entity.request.education.QueryEducationCourseRequest;
import com.hotdog.saas.application.entity.request.education.UpdateEducationCourseRequest;
import com.hotdog.saas.application.entity.request.education.type.CreateEducationCourseTypeRequest;
import com.hotdog.saas.application.entity.request.education.type.DeleteEducationCourseTypeRequest;
import com.hotdog.saas.application.entity.request.education.type.EducationCourseTypeListRequest;
import com.hotdog.saas.application.entity.request.education.type.EducationCourseTypePageRequest;
import com.hotdog.saas.application.entity.request.education.type.UpdateEducationCourseTypeRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseTypeDTO;

import java.util.List;

public interface EducationCourseTypeFacade {

    BaseResponse<Boolean> createEducationCourseType(CreateEducationCourseTypeRequest createEducationCourseTypeRequest);

    BaseResponse<PageResponseDTO<EducationCourseTypeDTO>> educationCourseTypeListPage(EducationCourseTypePageRequest educationCourseTypePageRequest);

    BaseResponse<List<EducationCourseTypeDTO>> educationCourseTypeList(EducationCourseTypeListRequest educationCourseTypeListRequest);

    BaseResponse<Boolean> updateEducationCourseType(UpdateEducationCourseTypeRequest updateEducationCourseTypeRequest);

    BaseResponse<Boolean> deleteEducationCourseType(DeleteEducationCourseTypeRequest deleteEducationCourseTypeRequest);

}
