package com.hotdog.saas.application.entity.response.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "文件上传返回DTO", description = "文件上传返回DTO")
public class FileUploadDTO {

    @Schema(description = "文件名")
    private String fileName;

    @Schema(description = "文件路径")
    private String filePath;
}
