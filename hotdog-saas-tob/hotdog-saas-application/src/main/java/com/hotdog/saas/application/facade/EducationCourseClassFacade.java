package com.hotdog.saas.application.facade;

import com.hotdog.saas.application.entity.request.education.clazz.CreateEducationCourseClassRequest;
import com.hotdog.saas.application.entity.request.education.clazz.DeleteEducationCourseClassRequest;
import com.hotdog.saas.application.entity.request.education.clazz.QueryEducationCourseClassRequest;
import com.hotdog.saas.application.entity.request.education.clazz.EducationCourseClassPageRequest;
import com.hotdog.saas.application.entity.request.education.clazz.UpdateEducationCourseClassRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassDTO;

public interface EducationCourseClassFacade {

    BaseResponse<Boolean> createEducationCourseClass(CreateEducationCourseClassRequest createEducationCourseClassRequest);

    BaseResponse<PageResponseDTO<EducationCourseClassDTO>> educationCourseClassListPage(EducationCourseClassPageRequest educationCourseClassPageRequest);

    BaseResponse<EducationCourseClassDTO> educationCourseClassDetail(QueryEducationCourseClassRequest queryEducationCourseClassRequest);

    BaseResponse<Boolean> updateEducationCourseClass(UpdateEducationCourseClassRequest updateEducationCourseClassRequest);

    BaseResponse<Boolean> deleteEducationCourseClass(DeleteEducationCourseClassRequest deleteEducationCourseClassRequest);

}
