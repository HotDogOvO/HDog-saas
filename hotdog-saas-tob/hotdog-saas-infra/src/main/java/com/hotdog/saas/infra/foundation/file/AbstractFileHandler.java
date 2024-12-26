package com.hotdog.saas.infra.foundation.file;

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
    protected abstract void upload(InputStream inputStream, String filePath);

    protected abstract void copy(String sourceFilePath, String targetFilePath);

    protected abstract String download(String filePath);

    protected abstract String generatorFilePath(String fileName, FilePathEnum filePathEnum);

    protected abstract String getRealFileName(String filePath);

    protected String generatorFileName(String fileName) {
        return UUID.randomUUID().toString().replace(Constants.MIDDLE_LINE, "") + Constants.MIDDLE_LINE + fileName;
    }
}
