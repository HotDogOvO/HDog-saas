package com.hotdog.saas.application.entity.request.education.clazz;

import com.hotdog.saas.application.entity.request.BaseRequestParam;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "创建班级DTO", description = "创建班级DTO")
public class CreateEducationCourseClassRequest extends BaseRequestParam {

    @NotNull(message = "微信ID不能为空")
    @Schema(description = "微信ID")
    private Long wechatId;

    @NotBlank(message = "课程编号不能为空")
    @Schema(description = "课程编号")
    private String courseNo;

    @NotBlank(message = "班级名称不能为空")
    @Schema(description = "班级名称")
    private String name;

    @NotBlank(message = "课时不能为空")
    @Schema(description = "课时")
    private String classHour;

    @NotBlank(message = "上课时间不能为空")
    @Schema(description = "上课时间")
    private String classTime;

    @NotNull(message = "报名截止日期不能为空")
    @Schema(description = "报名截止日期")
    private LocalDateTime registrationDeadline;

    @Schema(description = "价格")
    private BigDecimal classPrice;

    @Override
    public void validate() {
    }
}
