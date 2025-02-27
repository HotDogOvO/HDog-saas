package com.hotdog.saas.application.processor.education.clazz.enroll;

import com.hotdog.saas.application.entity.request.education.clazz.enroll.AssignEducationCourseClassEnrollRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.enums.education.CourseClassAssignStatusEnum;
import com.hotdog.saas.domain.enums.education.CourseClassPersonTypeEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.model.EducationCourseClassEnroll;
import com.hotdog.saas.domain.model.EducationCourseClassPerson;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseClassEnrollAssignProcessor extends AbstractEducationClassEnrollProcessor<AssignEducationCourseClassEnrollRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(AssignEducationCourseClassEnrollRequest request, BaseResponse<Boolean> response) {
        Long id = request.getId();
        super.existsByClassEnrollId(id);

        EducationCourseClassEnroll educationCourseClassEnroll = educationCourseClassEnrollRepository.findById(id);
        if (!educationCourseClassEnroll.isPay()) {
            throw new BusinessException("当前报名记录未支付，不能分配班级");
        }

        if(educationCourseClassEnroll.isAssign()){
            throw new BusinessException("当前报名记录已分配班级，不能重复分配");
        }

        // 1. 分配班级
        EducationCourseClassPerson educationCourseClassPerson = EducationCourseClassPerson.builder()
                .classNo(request.getClassNo())
                .type(CourseClassPersonTypeEnum.STUDENT.getCode())
                .peopleOpenId(request.getPeopleOpenId())
                .operator(request.getOperator())
                .build();

        educationCourseClassPersonRepository.save(educationCourseClassPerson);

        //2. 修改分配状态
        educationCourseClassEnroll.setClassNo(request.getClassNo());
        educationCourseClassEnroll.setAssignStatus(CourseClassAssignStatusEnum.ASSIGN.getCode());
        educationCourseClassEnroll.setOperator(request.getOperator());
        educationCourseClassEnrollRepository.modify(educationCourseClassEnroll);

        response.setData(Boolean.TRUE);
    }

}
