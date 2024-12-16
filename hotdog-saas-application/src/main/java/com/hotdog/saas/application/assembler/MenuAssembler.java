package com.hotdog.saas.application.assembler;

import com.hotdog.saas.application.entity.request.menu.CreateMenuRequest;
import com.hotdog.saas.application.entity.request.menu.MenuTreeRequest;
import com.hotdog.saas.application.entity.request.menu.UpdateMenuRequest;
import com.hotdog.saas.application.entity.response.menu.MenuDTO;
import com.hotdog.saas.domain.model.Menu;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MenuAssembler {

    MenuAssembler INSTANCE = Mappers.getMapper(MenuAssembler.class);

    Menu convert(CreateMenuRequest createMenuRequest);

    Menu convert(MenuTreeRequest MenuTreeRequest);

    Menu convert(UpdateMenuRequest updateMenuRequest);

    List<MenuDTO> convertList(List<Menu> list);

    MenuDTO convertToDTO(Menu Menu);
}
