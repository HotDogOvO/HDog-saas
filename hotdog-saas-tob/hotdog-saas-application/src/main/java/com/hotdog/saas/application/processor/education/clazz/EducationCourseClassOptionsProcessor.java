package com.hotdog.saas.application.processor.education.clazz;

import com.hotdog.saas.application.assembler.EducationCourseClassAssembler;
import com.hotdog.saas.application.entity.request.education.clazz.EducationCourseClassOptionsRequest;
import com.hotdog.saas.application.entity.request.education.clazz.QueryEducationCourseClassRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseClassOptionsDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourseClass;

import org.springframework.stereotype.Component;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseClassOptionsProcessor extends AbstractEducationClassProcessor<EducationCourseClassOptionsRequest, BaseResponse<List<EducationCourseClassOptionsDTO>>> {

    @Override
    public BaseResponse<List<EducationCourseClassOptionsDTO>> initResult() {
        BaseResponse<List<EducationCourseClassOptionsDTO>> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(EducationCourseClassOptionsRequest request, BaseResponse<List<EducationCourseClassOptionsDTO>> response) {
        String courseNo = request.getCourseNo();
        super.existsByCourseNo(courseNo);
        List<EducationCourseClass> list = educationCourseClassRepository.findByCourseNo(courseNo);
        List<EducationCourseClassOptionsDTO> educationCourseClassOptionsDTOList = EducationCourseClassAssembler.INSTANCE.convertOptions(list);
        response.setData(educationCourseClassOptionsDTOList);
    }

}
