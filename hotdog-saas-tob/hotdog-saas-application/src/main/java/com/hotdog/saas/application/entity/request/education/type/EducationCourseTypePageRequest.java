package com.hotdog.saas.application.entity.request.education.type;

import com.hotdog.saas.application.entity.request.PageRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "分页课程类型DTO", description = "分页课程类型DTO")
public class EducationCourseTypePageRequest extends PageRequestParam {

    @Schema(description = "微信ID")
    @NotNull(message = "微信ID不能为空")
    private Integer wechatId;

    @Schema(description = "课程名")
    private String name;

    @Schema(description = "课程状态")
    private Integer status;

}
