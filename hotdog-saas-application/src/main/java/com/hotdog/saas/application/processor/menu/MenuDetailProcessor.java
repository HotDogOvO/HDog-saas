package com.hotdog.saas.application.processor.menu;

import com.google.common.collect.Lists;

import com.hotdog.saas.application.assembler.MenuAssembler;
import com.hotdog.saas.application.assembler.RoleAssembler;
import com.hotdog.saas.application.entity.request.menu.QueryMenuRequest;
import com.hotdog.saas.application.entity.request.role.QueryRoleRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.menu.MenuDTO;
import com.hotdog.saas.application.entity.response.role.RoleDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.Menu;
import com.hotdog.saas.domain.model.Role;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MenuDetailProcessor extends AbstractMenuProcessor<QueryMenuRequest, BaseResponse<MenuDTO>> {

    @Override
    public BaseResponse<MenuDTO> initResult() {
        BaseResponse<MenuDTO> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(QueryMenuRequest request, BaseResponse<MenuDTO> response) {
        Long menuId = request.getId();
        super.exists(menuId);
        Menu menu = menuRepository.findById(menuId);

        List<Menu> childrenMenu = menuRepository.findByParentId(menuId);
        super.sortMenuTree(childrenMenu);
        menu.setChildren(childrenMenu);
        MenuDTO menuDTO = MenuAssembler.INSTANCE.convertToDTO(menu);
        response.setData(menuDTO);
    }

}
