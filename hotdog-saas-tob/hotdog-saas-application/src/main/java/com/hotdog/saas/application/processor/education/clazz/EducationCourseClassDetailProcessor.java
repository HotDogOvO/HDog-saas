package com.hotdog.saas.application.processor.education.clazz;

import com.hotdog.saas.application.entity.request.education.clazz.QueryEducationCourseClassRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourseClass;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseClassDetailProcessor extends AbstractEducationClassProcessor<QueryEducationCourseClassRequest, BaseResponse<EducationCourseClassDTO>> {

    @Override
    public BaseResponse<EducationCourseClassDTO> initResult() {
        BaseResponse<EducationCourseClassDTO> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(QueryEducationCourseClassRequest request, BaseResponse<EducationCourseClassDTO> response) {
        String classNo = request.getClassNo();
        super.existsByClassNo(classNo);
        EducationCourseClass educationCourseClass = educationCourseClassRepository.findByClassNo(classNo);
        EducationCourseClassDTO educationCourseClassDTO = buildEducationCourseClassDTO(educationCourseClass);
        response.setData(educationCourseClassDTO);
    }

}
