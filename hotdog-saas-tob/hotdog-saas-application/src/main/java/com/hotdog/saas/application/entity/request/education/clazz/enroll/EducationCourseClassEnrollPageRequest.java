package com.hotdog.saas.application.entity.request.education.clazz.enroll;

import com.hotdog.saas.application.entity.request.PageRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "分页报名DTO", description = "分页报名DTO")
public class EducationCourseClassEnrollPageRequest extends PageRequestParam {

    @Schema(description = "微信ID")
    @NotNull(message = "微信ID不能为空")
    private Integer wechatId;

    @Schema(description = "支付状态")
    private Integer payStatus;

    @Schema(description = "班级分配状态")
    private Integer assignStatus;

    @Schema(description = "状态")
    private Integer status;

}
