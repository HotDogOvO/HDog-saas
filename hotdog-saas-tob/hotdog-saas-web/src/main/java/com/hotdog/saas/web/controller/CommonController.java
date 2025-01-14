package com.hotdog.saas.web.controller;

import com.hotdog.saas.application.entity.request.common.FileDownloadRequest;
import com.hotdog.saas.application.entity.request.common.FileUploadFormalRequest;
import com.hotdog.saas.application.entity.request.common.FileUploadTmpRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.common.FileDownloadDTO;
import com.hotdog.saas.application.entity.response.common.FileUploadDTO;
import com.hotdog.saas.application.facade.CommonFacade;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "公用接口管理")
@RestController
@RequestMapping("/api/hotdog/v1/common")
public class CommonController {

    private final CommonFacade commonFacade;

    public CommonController(CommonFacade commonFacade) {
        this.commonFacade = commonFacade;
    }

    @Operation(summary = "上传临时文件")
    @PostMapping("/file/upload-tmp")
    public BaseResponse<FileUploadDTO> fileUploadTmp(@RequestPart("file") MultipartFile file) {
        FileUploadTmpRequest request = FileUploadTmpRequest.builder().file(file).build();
        return commonFacade.fileUploadTmp(request);
    }

    @Operation(summary = "上传正式文件")
    @PostMapping("/file/upload-formal")
    public BaseResponse<FileUploadDTO> fileUploadFormal(@RequestBody @Valid FileUploadFormalRequest request) {
        return commonFacade.fileUploadFormal(request);
    }

    @PostMapping("/file/download")
    public BaseResponse<FileDownloadDTO> fileDownload(@RequestBody @Valid FileDownloadRequest request) {
        return commonFacade.fileDownload(request);
    }

}
