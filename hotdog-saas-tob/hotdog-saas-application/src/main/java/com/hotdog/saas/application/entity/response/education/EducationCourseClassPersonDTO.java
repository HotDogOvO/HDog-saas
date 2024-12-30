package com.hotdog.saas.application.entity.response.education;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "班级人员返回DTO", description = "班级人员返回DTO")
public class EducationCourseClassPersonDTO {

    @NotNull(message = "课程表ID不能为空")
    @Schema(description = "课程表ID")
    private Long id;

    @NotBlank(message = "班级编号不能为空")
    @Schema(description = "班级编号")
    private String classNo;

    @NotNull(message = "人员类型不能为空")
    @Schema(description = "人员类型（1：教师 2：学员）")
    private Integer type;

    @NotNull(message = "人员OpenId不能为空")
    @Schema(description = "人员OpenId")
    private String peopleOpenId;

    @Schema(description = "创建人")
    private String creator;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人")
    private String updater;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
