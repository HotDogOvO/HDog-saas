package com.hotdog.saas.application.entity.request.education.clazz.signin;

import com.hotdog.saas.application.entity.request.PageRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "分页签到DTO", description = "分页签到DTO")
public class EducationCourseClassSignInPageRequest extends PageRequestParam {

    @Schema(description = "微信ID")
    @NotNull(message = "微信ID不能为空")
    private Integer wechatId;

    @Schema(description = "课程编号")
    private String courseNo;

    @Schema(description = "班级编号")
    private String classNo;

    @Schema(description = "签到状态")
    private Integer status;
}
