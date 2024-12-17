package com.hotdog.saas.application.assembler;

import com.hotdog.saas.application.entity.request.log.login.LoginLogPageRequest;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.log.login.LoginLogDTO;
import com.hotdog.saas.domain.model.LoginLog;
import com.hotdog.saas.domain.model.page.PageResponse;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LoginLogAssembler {

    LoginLogAssembler INSTANCE = Mappers.getMapper(LoginLogAssembler.class);

    LoginLog convert(LoginLogPageRequest loginLogPageRequest);

    PageResponseDTO<LoginLogDTO> convertPage(PageResponse<List<LoginLog>> pageResponse);

}
