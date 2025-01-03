package com.hotdog.saas.application.entity.request.education;

import com.hotdog.saas.application.entity.request.PageRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "分页课程DTO", description = "分页课程DTO")
public class EducationCoursePageRequest extends PageRequestParam {

    @Schema(description = "微信ID")
    @NotNull(message = "微信ID不能为空")
    private Integer wechatId;

}