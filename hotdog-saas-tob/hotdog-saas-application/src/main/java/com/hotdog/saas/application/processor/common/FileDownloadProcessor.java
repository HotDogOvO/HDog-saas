package com.hotdog.saas.application.processor.common;

import com.hotdog.saas.application.entity.request.common.FileDownloadRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.common.FileDownloadDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import org.springframework.stereotype.Component;

@Component
public class FileDownloadProcessor extends AbstractFileUploadProcessor<FileDownloadRequest, BaseResponse<FileDownloadDTO>> {

    @Override
    public BaseResponse<FileDownloadDTO> initResult() {
        BaseResponse<FileDownloadDTO> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(FileDownloadRequest request, BaseResponse<FileDownloadDTO> response) {
        String filePath = fileService.downloadFile(request.getFilePath());
        FileDownloadDTO fileDownloadDTO = FileDownloadDTO.builder()
                .downloadUrl(filePath)
                .build();
        response.setData(fileDownloadDTO);
    }
}
