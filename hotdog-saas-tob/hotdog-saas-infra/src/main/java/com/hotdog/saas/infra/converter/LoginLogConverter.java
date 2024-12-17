package com.hotdog.saas.infra.converter;

import com.hotdog.saas.domain.model.LoginLog;
import com.hotdog.saas.domain.model.Role;
import com.hotdog.saas.infra.entity.LoginLogDO;
import com.hotdog.saas.infra.entity.RoleDO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoginLogConverter {

    LoginLogConverter INSTANCE = Mappers.getMapper(LoginLogConverter.class);

    LoginLogDO convert2DO(LoginLog loginLog);

    LoginLog convert(LoginLogDO loginLogDO);
}
