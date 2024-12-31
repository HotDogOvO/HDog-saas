package com.hotdog.saas.application.processor.education.clazz.schedule;

import com.hotdog.saas.application.assembler.EducationCourseClassAssembler;
import com.hotdog.saas.application.assembler.EducationCourseClassScheduleAssembler;
import com.hotdog.saas.application.entity.request.education.clazz.EducationCourseClassPageRequest;
import com.hotdog.saas.application.entity.request.education.clazz.schedule.EducationCourseClassSchedulePageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassScheduleDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourseClass;
import com.hotdog.saas.domain.model.EducationCourseClassSchedule;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;

import org.springframework.stereotype.Component;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseClassScheduleListProcessor extends AbstractEducationClassScheduleProcessor<EducationCourseClassSchedulePageRequest, BaseResponse<PageResponseDTO<EducationCourseClassScheduleDTO>>> {

    @Override
    public BaseResponse<PageResponseDTO<EducationCourseClassScheduleDTO>> initResult() {
        BaseResponse<PageResponseDTO<EducationCourseClassScheduleDTO>> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(EducationCourseClassSchedulePageRequest request, BaseResponse<PageResponseDTO<EducationCourseClassScheduleDTO>> response) {
        request.initPage();
        EducationCourseClassSchedule educationCourseClassSchedule = EducationCourseClassScheduleAssembler.INSTANCE.convert(request);
        PageRequest pageRequest = reqToPage(request);

        PageResponse<List<EducationCourseClassSchedule>> listPageResponse = educationCourseClassScheduleRepository.listPage(educationCourseClassSchedule, pageRequest);

        PageResponseDTO<EducationCourseClassScheduleDTO> list = EducationCourseClassScheduleAssembler.INSTANCE.convertPage(listPageResponse);

        response.setData(list);
    }

}
