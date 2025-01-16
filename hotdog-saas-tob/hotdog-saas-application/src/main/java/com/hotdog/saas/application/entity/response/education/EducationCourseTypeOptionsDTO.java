package com.hotdog.saas.application.entity.response.education;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "课程类型下拉框返回DTO", description = "课程类型下拉框返回DTO")
public class EducationCourseTypeOptionsDTO {

    @Schema(description = "课程分类ID")
    private Long id;

    @Schema(description = "课程分类名")
    private String name;

    @Schema(description = "租户状态（0正常 1停用）")
    private Integer status;

}
