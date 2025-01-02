package com.hotdog.saas.application.entity.request.education.clazz.trail;

import com.hotdog.saas.application.entity.request.PageRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "分页试课DTO", description = "分页试课DTO")
public class EducationCourseClassTrailPageRequest extends PageRequestParam {

    @Schema(description = "微信ID")
    @NotNull(message = "微信ID不能为空")
    private Integer wechatId;

}
