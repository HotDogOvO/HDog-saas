package com.hotdog.saas.infra.foundation.file.handler;

import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.enums.common.FileMiddleEnum;
import com.hotdog.saas.domain.exception.BusinessException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileHandlerFactory {

    @Value("${project.file}")
    private String FILE_MIDDLE_TYPE;

    private final MinioHandler minioHandler;

    public FileHandlerFactory(MinioHandler minioHandler) {
        this.minioHandler = minioHandler;
    }

    /**
     * 获取文件处理器
     * @return fileHandler
     */
    public AbstractFileHandler getFileHandler() {
        FileMiddleEnum fileMiddleEnum = FileMiddleEnum.descToEnum(StringUtils.lowerCase(FILE_MIDDLE_TYPE));
        log.info("文件存储系统加载成功，当前文件存储系统为：{}", fileMiddleEnum);
        return switch (fileMiddleEnum) {
            case MINIO -> minioHandler;
            case UNKNOWN -> throw new BusinessException(ResultCodeEnum.FILE_MIDDLE_FAIL, "未知的文件系统类型：" + fileMiddleEnum);
        };
    }
}
