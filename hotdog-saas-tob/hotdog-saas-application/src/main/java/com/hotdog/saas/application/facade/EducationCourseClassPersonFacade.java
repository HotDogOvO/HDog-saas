package com.hotdog.saas.application.facade;

import com.hotdog.saas.application.entity.request.education.clazz.person.CreateEducationCourseClassPersonRequest;
import com.hotdog.saas.application.entity.request.education.clazz.person.DeleteEducationCourseClassPersonRequest;
import com.hotdog.saas.application.entity.request.education.clazz.person.EducationCourseClassPersonPageRequest;
import com.hotdog.saas.application.entity.request.education.clazz.person.UpdateEducationCourseClassPersonRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassPersonDTO;

public interface EducationCourseClassPersonFacade {

    BaseResponse<Boolean> createEducationCourseClassPerson(CreateEducationCourseClassPersonRequest createEducationCourseClassPersonRequest);

    BaseResponse<PageResponseDTO<EducationCourseClassPersonDTO>> educationCourseClassPersonListPage(EducationCourseClassPersonPageRequest educationCourseClassPersonPageRequest);

    BaseResponse<Boolean> updateEducationCourseClassPerson(UpdateEducationCourseClassPersonRequest updateEducationCourseClassPersonRequest);

    BaseResponse<Boolean> deleteEducationCourseClassPerson(DeleteEducationCourseClassPersonRequest deleteEducationCourseClassPersonRequest);

}
