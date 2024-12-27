package com.hotdog.saas.application.entity.response.education;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "课程附件返回DTO", description = "课程附件返回DTO")
public class EducationCourseAttachDTO {

    @Schema(description = "附件类型")
    private String attachType;

    @Schema(description = "附件路径")
    private String attachUrl;

}
