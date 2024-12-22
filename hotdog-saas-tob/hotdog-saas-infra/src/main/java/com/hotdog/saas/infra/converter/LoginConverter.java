package com.hotdog.saas.infra.converter;

import com.hotdog.saas.domain.model.Login;
import com.hotdog.saas.infra.entity.UserDO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoginConverter {

    LoginConverter INSTANCE = Mappers.getMapper(LoginConverter.class);

    Login convert(UserDO userDO);
}
