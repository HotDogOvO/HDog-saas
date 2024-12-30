package com.hotdog.saas.application.processor.education.clazz.person;

import com.hotdog.saas.application.assembler.EducationCourseClassPersonAssembler;
import com.hotdog.saas.application.assembler.EducationCourseClassScheduleAssembler;
import com.hotdog.saas.application.entity.request.education.clazz.person.EducationCourseClassPersonPageRequest;
import com.hotdog.saas.application.entity.request.education.clazz.schedule.EducationCourseClassSchedulePageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassPersonDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassScheduleDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourseClassPerson;
import com.hotdog.saas.domain.model.EducationCourseClassSchedule;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;

import org.springframework.stereotype.Component;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseClassPersonListProcessor extends AbstractEducationClassPersonProcessor<EducationCourseClassPersonPageRequest, BaseResponse<PageResponseDTO<EducationCourseClassPersonDTO>>> {

    @Override
    public BaseResponse<PageResponseDTO<EducationCourseClassPersonDTO>> initResult() {
        BaseResponse<PageResponseDTO<EducationCourseClassPersonDTO>> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(EducationCourseClassPersonPageRequest request, BaseResponse<PageResponseDTO<EducationCourseClassPersonDTO>> response) {
        request.initPage();
        EducationCourseClassPerson educationCourseClassPerson = EducationCourseClassPersonAssembler.INSTANCE.convert(request);
        PageRequest pageRequest = reqToPage(request);

        PageResponse<List<EducationCourseClassPerson>> listPageResponse = educationCourseClassPersonRepository.listPage(educationCourseClassPerson, pageRequest);

        PageResponseDTO<EducationCourseClassPersonDTO> list = EducationCourseClassPersonAssembler.INSTANCE.convertPage(listPageResponse);

        response.setData(list);
    }

}
