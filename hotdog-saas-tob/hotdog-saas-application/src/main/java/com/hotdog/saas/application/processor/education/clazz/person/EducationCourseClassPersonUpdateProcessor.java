package com.hotdog.saas.application.processor.education.clazz.person;

import com.hotdog.saas.application.assembler.EducationCourseClassPersonAssembler;
import com.hotdog.saas.application.assembler.EducationCourseClassScheduleAssembler;
import com.hotdog.saas.application.entity.request.education.clazz.person.UpdateEducationCourseClassPersonRequest;
import com.hotdog.saas.application.entity.request.education.clazz.schedule.UpdateEducationCourseClassScheduleRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourseClassPerson;
import com.hotdog.saas.domain.model.EducationCourseClassSchedule;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseClassPersonUpdateProcessor extends AbstractEducationClassPersonProcessor<UpdateEducationCourseClassPersonRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(UpdateEducationCourseClassPersonRequest request, BaseResponse<Boolean> response) {
        EducationCourseClassPerson educationCourseClassPerson = EducationCourseClassPersonAssembler.INSTANCE.convert(request);
        Integer modifyFlag = educationCourseClassPersonRepository.modify(educationCourseClassPerson);
        response.setData(checkFlag(modifyFlag));
    }

}
