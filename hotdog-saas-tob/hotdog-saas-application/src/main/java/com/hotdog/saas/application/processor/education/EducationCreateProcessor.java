package com.hotdog.saas.application.processor.education;

import com.hotdog.saas.application.assembler.EducationCourseAssembler;
import com.hotdog.saas.application.assembler.UserAssembler;
import com.hotdog.saas.application.entity.request.education.CreateEducationCourseRequest;
import com.hotdog.saas.application.entity.request.user.CreateUserRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.constant.Constants;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourse;
import com.hotdog.saas.domain.model.User;
import com.hotdog.saas.domain.service.PasswordService;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCreateProcessor extends AbstractEducationProcessor<CreateEducationCourseRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(CreateEducationCourseRequest request, BaseResponse<Boolean> response) {
        super.existsByName(request.getName());

        EducationCourse educationCourse = buildEducationCourse(request);

        Integer saveFlag = educationCourseRepository.save(educationCourse);
        response.setData(checkFlag(saveFlag));
    }

    private EducationCourse buildEducationCourse(CreateEducationCourseRequest request){
        EducationCourse educationCourse = EducationCourseAssembler.INSTANCE.convert(request);
        // 生成业务编号
        educationCourse.generateBusinessNo();
        educationCourse.setTenantId(getTenantId());
        return educationCourse;
    }

}
