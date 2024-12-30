package com.hotdog.saas.application.assembler;

import com.hotdog.saas.application.entity.request.education.clazz.schedule.CreateEducationCourseClassScheduleRequest;
import com.hotdog.saas.application.entity.request.education.clazz.schedule.EducationCourseClassSchedulePageRequest;
import com.hotdog.saas.application.entity.request.education.clazz.schedule.UpdateEducationCourseClassScheduleRequest;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassScheduleDTO;
import com.hotdog.saas.domain.model.EducationCourseClassSchedule;
import com.hotdog.saas.domain.model.page.PageResponse;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EducationCourseClassScheduleAssembler {

    EducationCourseClassScheduleAssembler INSTANCE = Mappers.getMapper(EducationCourseClassScheduleAssembler.class);

    EducationCourseClassSchedule convert(CreateEducationCourseClassScheduleRequest createEducationCourseClassScheduleRequest);

    EducationCourseClassSchedule convert(EducationCourseClassSchedulePageRequest EducationCourseClassSchedulePageRequest);

    EducationCourseClassSchedule convert(UpdateEducationCourseClassScheduleRequest updateEducationCourseClassScheduleRequest);

    PageResponseDTO<EducationCourseClassScheduleDTO> convertPage(PageResponse<List<EducationCourseClassSchedule>> pageResponse);

    List<EducationCourseClassScheduleDTO> convertList(List<EducationCourseClassSchedule> EducationCourseClassScheduleList);

}
