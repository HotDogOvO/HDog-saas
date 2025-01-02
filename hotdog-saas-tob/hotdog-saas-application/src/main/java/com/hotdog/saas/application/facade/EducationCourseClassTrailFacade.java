package com.hotdog.saas.application.facade;

import com.hotdog.saas.application.entity.request.education.clazz.trail.AssignEducationCourseClassTrailRequest;
import com.hotdog.saas.application.entity.request.education.clazz.trail.DeleteEducationCourseClassTrailRequest;
import com.hotdog.saas.application.entity.request.education.clazz.trail.EducationCourseClassTrailPageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassTrailDTO;

public interface EducationCourseClassTrailFacade {

    BaseResponse<PageResponseDTO<EducationCourseClassTrailDTO>> educationCourseClassTrailListPage(EducationCourseClassTrailPageRequest educationCourseClassTrailPageRequest);

    BaseResponse<Boolean> assignEducationCourseClassTrail(AssignEducationCourseClassTrailRequest assignEducationCourseClassTrailRequest);

    BaseResponse<Boolean> deleteEducationCourseClassTrail(DeleteEducationCourseClassTrailRequest deleteEducationCourseClassTrailRequest);

}