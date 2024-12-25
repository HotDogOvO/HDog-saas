package com.hotdog.saas.domain.foundation;


import java.io.InputStream;

public interface FileService {

    void upload(String fileName, InputStream inputStream, String contentType);

    InputStream downloadFile(String fileName);
}
