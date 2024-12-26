package com.hotdog.saas.domain.foundation;

import com.hotdog.saas.domain.model.common.FileUpload;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileService {

    FileUpload uploadTmp(MultipartFile multipartFile);

    FileUpload uploadFormal(String tmpFilePath);

    InputStream downloadFile(String fileName);

    /**
     * 构建文件上传领域对象
     * @param fileName 文件名
     * @param filePath 文件路径
     * @return 文件上传对象
     */
    default FileUpload buildFileUpload(String fileName, String filePath){
        return FileUpload.builder().fileName(fileName).filePath(filePath).build();
    }
}
