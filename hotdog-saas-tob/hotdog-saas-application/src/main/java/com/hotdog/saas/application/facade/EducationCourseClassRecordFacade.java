package com.hotdog.saas.application.facade;

import com.hotdog.saas.application.entity.request.education.clazz.record.EducationCourseClassRecordPageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassRecordDTO;

public interface EducationCourseClassRecordFacade {

    BaseResponse<PageResponseDTO<EducationCourseClassRecordDTO>> educationCourseClassPersonListPage(EducationCourseClassRecordPageRequest educationCourseClassRecordPageRequest);

}
