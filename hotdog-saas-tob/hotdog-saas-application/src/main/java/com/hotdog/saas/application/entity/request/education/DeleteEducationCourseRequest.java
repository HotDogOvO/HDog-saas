package com.hotdog.saas.application.entity.request.education;

import com.hotdog.saas.application.entity.request.BaseRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(name = "删除课程DTO", description = "删除课程DTO")
public class DeleteEducationCourseRequest extends BaseRequestParam {

    @NotBlank(message = "课程编号不能为空")
    @Schema(description = "课程编号")
    private String courseNo;

    @Override
    public void validate() {
    }
}
