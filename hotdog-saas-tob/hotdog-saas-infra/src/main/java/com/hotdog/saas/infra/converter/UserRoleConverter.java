package com.hotdog.saas.infra.converter;

import com.hotdog.saas.domain.model.UserRole;
import com.hotdog.saas.infra.entity.UserRoleDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserRoleConverter {

    UserRoleConverter INSTANCE = Mappers.getMapper(UserRoleConverter.class);

    UserRole convert(UserRoleDO userRoleDO);
}
