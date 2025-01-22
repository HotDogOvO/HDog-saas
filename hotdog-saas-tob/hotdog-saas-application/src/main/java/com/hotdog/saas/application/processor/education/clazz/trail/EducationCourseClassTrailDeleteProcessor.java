package com.hotdog.saas.application.processor.education.clazz.trail;

import com.hotdog.saas.application.entity.request.education.clazz.trail.DeleteEducationCourseClassTrailRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.model.EducationCourseClassEnroll;
import com.hotdog.saas.domain.model.EducationCourseClassTrail;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseClassTrailDeleteProcessor extends AbstractEducationClassTrailProcessor<DeleteEducationCourseClassTrailRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(DeleteEducationCourseClassTrailRequest request, BaseResponse<Boolean> response) {
        Long id = request.getId();
        super.existsByClassTrailId(id);

        EducationCourseClassTrail educationCourseClassTrail = educationCourseClassTrailRepository.findById(id);

        if(educationCourseClassTrail.isAssign()){
            throw new BusinessException("当前试课记录已分配班级，不能删除");
        }

        Integer modifyFlag = educationCourseClassTrailRepository.remove(id, request.getOperator());
        response.setData(checkFlag(modifyFlag));
    }

}
