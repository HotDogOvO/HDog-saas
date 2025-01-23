package com.hotdog.saas.application.entity.response.education;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "签到返回DTO", description = "签到返回DTO")
public class EducationCourseClassSignInDTO {

    @Schema(description = "签到ID")
    private Long id;

    @Schema(description = "课程编号")
    private String courseNo;

    @Schema(description = "课程名")
    private String courseName;

    @Schema(description = "班级编号")
    private String classNo;

    @Schema(description = "班级名")
    private String className;

    @Schema(description = "人员OpenId")
    private String peopleOpenId;

    @Schema(description = "课时ID")
    private Long classScheduleId;

    @Schema(description = "课时名")
    private String classScheduleName;

    @Schema(description = "签到时间")
    private LocalDateTime signInTime;

    @Schema(description = "状态（1：签到成功 2：签到失败）")
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
