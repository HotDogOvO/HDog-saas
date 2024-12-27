package com.hotdog.saas.application.assembler;

import com.hotdog.saas.application.entity.response.common.FileUploadDTO;
import com.hotdog.saas.domain.model.common.FileUpload;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommonAssembler {
    CommonAssembler INSTANCE = Mappers.getMapper(CommonAssembler.class);

    FileUploadDTO convertFileDTO(FileUpload fileUpload);
}
