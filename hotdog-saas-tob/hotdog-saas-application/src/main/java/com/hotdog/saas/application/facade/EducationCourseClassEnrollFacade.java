package com.hotdog.saas.application.facade;

import com.hotdog.saas.application.entity.request.education.clazz.enroll.AssignEducationCourseClassEnrollRequest;
import com.hotdog.saas.application.entity.request.education.clazz.enroll.DeleteEducationCourseClassEnrollRequest;
import com.hotdog.saas.application.entity.request.education.clazz.enroll.EducationCourseClassEnrollPageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassEnrollDTO;

public interface EducationCourseClassEnrollFacade {

    BaseResponse<PageResponseDTO<EducationCourseClassEnrollDTO>> educationCourseClassEnrollListPage(EducationCourseClassEnrollPageRequest educationCourseClassEnrollPageRequest);

    BaseResponse<Boolean> assignEducationCourseClassEnroll(AssignEducationCourseClassEnrollRequest assignEducationCourseClassEnrollRequest);

    BaseResponse<Boolean> deleteEducationCourseClassEnroll(DeleteEducationCourseClassEnrollRequest deleteEducationCourseClassEnrollRequest);

}