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

    /**
     * 文件复制抽象接口
     * 作用：将临时目录的文件转移到正式目录
     *
     * @param sourceFilePath 源文件地址
     * @param targetFilePath 目标文件地址
     */
    public abstract void copy(String sourceFilePath, String targetFilePath);

    public abstract String download(String filePath);

    public abstract void delete(String filePath);

    /**
     * 生成文件路径
     *
     * @param fileName     文件名
     * @param filePathEnum 文件类型
     * @return 文件路径
     */
    public abstract String generatorFilePath(String fileName, FilePathEnum filePathEnum);

    /**
     * 获取真实文件名（上传前的文件名）
     *
     * @param filePath 文件路径
     * @return 文件名
     */
    public abstract String getRealFileName(String filePath);

    /**
     * 生成文件名（uuid+源文件名）
     *
     * @param fileName 文件名
     * @return 文件名
     */
    public String generatorFileName(String fileName) {
        return UUID.randomUUID().toString().replace(Constants.MIDDLE_LINE, "") + Constants.MIDDLE_LINE + fileName;
    }
}
