package com.hotdog.saas.application.facade;

import com.hotdog.saas.application.entity.request.education.CreateEducationCourseRequest;
import com.hotdog.saas.application.entity.request.education.DeleteEducationCourseRequest;
import com.hotdog.saas.application.entity.request.education.EducationCourseOptionsRequest;
import com.hotdog.saas.application.entity.request.education.EducationCoursePageRequest;
import com.hotdog.saas.application.entity.request.education.QueryEducationCourseRequest;
import com.hotdog.saas.application.entity.request.education.UpdateEducationCourseRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseOptionsDTO;

import java.util.List;

public interface EducationCourseFacade {

    BaseResponse<Boolean> createEducationCourse(CreateEducationCourseRequest createEducationCourseRequest);

    BaseResponse<PageResponseDTO<EducationCourseDTO>> educationCourseListPage(EducationCoursePageRequest educationCoursePageRequest);

    BaseResponse<List<EducationCourseOptionsDTO>> educationCourseOptions(EducationCourseOptionsRequest educationCourseOptionsRequest);

    BaseResponse<EducationCourseDTO> educationCourseDetail(QueryEducationCourseRequest queryEducationCourseRequest);

    BaseResponse<Boolean> updateEducationCourse(UpdateEducationCourseRequest updateEducationCourseRequest);

    BaseResponse<Boolean> deleteEducationCourse(DeleteEducationCourseRequest deleteEducationCourseRequest);

}
