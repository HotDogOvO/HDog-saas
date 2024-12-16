package com.hotdog.saas.infra.converter;

import com.hotdog.saas.domain.model.RoleMenu;
import com.hotdog.saas.domain.model.UserRole;
import com.hotdog.saas.infra.entity.RoleMenuDO;
import com.hotdog.saas.infra.entity.UserRoleDO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMenuConverter {

    RoleMenuConverter INSTANCE = Mappers.getMapper(RoleMenuConverter.class);

    RoleMenu convert(RoleMenuDO roleMenuDO);
}
