package com.hotdog.saas.application.processor.education;

import com.hotdog.saas.application.assembler.EducationCourseAssembler;
import com.hotdog.saas.application.assembler.UserAssembler;
import com.hotdog.saas.application.entity.request.education.QueryEducationCourseRequest;
import com.hotdog.saas.application.entity.request.user.QueryUserRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.education.EducationCourseDTO;
import com.hotdog.saas.application.entity.response.user.UserDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourse;
import com.hotdog.saas.domain.model.Role;
import com.hotdog.saas.domain.model.User;

import org.springframework.stereotype.Component;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationDetailProcessor extends AbstractEducationProcessor<QueryEducationCourseRequest, BaseResponse<EducationCourseDTO>> {

    @Override
    public BaseResponse<EducationCourseDTO> initResult() {
        BaseResponse<EducationCourseDTO> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(QueryEducationCourseRequest request, BaseResponse<EducationCourseDTO> response) {
        String courseNo = request.getCourseNo();
        super.exists(courseNo);
        EducationCourse educationCourse = educationCourseRepository.findByCourseNo(courseNo);
        EducationCourseDTO educationCourseDTO = EducationCourseAssembler.INSTANCE.convertToDTO(educationCourse);
        response.setData(educationCourseDTO);
    }

}
