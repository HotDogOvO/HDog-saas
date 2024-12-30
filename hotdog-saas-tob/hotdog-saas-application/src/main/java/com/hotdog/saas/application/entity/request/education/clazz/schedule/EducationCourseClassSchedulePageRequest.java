package com.hotdog.saas.application.entity.request.education.clazz.schedule;

import com.hotdog.saas.application.entity.request.PageRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "分页课程表DTO", description = "分页课程表DTO")
public class EducationCourseClassSchedulePageRequest extends PageRequestParam {

    @NotBlank(message = "班级编号不能为空")
    @Schema(description = "班级编号")
    private String classNo;

}
