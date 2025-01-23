package com.hotdog.saas.application.entity.request.education.clazz.trail;

import com.hotdog.saas.application.entity.request.BaseRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "分配试课班级DTO", description = "分配试课班级DTO")
public class AssignEducationCourseClassTrailRequest extends BaseRequestParam {

    @NotNull(message = "试课ID不能为空")
    @Schema(description = "试课ID")
    private Long id;

    @NotNull(message = "微信ID不能为空")
    @Schema(description = "微信ID")
    private Long wechatId;

    @NotBlank(message = "班级编号不能为空")
    @Schema(description = "班级编号")
    private String classNo;

    @NotNull(message = "课时ID不能为空")
    @Schema(description = "课时ID")
    private Long classScheduleId;

    @Schema(description = "人员ID")
    private String peopleOpenId;

    @Override
    public void validate() {
    }
}
