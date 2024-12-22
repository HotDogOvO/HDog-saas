package com.hotdog.saas.application.assembler;

import com.hotdog.saas.application.entity.request.user.CreateUserRequest;
import com.hotdog.saas.application.entity.request.user.UpdateUserRequest;
import com.hotdog.saas.application.entity.request.user.UserPageRequest;
import com.hotdog.saas.application.entity.request.wechat.app.CreateWechatAppRequest;
import com.hotdog.saas.application.entity.request.wechat.app.UpdateWechatAppRequest;
import com.hotdog.saas.application.entity.request.wechat.app.WechatAppPageRequest;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.user.UserDTO;
import com.hotdog.saas.application.entity.response.wechat.app.WechatAppDTO;
import com.hotdog.saas.domain.model.User;
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

    PageResponseDTO<WechatAppDTO> convertPage(PageResponse<List<WechatApp>> pageResponse);

    WechatAppDTO convertToDTO(WechatApp wechatApp);
}
