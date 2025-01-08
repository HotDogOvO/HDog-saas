package com.hotdog.saas.application.assembler;

import com.hotdog.saas.application.entity.request.wechat.app.CreateWechatAppRequest;
import com.hotdog.saas.application.entity.request.wechat.app.UpdateWechatAppRequest;
import com.hotdog.saas.application.entity.request.wechat.app.WechatAppPageRequest;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.wechat.app.WechatAppCardDTO;
import com.hotdog.saas.application.entity.response.wechat.app.WechatAppDTO;
import com.hotdog.saas.domain.model.WechatApp;
import com.hotdog.saas.domain.model.page.PageResponse;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WechatAppAssembler {

    WechatAppAssembler INSTANCE = Mappers.getMapper(WechatAppAssembler.class);

    WechatApp convert(CreateWechatAppRequest createWechatAppRequest);

    WechatApp convert(WechatAppPageRequest wechatAppPageRequest);

    WechatApp convert(UpdateWechatAppRequest updateWechatAppRequest);

    PageResponseDTO<WechatAppCardDTO> convertPage(PageResponse<List<WechatApp>> pageResponse);

    WechatAppDTO convertToDTO(WechatApp wechatApp);
}
