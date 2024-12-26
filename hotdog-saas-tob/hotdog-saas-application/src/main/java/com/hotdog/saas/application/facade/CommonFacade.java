package com.hotdog.saas.application.facade;

import com.hotdog.saas.application.entity.request.common.FileUploadFormalRequest;
import com.hotdog.saas.application.entity.request.common.FileUploadTmpRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.common.FileUploadDTO;

public interface CommonFacade {

    BaseResponse<FileUploadDTO> fileUploadTmp(FileUploadTmpRequest request);

    BaseResponse<FileUploadDTO> fileUploadFormal(FileUploadFormalRequest request);
}
