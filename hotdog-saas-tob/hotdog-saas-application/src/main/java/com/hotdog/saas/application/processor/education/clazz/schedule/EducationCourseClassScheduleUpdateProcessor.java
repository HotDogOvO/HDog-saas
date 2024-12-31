package com.hotdog.saas.application.processor.education.clazz.schedule;

import com.hotdog.saas.application.assembler.EducationCourseClassScheduleAssembler;
import com.hotdog.saas.application.entity.request.education.clazz.schedule.UpdateEducationCourseClassScheduleRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.enums.education.CourseClassScheduleStatusEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.model.EducationCourseClassSchedule;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EducationCourseClassScheduleUpdateProcessor extends AbstractEducationClassScheduleProcessor<UpdateEducationCourseClassScheduleRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(UpdateEducationCourseClassScheduleRequest request, BaseResponse<Boolean> response) {
        Long id = request.getId();
        valid(id);
        super.removeCourseTaskDelayQueue(id);

        EducationCourseClassSchedule educationCourseClassSchedule = EducationCourseClassScheduleAssembler.INSTANCE.convert(request);
        Integer modifyFlag = educationCourseClassScheduleRepository.modify(educationCourseClassSchedule);

        super.pushCourseTaskDelayQueue(id, educationCourseClassSchedule.getClassBeginTime(), educationCourseClassSchedule.getClassEndTime());
        response.setData(checkFlag(modifyFlag));
    }

    private void valid(Long id){
        super.existsByClassScheduleId(id);
        EducationCourseClassSchedule educationCourseClassSchedule = educationCourseClassScheduleRepository.findById(id);
        if(CourseClassScheduleStatusEnum.canUpdateClassSchedule(educationCourseClassSchedule.getStatus())){
            throw new BusinessException("当前课程表状态不允许修改");
        }
    }

}
