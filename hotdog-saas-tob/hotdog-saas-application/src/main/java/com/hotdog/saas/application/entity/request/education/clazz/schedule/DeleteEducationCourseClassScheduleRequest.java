package com.hotdog.saas.application.entity.request.education.clazz.schedule;

import com.hotdog.saas.application.entity.request.BaseRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "删除课程表DTO", description = "删除课程表DTO")
public class DeleteEducationCourseClassScheduleRequest extends BaseRequestParam {

    @NotNull(message = "课程表ID不能为空")
    @Schema(description = "课程表ID")
    private Long id;

    @Override
    public void validate() {
    }
}
