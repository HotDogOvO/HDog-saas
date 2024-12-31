package com.hotdog.saas.application.processor.education.clazz;

import com.hotdog.saas.application.assembler.EducationCourseClassAssembler;
import com.hotdog.saas.application.entity.request.education.clazz.CreateEducationCourseClassRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourse;
import com.hotdog.saas.domain.model.EducationCourseClass;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseClassCreateProcessor extends AbstractEducationClassProcessor<CreateEducationCourseClassRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(CreateEducationCourseClassRequest request, BaseResponse<Boolean> response) {
        valid(request);
        EducationCourseClass educationCourseClass = buildEducationCourseClass(request);
        educationCourseClassRepository.save(educationCourseClass);
        response.setData(Boolean.TRUE);
    }

    private EducationCourseClass buildEducationCourseClass(CreateEducationCourseClassRequest request) {
        EducationCourseClass educationCourseClass = EducationCourseClassAssembler.INSTANCE.convert(request);
        // 生成业务编号
        educationCourseClass.generateBusinessNo();
        // 班级价格为空，使用课程价格
        if(Objects.isNull(request.getClassPrice())){
            EducationCourse educationCourse = educationCourseRepository.findByCourseNo(educationCourseClass.getCourseNo());
            educationCourseClass.setClassPrice(educationCourse.getCoursePrice());
        }
        return educationCourseClass;
    }

    private void valid(CreateEducationCourseClassRequest request){
        super.existsByCourseNo(request.getCourseNo());
        super.existsByClassName(request.getName(), request.getWechatId());
    }
}
