package com.hotdog.saas.application.entity.response.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "文件下载返回DTO", description = "文件下载返回DTO")
public class FileDownloadDTO {

    @Schema(description = "文件下载全路径")
    private String downloadUrl;

}
