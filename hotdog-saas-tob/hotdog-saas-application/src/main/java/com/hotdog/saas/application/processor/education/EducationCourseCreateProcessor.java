package com.hotdog.saas.application.processor.education;

import com.hotdog.saas.application.assembler.EducationCourseAssembler;
import com.hotdog.saas.application.assembler.EducationCourseAttachAssembler;
import com.hotdog.saas.application.entity.request.education.CreateEducationCourseRequest;
import com.hotdog.saas.application.entity.request.education.attach.EducationCourseAttachRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.EducationCourse;
import com.hotdog.saas.domain.model.EducationCourseAttach;
import com.hotdog.saas.domain.model.EducationCourseTypeRelation;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

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

        // 1. 保存课程
        EducationCourse educationCourse = buildEducationCourse(request);
        Integer saveFlag = educationCourseRepository.save(educationCourse);

        // 2. 保存课程分类
        String courseNo = educationCourse.getCourseNo();
        EducationCourseTypeRelation educationCourseTypeRelation = EducationCourseTypeRelation.builder().courseNo(courseNo).typeId(request.getCourseTypeId()).build();
        educationCourseTypeRelationRepository.save(educationCourseTypeRelation);

        // 3. 保存课程附件
        List<EducationCourseAttachRequest> attachList = request.getAttachList();
        List<EducationCourseAttach> educationCourseAttachList = EducationCourseAttachAssembler.INSTANCE.convert2List(attachList);
        educationCourseAttachList = educationCourseAttachList.stream().peek(x -> {
            x.setCourseNo(courseNo);
            x.setOperator(request.getOperator());
        }).collect(Collectors.toList());
        educationCourseAttachRepository.batchSave(educationCourseAttachList);

        response.setData(checkFlag(saveFlag));
    }

    private EducationCourse buildEducationCourse(CreateEducationCourseRequest request) {
        EducationCourse educationCourse = EducationCourseAssembler.INSTANCE.convert(request);
        // 生成业务编号
        educationCourse.generateBusinessNo();
        educationCourse.setTenantId(getTenantId());
        return educationCourse;
    }

    private void valid(CreateEducationCourseRequest request) {
        super.existsByName(request.getName());
        // 校验课程类型
        super.existsByCourseTypeId(request.getCourseTypeId());
    }

}
