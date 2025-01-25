package com.hotdog.saas.matrix.web.controller;

import com.hotdog.saas.application.entity.request.menu.CreateMenuRequest;
import com.hotdog.saas.application.entity.request.menu.DeleteMenuRequest;
import com.hotdog.saas.application.entity.request.menu.QueryMenuRequest;
import com.hotdog.saas.application.entity.request.menu.UpdateMenuRequest;
import com.hotdog.saas.application.entity.request.menu.MenuTreeRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.menu.MenuDTO;
import com.hotdog.saas.application.facade.MenuFacade;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "菜单管理")
@RestController
@RequestMapping("/api/hotdog/v1/menu")
public class MenuController {

    private final MenuFacade menuFacade;

    public MenuController(MenuFacade menuFacade) {
        this.menuFacade = menuFacade;
    }

    @Operation(summary = "创建菜单")
    @PostMapping("/create")
    public BaseResponse<Boolean> createMenu(@RequestBody @Validated CreateMenuRequest createMenuRequest) {
        return menuFacade.createMenu(createMenuRequest);
    }

    @Operation(summary = "查询菜单树")
    @PostMapping("/tree")
    public BaseResponse<List<MenuDTO>> menuTree(@RequestBody @Validated MenuTreeRequest menuTreeRequest) {
        return menuFacade.menuTree(menuTreeRequest);
    }

    @Operation(summary = "查询菜单详情")
    @PostMapping("/detail")
    public BaseResponse<MenuDTO> menuDetail(@RequestBody @Validated QueryMenuRequest queryMenuRequest) {
        return menuFacade.menuDetail(queryMenuRequest);
    }

    @Operation(summary = "更新菜单")
    @PostMapping("/update")
    public BaseResponse<Boolean> updateMenu(@RequestBody @Validated UpdateMenuRequest updateMenuRequest) {
        return menuFacade.updateMenu(updateMenuRequest);
    }

    @Operation(summary = "删除菜单")
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteMenu(@RequestBody @Validated DeleteMenuRequest deleteMenuRequest) {
        return menuFacade.deleteMenu(deleteMenuRequest);
    }

}
