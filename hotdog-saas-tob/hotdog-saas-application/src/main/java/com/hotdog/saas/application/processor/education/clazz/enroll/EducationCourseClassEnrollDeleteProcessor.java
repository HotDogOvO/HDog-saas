package com.hotdog.saas.application.processor.education.clazz.enroll;

import com.hotdog.saas.application.entity.request.education.clazz.enroll.DeleteEducationCourseClassEnrollRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.model.EducationCourseClassEnroll;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseClassEnrollDeleteProcessor extends AbstractEducationClassEnrollProcessor<DeleteEducationCourseClassEnrollRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(DeleteEducationCourseClassEnrollRequest request, BaseResponse<Boolean> response) {
        Long id = request.getId();
        super.existsByClassEnrollId(id);

        EducationCourseClassEnroll educationCourseClassEnroll = educationCourseClassEnrollRepository.findById(id);
        if(educationCourseClassEnroll.isPay()) {
            throw new BusinessException("当前报名记录已支付，不能删除");
        }

        if(educationCourseClassEnroll.isAssign()){
            throw new BusinessException("当前报名记录已分配班级，不能删除");
        }

        Integer modifyFlag = educationCourseClassEnrollRepository.remove(id, request.getOperator());
        response.setData(checkFlag(modifyFlag));
    }

}
