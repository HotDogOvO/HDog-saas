package com.hotdog.saas.application.entity.request.common;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.domain.exception.BusinessException;

import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "临时文件上传DTO", description = "临时文件上传DTO")
public class FileUploadTmpRequest extends BaseRequestParam {

    @Schema(description = "文件")
    private MultipartFile file;

    @Override
    public void validate() {
        if(Objects.isNull(file)){
            throw new BusinessException("文件不能为空");
        }
    }
}
