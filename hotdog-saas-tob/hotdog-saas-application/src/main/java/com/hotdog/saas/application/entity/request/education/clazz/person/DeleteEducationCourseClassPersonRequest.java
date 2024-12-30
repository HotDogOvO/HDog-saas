package com.hotdog.saas.application.entity.request.education.clazz.person;

import com.hotdog.saas.application.entity.request.BaseRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "删除班级人员DTO", description = "删除班级人员DTO")
public class DeleteEducationCourseClassPersonRequest extends BaseRequestParam {

    @NotNull(message = "班级人员ID不能为空")
    @Schema(description = "班级人员ID")
    private Long id;

    @Override
    public void validate() {
    }
}
