package com.hotdog.saas.application.processor.education.clazz.trail;

import com.hotdog.saas.application.assembler.EducationCourseClassTrailAssembler;
import com.hotdog.saas.application.entity.request.education.clazz.trail.AssignEducationCourseClassTrailRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourseClassTrail;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseClassTrailAssignProcessor extends AbstractEducationClassTrailProcessor<AssignEducationCourseClassTrailRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(AssignEducationCourseClassTrailRequest request, BaseResponse<Boolean> response) {
        Long id = request.getId();
        super.existsByClassTrailId(id);
        EducationCourseClassTrail educationCourseClassTrail = EducationCourseClassTrailAssembler.INSTANCE.convert(request);

        Integer modifyFlag = educationCourseClassTrailRepository.modify(educationCourseClassTrail);
        response.setData(checkFlag(modifyFlag));
    }

}
