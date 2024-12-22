package com.hotdog.saas.infra.converter;

import com.hotdog.saas.domain.model.Menu;
import com.hotdog.saas.domain.model.Role;
import com.hotdog.saas.infra.entity.MenuDO;
import com.hotdog.saas.infra.entity.RoleDO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MenuConverter {

    MenuConverter INSTANCE = Mappers.getMapper(MenuConverter.class);

    MenuDO convert2DO(Menu menu);

    Menu convert(MenuDO menuDO);
}
