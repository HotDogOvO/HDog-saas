package com.hotdog.saas.application.facade;

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

import java.util.List;

public interface EducationCourseClassFacade {

    BaseResponse<Boolean> createEducationCourseClass(CreateEducationCourseClassRequest createEducationCourseClassRequest);

    BaseResponse<PageResponseDTO<EducationCourseClassDTO>> educationCourseClassListPage(EducationCourseClassPageRequest educationCourseClassPageRequest);

    BaseResponse<List<EducationCourseClassOptionsDTO>> educationCourseClassOptions(EducationCourseClassOptionsRequest educationCourseClassOptionsRequest);

    BaseResponse<EducationCourseClassDTO> educationCourseClassDetail(QueryEducationCourseClassRequest queryEducationCourseClassRequest);

    BaseResponse<Boolean> updateEducationCourseClass(UpdateEducationCourseClassRequest updateEducationCourseClassRequest);

    BaseResponse<Boolean> startEducationCourseClass(StartEducationCourseClassRequest startEducationCourseClassRequest);

    BaseResponse<Boolean> deleteEducationCourseClass(DeleteEducationCourseClassRequest deleteEducationCourseClassRequest);

}
