package com.hotdog.saas.application.entity.request.education.clazz.signin;

import com.hotdog.saas.application.entity.request.BaseRequestParam;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "修改签到DTO", description = "修改签到DTO")
public class UpdateEducationCourseClassSignInRequest extends BaseRequestParam {

    @NotNull(message = "签到ID不能为空")
    @Schema(description = "签到ID")
    private Long id;

    @Schema(description = "签到时间")
    private LocalDateTime signInTime;

    @Override
    public void validate() {
    }
}
