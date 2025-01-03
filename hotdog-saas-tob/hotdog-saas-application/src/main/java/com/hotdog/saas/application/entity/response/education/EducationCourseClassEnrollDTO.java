package com.hotdog.saas.application.entity.response.education;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "报名返回DTO", description = "报名返回DTO")
public class EducationCourseClassEnrollDTO {

    @Schema(description = "报名ID")
    private Long id;

    @Schema(description = "课程编号")
    private String courseNo;

    @Schema(description = "人员OpenId")
    private String peopleOpenId;

    @Schema(description = "支付状态（0：未支付 1：已支付）")
    private Integer payStatus;

    @Schema(description = "班级分配状态（0：未分配 1：已分配）")
    private Integer assignStatus;

    @Schema(description = "状态（1：报名成功 2：班级分配中 3：班级分配完成）")
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
