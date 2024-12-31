package com.hotdog.saas.application.assembler;

import com.hotdog.saas.application.entity.request.education.clazz.record.EducationCourseClassRecordPageRequest;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassRecordDTO;
import com.hotdog.saas.domain.model.EducationCourseClassRecord;
import com.hotdog.saas.domain.model.page.PageResponse;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EducationCourseClassRecordAssembler {

    EducationCourseClassRecordAssembler INSTANCE = Mappers.getMapper(EducationCourseClassRecordAssembler.class);

    EducationCourseClassRecord convert(EducationCourseClassRecordPageRequest educationCourseClassRecordPageRequest);

    PageResponseDTO<EducationCourseClassRecordDTO> convertPage(PageResponse<List<EducationCourseClassRecord>> pageResponse);
}
