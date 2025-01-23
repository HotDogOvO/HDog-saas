package com.hotdog.saas.application.facade.impl;

import com.hotdog.saas.application.entity.request.common.FileDownloadRequest;
import com.hotdog.saas.application.entity.request.common.FileUploadFormalRequest;
import com.hotdog.saas.application.entity.request.common.FileUploadTmpRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.common.FileDownloadDTO;
import com.hotdog.saas.application.entity.response.common.FileUploadDTO;
import com.hotdog.saas.application.facade.CommonFacade;
import com.hotdog.saas.application.processor.BaseProcessor;
import com.hotdog.saas.application.processor.common.FileDownloadProcessor;
import com.hotdog.saas.application.processor.common.FileUploadFormalProcessor;
import com.hotdog.saas.application.processor.common.FileUploadTmpProcessor;

import org.springframework.stereotype.Component;

@Component
public class CommonFacadeImpl extends BaseProcessor implements CommonFacade {

    private final FileUploadTmpProcessor fileUploadTmpProcessor;
    private final FileUploadFormalProcessor fileUploadFormalProcessor;
    private final FileDownloadProcessor fileDownloadProcessor;

    public CommonFacadeImpl(FileUploadTmpProcessor fileUploadTmpProcessor, FileUploadFormalProcessor fileUploadFormalProcessor, FileDownloadProcessor fileDownloadProcessor) {
        this.fileUploadTmpProcessor = fileUploadTmpProcessor;
        this.fileUploadFormalProcessor = fileUploadFormalProcessor;
        this.fileDownloadProcessor = fileDownloadProcessor;
    }

    @Override
    public BaseResponse<FileUploadDTO> fileUploadTmp(FileUploadTmpRequest request) {
        return this.doBiz(request, fileUploadTmpProcessor);
    }

    @Override
    public BaseResponse<FileUploadDTO> fileUploadFormal(FileUploadFormalRequest request) {
        return this.doBiz(request, fileUploadFormalProcessor);
    }

    @Override
    public BaseResponse<FileDownloadDTO> fileDownload(FileDownloadRequest request) {
        return this.doBiz(request, fileDownloadProcessor);
    }
}
