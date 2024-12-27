package com.hotdog.saas.application.entity.response.education;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "课程类型返回DTO", description = "课程类型返回DTO")
public class EducationCourseTypeDTO {

    @Schema(description = "课程分类ID")
    private Long id;

    @Schema(description = "微信ID")
    private Long wechatId;

    @Schema(description = "课程分类名")
    private String name;

    @Schema(description = "课程分类备注")
    private String remark;

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
