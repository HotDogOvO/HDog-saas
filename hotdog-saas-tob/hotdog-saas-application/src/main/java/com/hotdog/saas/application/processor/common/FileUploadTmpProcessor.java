package com.hotdog.saas.application.processor.common;

import com.hotdog.saas.application.assembler.CommonAssembler;
import com.hotdog.saas.application.entity.request.common.FileUploadTmpRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.common.FileUploadDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.common.FileUpload;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadTmpProcessor extends AbstractFileUploadProcessor<FileUploadTmpRequest, BaseResponse<FileUploadDTO>> {

    @Override
    public BaseResponse<FileUploadDTO> initResult() {
        BaseResponse<FileUploadDTO> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(FileUploadTmpRequest request, BaseResponse<FileUploadDTO> response) {
        MultipartFile file = request.getFile();
        FileUpload fileUpload = fileService.uploadTmp(file);
        FileUploadDTO fileUploadDTO = CommonAssembler.INSTANCE.convertFileDTO(fileUpload);
        response.setData(fileUploadDTO);
    }
}
