package com.hotdog.saas.application.entity.request.education.clazz.signin;

import com.hotdog.saas.application.entity.request.BaseRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "删除签到DTO", description = "删除签到DTO")
public class DeleteEducationCourseClassSignInRequest extends BaseRequestParam {

    @NotNull(message = "签到ID不能为空")
    @Schema(description = "签到ID")
    private Long id;

    @Override
    public void validate() {
    }
}
