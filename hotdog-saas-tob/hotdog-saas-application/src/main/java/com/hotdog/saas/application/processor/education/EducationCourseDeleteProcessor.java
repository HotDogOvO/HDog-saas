package com.hotdog.saas.application.processor.education;

import com.hotdog.saas.application.entity.request.education.DeleteEducationCourseRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.enums.education.CourseClassStatusEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.model.EducationCourseClass;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseDeleteProcessor extends AbstractEducationProcessor<DeleteEducationCourseRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(DeleteEducationCourseRequest request, BaseResponse<Boolean> response) {
        String courseNo = request.getCourseNo();
        valid(courseNo);
        Integer removeFlag = educationCourseRepository.remove(request.getCourseNo(), request.getOperator());
        response.setData(checkFlag(removeFlag));
    }

    private void valid(String courseNo) {
        // 1. 校验课程是否存在
        super.existsByCourseNo(courseNo);
        // 2. 校验是否关联班级
        List<EducationCourseClass> classList = educationCourseClassRepository.findByCourseNo(courseNo);
        long count = classList.stream().filter(courseClass -> CourseClassStatusEnum.cantDeleteCourse(courseClass.getStatus())).count();
        if (count > 0) {
            String cantDeleteStatusStr = CourseClassStatusEnum.cantDeleteCourseStatusList().stream()
                    .map(CourseClassStatusEnum::getDesc)
                    .collect(Collectors.joining(","));

            String errorMessage = String.format("当前课程下存在【%s】的班级，不允许删除", cantDeleteStatusStr);
            throw new BusinessException(errorMessage);
        }
    }

}
