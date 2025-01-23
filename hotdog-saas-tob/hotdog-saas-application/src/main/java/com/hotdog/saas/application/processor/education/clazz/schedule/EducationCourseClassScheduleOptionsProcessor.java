package com.hotdog.saas.application.processor.education.clazz.schedule;

import com.hotdog.saas.application.assembler.EducationCourseClassScheduleAssembler;
import com.hotdog.saas.application.entity.request.education.clazz.schedule.EducationCourseClassScheduleOptionsRequest;
import com.hotdog.saas.application.entity.request.education.clazz.schedule.EducationCourseClassSchedulePageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassScheduleDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassScheduleOptionsDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourseClassSchedule;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;

import org.springframework.stereotype.Component;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseClassScheduleOptionsProcessor extends AbstractEducationClassScheduleProcessor<EducationCourseClassScheduleOptionsRequest, BaseResponse<List<EducationCourseClassScheduleOptionsDTO>>> {

    @Override
    public BaseResponse<List<EducationCourseClassScheduleOptionsDTO>> initResult() {
        BaseResponse<List<EducationCourseClassScheduleOptionsDTO>> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(EducationCourseClassScheduleOptionsRequest request, BaseResponse<List<EducationCourseClassScheduleOptionsDTO>> response) {
        String classNo = request.getClassNo();
        super.existsByClassNo(classNo);
        List<EducationCourseClassSchedule> educationCourseClassScheduleList = educationCourseClassScheduleRepository.findByClassNo(classNo);

        List<EducationCourseClassScheduleOptionsDTO> list = EducationCourseClassScheduleAssembler.INSTANCE.convertOptions(educationCourseClassScheduleList);

        response.setData(list);
    }

}
