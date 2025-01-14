package com.hotdog.saas.infra.foundation.file;

import com.hotdog.saas.domain.enums.common.FilePathEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.foundation.FileService;
import com.hotdog.saas.domain.model.common.FileUpload;
import com.hotdog.saas.infra.foundation.file.handler.AbstractFileHandler;
import com.hotdog.saas.infra.foundation.file.handler.FileHandlerFactory;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileServiceImpl implements FileService {

    private final AbstractFileHandler fileHandler;

    public FileServiceImpl(FileHandlerFactory fileHandlerFactory) {
        this.fileHandler = fileHandlerFactory.getFileHandler();
    }

    @Override
    public FileUpload uploadTmp(MultipartFile multipartFile) {
        try {
            String fileName = fileHandler.generatorFileName(multipartFile.getOriginalFilename());
            String filePath = fileHandler.generatorFilePath(fileName, FilePathEnum.TMP);
            fileHandler.upload(multipartFile.getInputStream(), filePath);
            return buildFileUpload(fileName, filePath);
        } catch (Exception e) {
            log.error("上传临时文件失败：", e);
            throw new BusinessException("文件上传失败");
        }
    }

    @Override
    public FileUpload uploadFormal(String tmpFilePath) {
        try {
            String realName = fileHandler.getRealFileName(tmpFilePath);
            String fileName = fileHandler.generatorFileName(realName);
            String filePath = fileHandler.generatorFilePath(fileName, FilePathEnum.FORMAL);

            fileHandler.copy(tmpFilePath, filePath);
            return buildFileUpload(fileName, filePath);
        } catch (Exception e) {
            log.error("上传正式文件失败：", e);
            throw new BusinessException("上传正式文件失败");
        }
    }

    @Override
    public String downloadFile(String filePath) {
        try{
            return fileHandler.download(filePath);
        } catch (Exception e){
            log.error("文件下载失败：", e);
            throw new BusinessException("文件下载失败");
        }
    }

    @Override
    public Boolean deleteFile(String filePath) {
        try {
            fileHandler.delete(filePath);
            return Boolean.TRUE;
        } catch (Exception e) {
            log.error("删除文件失败：", e);
            throw new BusinessException("删除文件失败");
        }
    }

}