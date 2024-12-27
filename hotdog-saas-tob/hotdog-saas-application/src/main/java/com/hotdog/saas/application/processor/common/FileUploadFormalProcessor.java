package com.hotdog.saas.application.processor.common;

import com.hotdog.saas.application.assembler.CommonAssembler;
import com.hotdog.saas.application.entity.request.common.FileUploadFormalRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.common.FileUploadDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.common.FileUpload;

import org.springframework.stereotype.Component;

@Component
public class FileUploadFormalProcessor extends AbstractFileUploadProcessor<FileUploadFormalRequest, BaseResponse<FileUploadDTO>> {

    @Override
    public BaseResponse<FileUploadDTO> initResult() {
        BaseResponse<FileUploadDTO> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(FileUploadFormalRequest request, BaseResponse<FileUploadDTO> response) {
        FileUpload fileUpload = fileService.uploadFormal(request.getTmpFilePath());
        FileUploadDTO fileUploadDTO = CommonAssembler.INSTANCE.convertFileDTO(fileUpload);
        response.setData(fileUploadDTO);
    }
}
