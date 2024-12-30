package com.hotdog.saas.application.processor.education.clazz.schedule;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.hotdog.saas.application.assembler.EducationCourseClassScheduleAssembler;
import com.hotdog.saas.application.entity.request.education.clazz.schedule.CalendarEducationCourseClassScheduleRequest;
import com.hotdog.saas.application.entity.request.education.clazz.schedule.CreateEducationCourseClassScheduleRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassScheduleCalendarDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassScheduleDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourseClassSchedule;
import com.hotdog.saas.domain.utils.DateUtils;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseClassScheduleCalendarProcessor extends AbstractEducationClassScheduleProcessor<CalendarEducationCourseClassScheduleRequest, BaseResponse<List<EducationCourseClassScheduleCalendarDTO>>> {

    @Override
    public BaseResponse<List<EducationCourseClassScheduleCalendarDTO>> initResult() {
        BaseResponse<List<EducationCourseClassScheduleCalendarDTO>> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(CalendarEducationCourseClassScheduleRequest request, BaseResponse<List<EducationCourseClassScheduleCalendarDTO>> response) {
        String classNo = request.getClassNo();
        super.existsByClassNo(classNo);
        List<EducationCourseClassSchedule> scheduleList = educationCourseClassScheduleRepository.findByClassNo(classNo);
        Map<String, List<EducationCourseClassSchedule>> scheduleMap = scheduleList.stream()
                .collect(Collectors.groupingBy(schedule -> DateUtils.getFormatDate(schedule.getClassBeginTime(), DateUtils.YYYY_MM_DD)));

        // 使用 forEach 来处理分组后的结果并进行转换
        List<EducationCourseClassScheduleCalendarDTO> result = scheduleMap.entrySet().stream()
                .map(entry -> {
                    List<EducationCourseClassScheduleDTO> educationCourseClassScheduleDTOS = EducationCourseClassScheduleAssembler.INSTANCE.convertList(entry.getValue());
                    return EducationCourseClassScheduleCalendarDTO.builder()
                            .date(entry.getKey())
                            .dataList(educationCourseClassScheduleDTOS)
                            .build();
                })
                .sorted(Comparator.comparing(EducationCourseClassScheduleCalendarDTO::getDate))
                .toList();

        response.setData(result);
    }

}
