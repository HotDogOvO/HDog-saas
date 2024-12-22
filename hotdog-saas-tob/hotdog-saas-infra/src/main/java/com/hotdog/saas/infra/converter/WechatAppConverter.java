package com.hotdog.saas.infra.converter;

import com.hotdog.saas.domain.model.WechatApp;
import com.hotdog.saas.infra.entity.WechatAppDO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WechatAppConverter {

    WechatAppConverter INSTANCE = Mappers.getMapper(WechatAppConverter.class);

    WechatAppDO convert2DO(WechatApp wechatApp);

    WechatApp convert(WechatAppDO wechatAppDO);
}
