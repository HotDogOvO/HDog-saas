package com.hotdog.saas.application.entity.request.education.clazz.enroll;

import com.hotdog.saas.application.entity.request.BaseRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "删除报名DTO", description = "删除报名DTO")
public class DeleteEducationCourseClassEnrollRequest extends BaseRequestParam {

    @NotNull(message = "报名ID不能为空")
    @Schema(description = "报名ID")
    private Long id;

    @Override
    public void validate() {
    }
}
