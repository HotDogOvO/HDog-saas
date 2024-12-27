package com.hotdog.saas.application.entity.request.education.clazz;

import com.hotdog.saas.application.entity.request.BaseRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(name = "班级开班DTO", description = "班级开班DTO")
public class StartEducationCourseClassRequest extends BaseRequestParam {

    @NotBlank(message = "班级编号不能为空")
    @Schema(description = "班级编号")
    private String classNo;

    @Override
    public void validate() {
    }
}
