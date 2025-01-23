package com.hotdog.saas.application.processor.education.clazz.signin;

import com.hotdog.saas.application.assembler.EducationCourseClassSignInAssembler;
import com.hotdog.saas.application.entity.request.education.clazz.signin.EducationCourseClassSignInPageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassSignInDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassTrailDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourse;
import com.hotdog.saas.domain.model.EducationCourseClass;
import com.hotdog.saas.domain.model.EducationCourseClassSchedule;
import com.hotdog.saas.domain.model.EducationCourseClassSignIn;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseClassSignInListProcessor extends AbstractEducationClassSignInProcessor<EducationCourseClassSignInPageRequest, BaseResponse<PageResponseDTO<EducationCourseClassSignInDTO>>> {

    @Override
    public BaseResponse<PageResponseDTO<EducationCourseClassSignInDTO>> initResult() {
        BaseResponse<PageResponseDTO<EducationCourseClassSignInDTO>> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(EducationCourseClassSignInPageRequest request, BaseResponse<PageResponseDTO<EducationCourseClassSignInDTO>> response) {
        request.initPage();

        EducationCourseClassSignIn educationCourseClassSignIn = EducationCourseClassSignInAssembler.INSTANCE.convert(request);
        PageRequest pageRequest = reqToPage(request);

        PageResponse<List<EducationCourseClassSignIn>> listPageResponse = educationCourseClassSignInRepository.listPage(educationCourseClassSignIn, pageRequest);

        PageResponseDTO<EducationCourseClassSignInDTO> list = EducationCourseClassSignInAssembler.INSTANCE.convertPage(listPageResponse);

        List<EducationCourseClassSignInDTO> dtoList = list.getData();
        // 课程内容转换
        List<String> courseNoList = dtoList.stream().map(EducationCourseClassSignInDTO::getCourseNo).toList();
        Map<String, String> courseNoToNameMap = educationCourseRepository.findInCourseNo(courseNoList).stream()
                .collect(Collectors.toMap(EducationCourse::getCourseNo, EducationCourse::getName));

        // 班级内容转换
        List<String> classNoList = dtoList.stream().map(EducationCourseClassSignInDTO::getClassNo).toList();
        Map<String, String> classNoToNameMap = educationCourseClassRepository.findInClassNo(classNoList).stream()
                .collect(Collectors.toMap(EducationCourseClass::getClassNo, EducationCourseClass::getName));

        // 课时内容转换
        List<Long> classScheduleIdList = dtoList.stream().map(EducationCourseClassSignInDTO::getClassScheduleId).toList();
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
