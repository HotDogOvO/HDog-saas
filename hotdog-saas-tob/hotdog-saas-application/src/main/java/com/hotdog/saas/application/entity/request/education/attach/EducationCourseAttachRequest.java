package com.hotdog.saas.application.entity.request.education.attach;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "课程附件DTO", description = "课程附件DTO")
public class EducationCourseAttachRequest {

    @NotNull(message = "附件类型不能为空")
    @Schema(description = "附件类型（1：封面，9：其他）")
    private Integer attachType;

    @NotBlank(message = "附件路径不能为空")
    @Schema(description = "附件路径（临时路径）")
    private String attachUrl;

    @NotBlank(message = "附件名不能为空")
    @Schema(description = "附件名")
    private String attachName;
}