package com.hotdog.saas.application.processor.education;

import com.hotdog.saas.application.entity.request.education.QueryEducationCourseRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.education.EducationCourseDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourse;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseDetailProcessor extends AbstractEducationProcessor<QueryEducationCourseRequest, BaseResponse<EducationCourseDTO>> {

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
        super.existsByCourseNo(courseNo);
        EducationCourse educationCourse = educationCourseRepository.findByCourseNo(courseNo);
        EducationCourseDTO educationCourseDTO = super.convertEducationCourseDTO(educationCourse);
        response.setData(educationCourseDTO);
    }

}
