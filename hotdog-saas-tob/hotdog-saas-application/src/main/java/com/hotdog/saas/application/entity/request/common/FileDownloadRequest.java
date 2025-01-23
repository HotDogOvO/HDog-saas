package com.hotdog.saas.application.entity.request.common;

import com.hotdog.saas.application.entity.request.BaseRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Schema(name = "文件下载DTO", description = "文件下载DTO")
public class FileDownloadRequest extends BaseRequestParam {

    @NotEmpty(message = "文件路径不能为空")
    @Schema(description = "文件路径")
    private String filePath;

    @Override
    public void validate() {
    }
}
