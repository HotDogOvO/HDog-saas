package com.hotdog.saas.application.processor.education;

import com.hotdog.saas.application.assembler.EducationCourseAssembler;
import com.hotdog.saas.application.entity.request.education.CreateEducationCourseRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourse;
import com.hotdog.saas.domain.model.EducationCourseTypeRelation;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseCreateProcessor extends AbstractEducationProcessor<CreateEducationCourseRequest, BaseResponse<Boolean>> {

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
        valid(request);

        // 保存课程
        EducationCourse educationCourse = buildEducationCourse(request);
        Integer saveFlag = educationCourseRepository.save(educationCourse);

        // 保存课程分类
        EducationCourseTypeRelation educationCourseTypeRelation = EducationCourseTypeRelation.builder()
                .courseNo(educationCourse.getCourseNo())
                .typeId(request.getCourseTypeId())
                .build();
        educationCourseTypeRelationRepository.save(educationCourseTypeRelation);

        response.setData(checkFlag(saveFlag));
    }

    private EducationCourse buildEducationCourse(CreateEducationCourseRequest request){
        EducationCourse educationCourse = EducationCourseAssembler.INSTANCE.convert(request);
        // 生成业务编号
        educationCourse.generateBusinessNo();
        educationCourse.setTenantId(getTenantId());
        return educationCourse;
    }

    private void valid(CreateEducationCourseRequest request){
        super.existsByName(request.getName());
        // 校验课程类型
        super.existsByCourseTypeId(request.getCourseTypeId());
    }

}
