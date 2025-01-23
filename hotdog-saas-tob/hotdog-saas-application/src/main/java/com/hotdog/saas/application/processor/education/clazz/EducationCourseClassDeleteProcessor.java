package com.hotdog.saas.application.processor.education.clazz;

import com.hotdog.saas.application.entity.request.education.clazz.DeleteEducationCourseClassRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.enums.education.CourseClassStatusEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.model.EducationCourseClass;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseClassDeleteProcessor extends AbstractEducationClassProcessor<DeleteEducationCourseClassRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(DeleteEducationCourseClassRequest request, BaseResponse<Boolean> response) {
        valid(request.getClassNo());
        Integer removeFlag = educationCourseClassRepository.remove(request.getClassNo(), request.getOperator());
        response.setData(checkFlag(removeFlag));
    }

    private void valid(String classNo){
        super.existsByClassNo(classNo);
        EducationCourseClass sourceEducationCourseClass = educationCourseClassRepository.findByClassNo(classNo);
        if(CourseClassStatusEnum.cantDeleteCourse(sourceEducationCourseClass.getStatus())){
            throw new BusinessException("当前班级状态不允许删除");
        }
    }

}
