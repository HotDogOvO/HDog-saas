package com.hotdog.saas.application.entity.request.education.clazz.schedule;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.domain.exception.BusinessException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "创建课程表DTO", description = "创建课程表DTO")
public class CreateEducationCourseClassScheduleRequest extends BaseRequestParam {

    @NotBlank(message = "班级编号不能为空")
    @Schema(description = "班级编号")
    private String classNo;

    @NotBlank(message = "课时名不能为空")
    @Schema(description = "课时名")
    private String classHoursName;

    @NotNull(message = "课程开始时间不能为空")
    @Schema(description = "课程开始时间")
    private LocalDateTime classBeginTime;

    @NotNull(message = "课程结束时间不能为空")
    @Schema(description = "课程结束时间")
    private LocalDateTime classEndTime;

    @Override
    public void validate() {
        if(classBeginTime.isAfter(classEndTime)) {
            throw new BusinessException("课程开始时间不能晚于课程结束时间");
        }
    }
}
