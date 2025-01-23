package com.hotdog.saas.application.processor.education.clazz.enroll;

import com.hotdog.saas.application.assembler.EducationCourseClassEnrollAssembler;
import com.hotdog.saas.application.entity.request.education.clazz.enroll.EducationCourseClassEnrollPageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassEnrollDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourse;
import com.hotdog.saas.domain.model.EducationCourseClass;
import com.hotdog.saas.domain.model.EducationCourseClassEnroll;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseClassEnrollListProcessor extends AbstractEducationClassEnrollProcessor<EducationCourseClassEnrollPageRequest, BaseResponse<PageResponseDTO<EducationCourseClassEnrollDTO>>> {

    @Override
    public BaseResponse<PageResponseDTO<EducationCourseClassEnrollDTO>> initResult() {
        BaseResponse<PageResponseDTO<EducationCourseClassEnrollDTO>> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(EducationCourseClassEnrollPageRequest request, BaseResponse<PageResponseDTO<EducationCourseClassEnrollDTO>> response) {
        request.initPage();

        EducationCourseClassEnroll educationCourseClassEnroll = EducationCourseClassEnrollAssembler.INSTANCE.convert(request);
        PageRequest pageRequest = reqToPage(request);

        PageResponse<List<EducationCourseClassEnroll>> listPageResponse = educationCourseClassEnrollRepository.listPage(educationCourseClassEnroll, pageRequest);

        PageResponseDTO<EducationCourseClassEnrollDTO> list = EducationCourseClassEnrollAssembler.INSTANCE.convertPage(listPageResponse);
        List<EducationCourseClassEnrollDTO> dtoList = list.getData();
        // 课程内容转换
        List<String> courseNoList = dtoList.stream().map(EducationCourseClassEnrollDTO::getCourseNo).toList();
        Map<String, String> courseNoToNameMap = educationCourseRepository.findInCourseNo(courseNoList).stream()
                .collect(Collectors.toMap(EducationCourse::getCourseNo, EducationCourse::getName));

        // 班级内容转换
        List<String> classNoList = dtoList.stream().map(EducationCourseClassEnrollDTO::getClassNo).toList();
        Map<String, String> classNoToNameMap = educationCourseClassRepository.findInClassNo(classNoList).stream()
                .collect(Collectors.toMap(EducationCourseClass::getClassNo, EducationCourseClass::getName));

        dtoList.forEach(dto -> {
            dto.setCourseName(courseNoToNameMap.get(dto.getCourseNo()));
            dto.setClassName(classNoToNameMap.get(dto.getClassNo()));
        });

        response.setData(list);
    }

}
