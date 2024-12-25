package com.hotdog.saas.domain.utils;


import io.minio.*;
import io.minio.http.Method;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@Component
public class MinioUtils {

    private volatile static MinioClient minioClient;
    /** HTTP 文件默认访问时间 7天 */
    private static final int DEFAULT_EXPIRY_TIME = 7 * 24 * 3600;

    private static String url;
    private static String access;
    private static String secret;
    private static String bucket;

    private final Environment env;

    @Autowired
    public MinioUtils(Environment env) {
        this.env = env;
    }

    /**
     * 初始化minio参数
     */
    @PostConstruct
    public void initMinioConfig() {
        url = env.getProperty("minio.url");
        access = env.getProperty("minio.access");
        secret = env.getProperty("minio.secret");
        bucket = env.getProperty("minio.bucket");
        log.info("MINIO参数初始化，url:{}, access:{}, secret:{}, bucket:{}", url, access, secret, bucket);
        // 初始化Minio客户端
        initMinioClient();
    }

    /**
     * 获取minio客户端
     * @return io.minio.MinioClient
     */
    public static MinioClient getMinioClient(){
        if(minioClient == null){
            synchronized (MinioUtils.class){
                if(minioClient == null){
                    initMinioClient();
                }
            }
        }
        return minioClient;
    }

    /**
     * 初始化minio客户端
     * @return io.minio.MinioClient
     */
    private static MinioClient initMinioClient() {
        log.info("Minio文件系统初始化加载");
        // 初始化客户端
        minioClient = MinioClient.builder()
                .endpoint(url)
                .credentials(access, secret).build();

        // 判断bucket分区是否存在
        if(!existBucket(bucket)){
            // bucket分区不存在，创建分区
            log.info("bucket分区不存在：{}", bucket);
            createBucket(bucket);
        }
        log.info("Minio文件系统初始化完成");
        return minioClient;
    }

    /**
     * 判断文件分区是否存在
     * @param bucket 文件分区
     * @return boolean
     */
    private static boolean existBucket(String bucket) {
        boolean exists = false;
        try {
            exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
        } catch (Exception e) {
            log.error("Minio查询bucket失败：", e);
        }
        return exists;
    }

    /**
     * 创建文件分区
     * @param bucket 文件分区
     */
    private static void createBucket(String bucket){
        try{
            log.info("Minio创建bucket：{}", bucket);
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        } catch(Exception e){
            log.error("Minio创建bucket失败：", e);
        }
    }

    /**
     * 获取文件夹路径
     * @param fileType 枚举（TMP/FORMAL）
     * @return java.lang.String
     */
    private static String getBaseFilePath(String fileType){
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd/");
        return fileType + SystemConstant.SLASH + localDateTime.format(dateTimeFormatter);
    }

    /**
     * 获取真正的文件名
     * @param fileName 上传的文件名
     * @return java.lang.String
     */
    private static String getRelFileName(String fileName){
        String[] fileNameSplit = fileName.split("\\.");
        // 重新生成文件名 uuid+小数点+文件后缀
        String lastSuffix = fileNameSplit[fileNameSplit.length - 1];
        // ios的视频格式需要特殊处理
        if(lastSuffix.equals(SystemConstant.VIDEO)){
            lastSuffix = SystemConstant.MP4;
        }
        fileName = UUID.randomUUID() + SystemConstant.DECIMAL_POINT + lastSuffix;
        // 真正的文件名 文件路径/年/月/日/文件名
        return fileName;
    }

    /**
     * 上传临时文件
     * @param multipartFile 文件流
     * @return java.lang.String
     */
    public static String uploadTmp(MultipartFile multipartFile) {
        try{
            String fileName = multipartFile.getOriginalFilename();
            InputStream inputStream = multipartFile.getInputStream();
            String filePath = getBaseFilePath(FileTypeEnums.TMP.name());
            String relFileName = filePath + getRelFileName(fileName);
            return upload(inputStream, relFileName);
        } catch(Exception e){
            log.error("上传临时文件失败：", e);
        }
    }

    /**
     * 上传正式文件
     * @param tmpFilePath 临时文件地址
     * @return java.lang.String
     */
    public static String uploadFormal(String tmpFilePath){
        try{
            String formalFilePath = getBaseFilePath(FileTypeEnums.FORMAL.name());
            String[] tmpFilePathSplit = tmpFilePath.split("\\/");
            String relFileName = formalFilePath + tmpFilePathSplit[tmpFilePathSplit.length - 1];
            // 从临时目录转移到正式目录
            minioClient.copyObject(CopyObjectArgs.builder()
                    .bucket(bucket)
                    .object(relFileName)
                    .source(CopySource.builder()
                            .bucket(bucket)
                            .object(tmpFilePath)
                            .build())
                    .build());
            return relFileName;
        } catch(Exception e){
            log.error("上传正式文件失败：", e);
        }
    }

    /**
     * 上传文件
     * @param inputStream 文件流
     * @param fileName 文件名
     * @return java.lang.String
     */
    private static String upload(InputStream inputStream, String fileName) throws Exception {
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucket)
                .object(fileName)
                .stream(inputStream, inputStream.available(), -1)
                .build());
        return fileName;
    }

    /**
     * 根据文件路径获取文件
     * @param filePath 文件路径
     * @return java.io.InputStream
     */
    public static String getFileByPath(String filePath) {
        try{
            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(bucket)
                    .object(filePath)
                    .expiry(DEFAULT_EXPIRY_TIME)
                    .build());
        } catch(Exception e){
            log.error("获取文件路径失败：", e);
        }
    }

    /**
     * 判断文件是不是存在临时目录
     * @param filePath 文件路径
     * @return java.lang.String
     */
    public static String checkTmpPath(String filePath){
        if(filePath.startsWith(FileTypeEnums.TMP.name())){
            return uploadFormal(filePath);
        }
        return filePath;
    }
}
