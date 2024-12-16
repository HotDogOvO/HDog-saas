package com.hotdog.saas.application.processor.menu;

import com.google.common.collect.Lists;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.processor.AbstractBaseProcessor;
import com.hotdog.saas.application.template.BizProcessorTemplate;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.model.Menu;
import com.hotdog.saas.domain.repository.MenuRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import io.micrometer.common.util.StringUtils;

public abstract class AbstractMenuProcessor<Req extends BaseRequestParam, Resp extends BaseResponse<?>> extends AbstractBaseProcessor implements BizProcessorTemplate<Req, Resp> {

    @Autowired
    protected MenuRepository menuRepository;

    protected void existsByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return;
        }
        Long nameCount = menuRepository.existsByName(name);
        if (nameCount > 0) {
            throw new BusinessException(ResultCodeEnum.FAIL, "权限名重复");
        }
    }

    protected void existsByPermission(String permission) {
        if (StringUtils.isEmpty(permission)) {
            return;
        }
        Long nameCount = menuRepository.existsByPermission(permission);
        if (nameCount > 0) {
            throw new BusinessException(ResultCodeEnum.FAIL, "权限标识重复");
        }
    }

    protected void exists(Long id) {
        if (Objects.isNull(id)) {
            return;
        }
        Long count = menuRepository.exists(id);
        if (count == 0) {
            throw new BusinessException(ResultCodeEnum.FAIL, "菜单不存在");
        }
    }

    protected Menu buildMenuTree(Menu menu) {
        List<Menu> menus = buildMenuTree(Lists.newArrayList(menu));
        return CollectionUtils.isEmpty(menus) ? Menu.builder().build() : menus.get(0);
    }

    protected List<Menu> buildMenuTree(List<Menu> menuList) {
        List<Menu> resultTree = new ArrayList<>();

        Map<Long, Menu> menuMap = menuList.stream().peek(x -> x.setChildren(Lists.newArrayList()))
                .collect(Collectors.toMap(Menu::getId, Function.identity(), (k1, k2) -> k1));

        // 构建树结构
        menuList.forEach(menu -> {
            Long parentId = menu.getParentId();
            if (parentId == 0) {
                // 根节点
                resultTree.add(menu);
            } else {
                // 非根节点，找到父节点
                Menu parent = menuMap.get(parentId);
                if (parent != null) {
                    parent.getChildren().add(menu);
                }
            }
        });
        return resultTree;
    }

}
