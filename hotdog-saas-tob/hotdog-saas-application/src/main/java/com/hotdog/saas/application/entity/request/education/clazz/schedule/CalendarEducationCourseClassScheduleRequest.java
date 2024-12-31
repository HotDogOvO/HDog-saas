package com.hotdog.saas.application.entity.request.education.clazz.schedule;

import com.hotdog.saas.application.entity.request.BaseRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "课程表日历DTO", description = "课程表日历DTO")
public class CalendarEducationCourseClassScheduleRequest extends BaseRequestParam {

    @NotNull(message = "班级编号不能为空")
    @Schema(description = "班级编号")
    private String classNo;

    @Override
    public void validate() {
    }
}
