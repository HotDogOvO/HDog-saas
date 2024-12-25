package com.hotdog.saas.application.entity.request.education.type;

import com.hotdog.saas.application.entity.request.BaseRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(name = "删除课程类型DTO", description = "删除课程类型DTO")
public class DeleteEducationCourseTypeRequest extends BaseRequestParam {

    @NotBlank(message = "课程类型ID不能为空")
    @Schema(description = "课程类型ID")
    private Long id;

    @Override
    public void validate() {
    }
}
