package com.hotdog.saas.application.facade;

import com.hotdog.saas.application.entity.request.menu.CreateMenuRequest;
import com.hotdog.saas.application.entity.request.menu.DeleteMenuRequest;
import com.hotdog.saas.application.entity.request.menu.QueryMenuRequest;
import com.hotdog.saas.application.entity.request.menu.UpdateMenuRequest;
import com.hotdog.saas.application.entity.request.menu.MenuTreeRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.menu.MenuDTO;

import java.util.List;

public interface MenuFacade {
    BaseResponse<Boolean> createMenu(CreateMenuRequest createMenuRequest);

    BaseResponse<List<MenuDTO>> menuTree(MenuTreeRequest MenuTreeRequest);

    BaseResponse<MenuDTO> menuDetail(QueryMenuRequest queryMenuRequest);

    BaseResponse<Boolean> updateMenu(UpdateMenuRequest updateMenuRequest);

    BaseResponse<Boolean> deleteMenu(DeleteMenuRequest deleteMenuRequest);
}
