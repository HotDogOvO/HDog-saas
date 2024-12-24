package com.hotdog.saas.application.entity.response.education;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Schema(name = "课程返回DTO", description = "课程返回DTO")
public class EducationCourseDTO {

    @Schema(description = "课程编号")
    private String courseNo;

    @Schema(description = "课程名称")
    private String name;

    @Schema(description = "课程描述")
    private String description;

    @Schema(description = "课程类型（1:线下 2：线上）")
    private Integer courseType;

    @Schema(description = "课程价格")
    private BigDecimal coursePrice;

    @Schema(description = "状态（0正常 1停用）")
    private Integer status;

    @Schema(description = "创建人")
    private String creator;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人")
    private String updater;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
