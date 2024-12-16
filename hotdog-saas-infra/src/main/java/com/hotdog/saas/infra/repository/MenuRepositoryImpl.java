package com.hotdog.saas.infra.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hotdog.saas.domain.enums.common.DeleteEnum;
import com.hotdog.saas.domain.model.Menu;
import com.hotdog.saas.domain.repository.MenuRepository;
import com.hotdog.saas.domain.utils.DateUtils;
import com.hotdog.saas.infra.converter.MenuConverter;
import com.hotdog.saas.infra.dao.MenuMapper;
import com.hotdog.saas.infra.entity.MenuDO;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class MenuRepositoryImpl extends AbstractBaseRepository implements MenuRepository {

    private final MenuMapper menuMapper;

    public MenuRepositoryImpl(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    @Override
    public List<Menu> list(Menu menu) {
        LambdaQueryWrapper<MenuDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<MenuDO> menuDOList = menuMapper.selectList(lambdaQueryWrapper);
        return menuDOList.stream().map(MenuConverter.INSTANCE::convert).toList();
    }

    @Override
    public Integer save(Menu menu) {
        MenuDO menuDO = MenuConverter.INSTANCE.convert2DO(menu);
        LocalDateTime now = DateUtils.now();
        menuDO.setCreator(menu.getOperator()).setCreateTime(now)
                .setUpdater(menu.getOperator()).setUpdateTime(now);

        return menuMapper.insert(menuDO);
    }

    @Override
    public Menu findById(Long id) {
        MenuDO menuDO = menuMapper.selectById(id);
        return MenuConverter.INSTANCE.convert(menuDO);
    }

    @Override
    public Long exists(Long id) {
        LambdaQueryWrapper<MenuDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(MenuDO::getId, id);
        return menuMapper.selectCount(lambdaQueryWrapper);
    }

    @Override
    public Long existsByName(String name) {
        LambdaQueryWrapper<MenuDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(MenuDO::getName, name);
        return menuMapper.selectCount(lambdaQueryWrapper);
    }

    @Override
    public Long existsByPermission(String permission) {
        LambdaQueryWrapper<MenuDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(MenuDO::getPermission, permission);
        return menuMapper.selectCount(lambdaQueryWrapper);
    }

    @Override
    public Integer modify(Menu menu) {
        MenuDO menuDO = MenuConverter.INSTANCE.convert2DO(menu);
        menuDO.setUpdater(menu.getOperator()).setUpdateTime(DateUtils.now());
        return menuMapper.updateById(menuDO);
    }

    @Override
    public Integer remove(Long id, String operator) {
        MenuDO menuDO = new MenuDO()
                .setId(id)
                .setDeleted(DeleteEnum.YES.getCode())
                .setUpdater(operator)
                .setUpdateTime(DateUtils.now());
        return menuMapper.updateById(menuDO);
    }
}
