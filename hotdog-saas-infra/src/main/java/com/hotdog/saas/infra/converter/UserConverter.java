package com.hotdog.saas.infra.converter;

import com.hotdog.saas.domain.model.Tenant;
import com.hotdog.saas.domain.model.User;
import com.hotdog.saas.infra.entity.TenantDO;
import com.hotdog.saas.infra.entity.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    UserDO convert2DO(User user);

    User convert(UserDO userDO);
}
