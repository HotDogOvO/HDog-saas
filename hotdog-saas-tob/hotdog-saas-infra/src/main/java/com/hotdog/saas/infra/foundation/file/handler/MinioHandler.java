package com.hotdog.saas.infra.foundation.file.handler;

import com.hotdog.saas.domain.config.MinioConfig;
import com.hotdog.saas.domain.constant.Constants;
import com.hotdog.saas.domain.enums.common.FilePathEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.utils.DateUtils;

import org.springframework.stereotype.Component;

import java.io.InputStream;

import io.minio.BucketExistsArgs;
import io.minio.CopyObjectArgs;
import io.minio.CopySource;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MinioHandler extends AbstractFileHandler {

    private final MinioClient minioClient;
    private final String minioBucketName;
    private final String minioUrl;

    public MinioHandler(MinioClient minioClient, String minioBucketName, String minioUrl) {
        this.minioClient = minioClient;
        this.minioUrl = minioUrl;
        this.minioBucketName = minioBucketName;
        initMinioBucket();
    }

    @Override
    public void upload(InputStream inputStream, String filePath) {
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(minioBucketName)
                    .object(filePath)
                    .stream(inputStream, inputStream.available(), -1)
                    .build());
        } catch (Exception e) {
            log.error("minio文件上传失败，原因是：{}", e.getMessage(), e);
            throw new BusinessException("minio文件上传失败");
        }
    }

    @Override
    public void copy(String sourceFilePath, String targetFilePath) {
        try {
            minioClient.copyObject(CopyObjectArgs.builder()
                    .bucket(minioBucketName)
                    .object(targetFilePath)
                    .source(CopySource.builder()
                            .bucket(minioBucketName)
                            .object(sourceFilePath)
                            .build())
                    .build());
        } catch (Exception e) {
            log.error("minio文件复制失败，原因是：{}", e.getMessage(), e);
            throw new BusinessException("minio文件复制失败");
        }
    }

    @Override
    public String download(String filePath) {
        try {
            return minioUrl + Constants.SLASH + minioBucketName + Constants.SLASH + filePath;
        } catch (Exception e) {
            log.error("minio文件查询失败，原因是：{}", e.getMessage(), e);
            throw new BusinessException("minio文件查询失败");
        }
    }

    @Override
    public void delete(String filePath) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(minioBucketName)
                    .object(filePath)
                    .build());
        } catch (Exception e) {
            log.error("minio文件删除失败，原因是：{}", e.getMessage(), e);
            throw new BusinessException("minio文件删除失败");
        }
    }

    @Override
    public String generatorFilePath(String fileName, FilePathEnum filePathEnum) {
        String formatDate = DateUtils.getFormatDate(DateUtils.YYYY_MM_DD_SLASH);
        // 格式：tmp/2024/12/26/(uuid)-test.txt
        return filePathEnum.getDesc() + Constants.SLASH + formatDate + Constants.SLASH + fileName;
    }

    /**
     * 根据文件路径获取真实文件名
     * 文件路径格式：tmp/2024/12/26/(uuid)-test.txt
     *
     * @param filePath 文件路径
     * @return 真实文件名
     */
    @Override
    public String getRealFileName(String filePath) {
        int firstDashIndex = filePath.indexOf('-');
        if (firstDashIndex != -1) {
            return filePath.substring(firstDashIndex + 1);
        }
        return filePath;
    }

    /**
     * 初始化bucket
     */
    private void initMinioBucket() {
        // 判断bucket分区是否存在
        if (!existBucket(minioBucketName)) {
            // bucket分区不存在，创建分区
            log.info("bucket分区不存在：{}，尝试创建分区", minioBucketName);
            createBucket(minioBucketName);
        }
    }

    /**
     * 创建文件分区
     *
     * @param bucket 文件分区
     */
    private void createBucket(String bucket) {
        try {
            log.info("Minio创建bucket：{}", bucket);
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        } catch (Exception e) {
            log.error("Minio创建bucket失败：", e);
        }
    }

    /**
     * 判断文件分区是否存在
     *
     * @param bucket 文件分区
     * @return boolean
     */
    private boolean existBucket(String bucket) {
        boolean exists = false;
        try {
            exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
        } catch (Exception e) {
            log.error("Minio查询bucket失败：", e);
        }
        return exists;
    }

}
