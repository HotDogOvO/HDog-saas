package com.hotdog.saas.application.processor.education.clazz.trail;

import com.hotdog.saas.application.assembler.EducationCourseClassPersonAssembler;
import com.hotdog.saas.application.assembler.EducationCourseClassTrailAssembler;
import com.hotdog.saas.application.entity.request.education.clazz.person.EducationCourseClassPersonPageRequest;
import com.hotdog.saas.application.entity.request.education.clazz.trail.EducationCourseClassTrailPageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassPersonDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassTrailDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourseClassPerson;
import com.hotdog.saas.domain.model.EducationCourseClassTrail;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;

import org.springframework.stereotype.Component;

import java.util.List;

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

        response.setData(list);
    }

}
