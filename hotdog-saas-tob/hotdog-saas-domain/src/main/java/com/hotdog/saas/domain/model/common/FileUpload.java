package com.hotdog.saas.domain.model.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileUpload {

    private String fileName;

    private String filePath;
}
