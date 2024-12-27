package com.hotdog.saas.application.processor.education.clazz;

import com.hotdog.saas.application.assembler.EducationCourseClassAssembler;
import com.hotdog.saas.application.entity.request.education.clazz.UpdateEducationCourseClassRequest;
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
public class EducationCourseClassUpdateProcessor extends AbstractEducationClassProcessor<UpdateEducationCourseClassRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(UpdateEducationCourseClassRequest request, BaseResponse<Boolean> response) {
        String classNo = request.getClassNo();
        valid(classNo);

        EducationCourseClass educationCourseClass = EducationCourseClassAssembler.INSTANCE.convert(request);
        Integer modifyFlag = educationCourseClassRepository.modify(educationCourseClass);

        response.setData(checkFlag(modifyFlag));
    }

    private void valid(String classNo){
        super.existsByClassNo(classNo);
        EducationCourseClass sourceEducationCourseClass = educationCourseClassRepository.findByClassNo(classNo);
        // 校验状态是否为[待开班]
        if(CourseClassStatusEnum.canUpdateClass(sourceEducationCourseClass.getStatus())){
            throw new BusinessException("当前班级状态不允许修改");
        }
    }

}
