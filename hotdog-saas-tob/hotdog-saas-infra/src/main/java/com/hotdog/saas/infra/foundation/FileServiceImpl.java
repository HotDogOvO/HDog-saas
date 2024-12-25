package com.hotdog.saas.infra.foundation;

import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.foundation.FileService;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileServiceImpl implements FileService {

    private final MinioClient minioClient;

    private final String minioBucketName;

    public FileServiceImpl(MinioClient minioClient, String minioBucketName) {
        this.minioClient = minioClient;
        this.minioBucketName = minioBucketName;
    }


    @Override
    public void upload(String fileName, InputStream inputStream, String contentType) {
        try {
            ObjectWriteResponse objectWriteResponse = minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(minioBucketName)
                            .object(fileName)
                            .stream(inputStream, -1, 10485760) // 可以设置流大小（这里是 10MB）
                            .contentType(contentType)
                            .build()
            );
        } catch (MinioException | InvalidKeyException | IOException | NoSuchAlgorithmException e) {
            log.error("文件上传失败，minio失败，原因是：{}", e.getMessage(), e);
            throw new BusinessException("文件上传失败");
        }
    }

    @Override
    public InputStream downloadFile(String fileName) {
        try {
            return minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(minioBucketName)
                            .object(fileName)
                            .build()
            );
        } catch (MinioException | InvalidKeyException | IOException | NoSuchAlgorithmException e) {
            log.error("文件下载失败，minio失败，原因是：{}", e.getMessage(), e);
            throw new BusinessException("文件下载失败");
        }
    }
}
