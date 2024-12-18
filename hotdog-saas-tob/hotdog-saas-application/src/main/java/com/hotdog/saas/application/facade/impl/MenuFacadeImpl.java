package com.hotdog.saas.application.facade.impl;

import com.hotdog.saas.application.entity.request.menu.CreateMenuRequest;
import com.hotdog.saas.application.entity.request.menu.DeleteMenuRequest;
import com.hotdog.saas.application.entity.request.menu.MenuTreeRequest;
import com.hotdog.saas.application.entity.request.menu.QueryMenuRequest;
import com.hotdog.saas.application.entity.request.menu.UpdateMenuRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.menu.MenuDTO;
import com.hotdog.saas.application.facade.MenuFacade;
import com.hotdog.saas.application.processor.BaseProcessor;
import com.hotdog.saas.application.processor.menu.MenuCreateProcessor;
import com.hotdog.saas.application.processor.menu.MenuDeleteProcessor;
import com.hotdog.saas.application.processor.menu.MenuDetailProcessor;
import com.hotdog.saas.application.processor.menu.MenuTreeProcessor;
import com.hotdog.saas.application.processor.menu.MenuUpdateProcessor;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MenuFacadeImpl extends BaseProcessor implements MenuFacade {

    private final MenuCreateProcessor menuCreateProcessor;
    private final MenuTreeProcessor menuTreeProcessor;
    private final MenuDetailProcessor menuDetailProcessor;
    private final MenuDeleteProcessor menuDeleteProcessor;
    private final MenuUpdateProcessor menuUpdateProcessor;

    public MenuFacadeImpl(MenuCreateProcessor menuCreateProcessor, MenuTreeProcessor menuTreeProcessor, MenuDetailProcessor menuDetailProcessor, MenuDeleteProcessor menuDeleteProcessor, MenuUpdateProcessor menuUpdateProcessor) {
        this.menuCreateProcessor = menuCreateProcessor;
        this.menuTreeProcessor = menuTreeProcessor;
        this.menuDetailProcessor = menuDetailProcessor;
        this.menuDeleteProcessor = menuDeleteProcessor;
        this.menuUpdateProcessor = menuUpdateProcessor;
    }

    @Override
    public BaseResponse<Boolean> createMenu(CreateMenuRequest createMenuRequest) {
        return this.doBiz(createMenuRequest, menuCreateProcessor);
    }

    @Override
    public BaseResponse<List<MenuDTO>> menuTree(MenuTreeRequest menuTreeRequest) {
        return this.doBiz(menuTreeRequest, menuTreeProcessor);
    }

    @Override
    public BaseResponse<MenuDTO> menuDetail(QueryMenuRequest queryMenuRequest) {
        return this.doBiz(queryMenuRequest, menuDetailProcessor);
    }

    @Override
    public BaseResponse<Boolean> updateMenu(UpdateMenuRequest updateMenuRequest) {
        return this.doBiz(updateMenuRequest, menuUpdateProcessor);
    }

    @Override
    public BaseResponse<Boolean> deleteMenu(DeleteMenuRequest deleteMenuRequest) {
        return this.doBiz(deleteMenuRequest, menuDeleteProcessor);
    }
}
