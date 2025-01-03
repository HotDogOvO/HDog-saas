package com.hotdog.saas.application.entity.request.education.clazz.trail;

import com.hotdog.saas.application.entity.request.BaseRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "删除试课DTO", description = "删除试课DTO")
public class DeleteEducationCourseClassTrailRequest extends BaseRequestParam {

    @NotNull(message = "试课ID不能为空")
    @Schema(description = "试课ID")
    private Long id;

    @Override
    public void validate() {
    }
}
