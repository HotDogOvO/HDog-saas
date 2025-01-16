package com.hotdog.saas.application.entity.response.education;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "班级返回DTO", description = "班级返回DTO")
public class EducationCourseClassDTO {

    @Schema(description = "课程编号")
    private String courseNo;

    @Schema(description = "课程名称")
    private String courseName;

    @Schema(description = "班级编号")
    private String classNo;

    @Schema(description = "班级名称")
    private String className;

    @Schema(description = "课时")
    private String classHour;

    @Schema(description = "上课时间")
    private String classTime;

    @Schema(description = "班级开班日期")
    private LocalDateTime classStartTime;

    @Schema(description = "班级结束日期")
    private LocalDateTime classFinishTime;

    @Schema(description = "报名截止日期")
    private LocalDateTime registrationDeadline;

    @Schema(description = "班级价格")
    private BigDecimal classPrice;

    @Schema(description = "状态（1：待开班 2：开班中 3：结业 9：取消）")
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
