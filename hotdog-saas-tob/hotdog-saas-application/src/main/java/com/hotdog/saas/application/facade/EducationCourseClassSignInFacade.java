package com.hotdog.saas.application.facade;

import com.hotdog.saas.application.entity.request.education.clazz.signin.DeleteEducationCourseClassSignInRequest;
import com.hotdog.saas.application.entity.request.education.clazz.signin.EducationCourseClassSignInPageRequest;
import com.hotdog.saas.application.entity.request.education.clazz.signin.UpdateEducationCourseClassSignInRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassSignInDTO;

public interface EducationCourseClassSignInFacade {

    BaseResponse<PageResponseDTO<EducationCourseClassSignInDTO>> educationCourseClassSignInListPage(EducationCourseClassSignInPageRequest educationCourseClassSignInPageRequest);

    BaseResponse<Boolean> updateEducationCourseClassSignIn(UpdateEducationCourseClassSignInRequest updateEducationCourseClassSignInRequest);

    BaseResponse<Boolean> deleteEducationCourseClassSignIn(DeleteEducationCourseClassSignInRequest deleteEducationCourseClassSignInRequest);

}
