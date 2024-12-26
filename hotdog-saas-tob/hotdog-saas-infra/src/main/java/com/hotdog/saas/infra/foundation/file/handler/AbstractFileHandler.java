package com.hotdog.saas.infra.foundation.file.handler;

import com.hotdog.saas.domain.constant.Constants;
import com.hotdog.saas.domain.enums.common.FilePathEnum;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public abstract class AbstractFileHandler {

    /**
     * 文件上传抽象接口
     *
     * @param inputStream 文件流
     * @param filePath    文件路径（不同实现有不同的路径）
     * @return 文件路径
     */
    public abstract void upload(InputStream inputStream, String filePath);

    public abstract void copy(String sourceFilePath, String targetFilePath);

    public abstract String download(String filePath);

    public abstract String generatorFilePath(String fileName, FilePathEnum filePathEnum);

    public abstract String getRealFileName(String filePath);

    public String generatorFileName(String fileName) {
        return UUID.randomUUID().toString().replace(Constants.MIDDLE_LINE, "") + Constants.MIDDLE_LINE + fileName;
    }
}
