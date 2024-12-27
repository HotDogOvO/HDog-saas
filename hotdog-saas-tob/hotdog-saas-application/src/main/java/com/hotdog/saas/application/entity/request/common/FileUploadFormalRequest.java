package com.hotdog.saas.application.entity.request.common;

import com.hotdog.saas.application.entity.request.BaseRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Schema(name = "正式文件上传DTO", description = "正式文件上传DTO")
public class FileUploadFormalRequest extends BaseRequestParam {

    @NotEmpty(message = "临时文件路径不能为空")
    @Schema(description = "临时文件路径")
    private String tmpFilePath;

    @Override
    public void validate() {
    }
}
