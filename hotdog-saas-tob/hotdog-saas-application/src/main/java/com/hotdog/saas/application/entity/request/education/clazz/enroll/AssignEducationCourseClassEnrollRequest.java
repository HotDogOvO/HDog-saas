package com.hotdog.saas.application.entity.request.education.clazz.enroll;

import com.hotdog.saas.application.entity.request.BaseRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "分配报名班级DTO", description = "分配报名班级DTO")
public class AssignEducationCourseClassEnrollRequest extends BaseRequestParam {

    @NotNull(message = "报名ID不能为空")
    @Schema(description = "报名ID")
    private Long id;

    @NotNull(message = "微信ID不能为空")
    @Schema(description = "微信ID")
    private Long wechatId;

    @NotBlank(message = "班级编号不能为空")
    @Schema(description = "班级编号")
    private String classNo;

    @NotBlank(message = "人员openId不能为空")
    @Schema(description = "人员openId")
    private String peopleOpenId;

    @Override
    public void validate() {
    }
}
