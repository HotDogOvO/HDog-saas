package com.hotdog.saas.application.processor.menu;

import com.hotdog.saas.application.assembler.MenuAssembler;
import com.hotdog.saas.application.entity.request.menu.MenuTreeRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.menu.MenuDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.Menu;

import org.springframework.stereotype.Component;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MenuTreeProcessor extends AbstractMenuProcessor<MenuTreeRequest, BaseResponse<List<MenuDTO>>> {

    @Override
    public BaseResponse<List<MenuDTO>> initResult() {
        BaseResponse<List<MenuDTO>> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(MenuTreeRequest request, BaseResponse<List<MenuDTO>> response) {
        Menu menu = MenuAssembler.INSTANCE.convert(request);

        List<Menu> list = menuRepository.list(menu);
        list = super.buildMenuTree(list);
        super.sortMenuTree(list);
        List<MenuDTO> menuDTOList = MenuAssembler.INSTANCE.convertList(list);

        response.setData(menuDTOList);
    }

}
