package com.hotdog.saas.infra.converter;

import com.hotdog.saas.domain.model.Role;
import com.hotdog.saas.infra.entity.RoleDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleConverter {

    RoleConverter INSTANCE = Mappers.getMapper(RoleConverter.class);

    RoleDO convert2DO(Role role);

    Role convert(RoleDO roleDO);
}
