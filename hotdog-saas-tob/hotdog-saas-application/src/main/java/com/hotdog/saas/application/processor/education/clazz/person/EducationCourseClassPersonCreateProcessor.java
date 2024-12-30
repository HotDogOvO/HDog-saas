package com.hotdog.saas.application.processor.education.clazz.person;

import com.hotdog.saas.application.assembler.EducationCourseClassPersonAssembler;
import com.hotdog.saas.application.assembler.EducationCourseClassScheduleAssembler;
import com.hotdog.saas.application.entity.request.education.clazz.person.CreateEducationCourseClassPersonRequest;
import com.hotdog.saas.application.entity.request.education.clazz.schedule.CreateEducationCourseClassScheduleRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourseClassPerson;
import com.hotdog.saas.domain.model.EducationCourseClassSchedule;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseClassPersonCreateProcessor extends AbstractEducationClassPersonProcessor<CreateEducationCourseClassPersonRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(CreateEducationCourseClassPersonRequest request, BaseResponse<Boolean> response) {
        EducationCourseClassPerson educationCourseClassPerson = EducationCourseClassPersonAssembler.INSTANCE.convert(request);
        Integer saveFlag = educationCourseClassPersonRepository.save(educationCourseClassPerson);
        response.setData(checkFlag(saveFlag));
    }

}
