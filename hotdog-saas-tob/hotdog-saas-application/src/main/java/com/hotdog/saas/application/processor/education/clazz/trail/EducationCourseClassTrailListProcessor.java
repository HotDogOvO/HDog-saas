package com.hotdog.saas.application.processor.education.clazz.trail;

import com.hotdog.saas.application.assembler.EducationCourseClassPersonAssembler;
import com.hotdog.saas.application.assembler.EducationCourseClassTrailAssembler;
import com.hotdog.saas.application.entity.request.education.clazz.person.EducationCourseClassPersonPageRequest;
import com.hotdog.saas.application.entity.request.education.clazz.trail.EducationCourseClassTrailPageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassEnrollDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassPersonDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassTrailDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourse;
import com.hotdog.saas.domain.model.EducationCourseClass;
import com.hotdog.saas.domain.model.EducationCourseClassPerson;
import com.hotdog.saas.domain.model.EducationCourseClassSchedule;
import com.hotdog.saas.domain.model.EducationCourseClassTrail;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseClassTrailListProcessor extends AbstractEducationClassTrailProcessor<EducationCourseClassTrailPageRequest, BaseResponse<PageResponseDTO<EducationCourseClassTrailDTO>>> {

    @Override
    public BaseResponse<PageResponseDTO<EducationCourseClassTrailDTO>> initResult() {
        BaseResponse<PageResponseDTO<EducationCourseClassTrailDTO>> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(EducationCourseClassTrailPageRequest request, BaseResponse<PageResponseDTO<EducationCourseClassTrailDTO>> response) {
        request.initPage();

        EducationCourseClassTrail educationCourseClassTrail = EducationCourseClassTrailAssembler.INSTANCE.convert(request);
        PageRequest pageRequest = reqToPage(request);

        PageResponse<List<EducationCourseClassTrail>> listPageResponse = educationCourseClassTrailRepository.listPage(educationCourseClassTrail, pageRequest);

        PageResponseDTO<EducationCourseClassTrailDTO> list = EducationCourseClassTrailAssembler.INSTANCE.convertPage(listPageResponse);

        List<EducationCourseClassTrailDTO> dtoList = list.getData();
        // 课程内容转换
        List<String> courseNoList = dtoList.stream().map(EducationCourseClassTrailDTO::getCourseNo).toList();
        Map<String, String> courseNoToNameMap = educationCourseRepository.findInCourseNo(courseNoList).stream()
                .collect(Collectors.toMap(EducationCourse::getCourseNo, EducationCourse::getName));

        // 班级内容转换
        List<String> classNoList = dtoList.stream().map(EducationCourseClassTrailDTO::getClassNo).toList();
        Map<String, String> classNoToNameMap = educationCourseClassRepository.findInClassNo(classNoList).stream()
                .collect(Collectors.toMap(EducationCourseClass::getClassNo, EducationCourseClass::getName));

        // 课时内容转换
        List<Long> classScheduleIdList = dtoList.stream().map(EducationCourseClassTrailDTO::getClassScheduleId).toList();
        Map<Long, String> scheduleIdToNameMap = educationCourseClassScheduleRepository.findInIdList(classScheduleIdList).stream()
                .collect(Collectors.toMap(EducationCourseClassSchedule::getId, EducationCourseClassSchedule::getClassHoursName));

        dtoList.forEach(dto -> {
            dto.setCourseName(courseNoToNameMap.get(dto.getCourseNo()));
            dto.setClassName(classNoToNameMap.get(dto.getClassNo()));
            dto.setClassScheduleName(scheduleIdToNameMap.get(dto.getClassScheduleId()));
        });

        response.setData(list);
    }

}
