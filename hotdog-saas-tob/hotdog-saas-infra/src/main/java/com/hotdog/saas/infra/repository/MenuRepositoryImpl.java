package com.hotdog.saas.infra.repository;

import com.google.common.collect.Lists;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.hotdog.saas.domain.enums.common.DeleteEnum;
import com.hotdog.saas.domain.model.Menu;
import com.hotdog.saas.domain.repository.MenuRepository;
import com.hotdog.saas.domain.utils.DateUtils;
import com.hotdog.saas.infra.converter.MenuConverter;
import com.hotdog.saas.infra.dao.MenuMapper;
import com.hotdog.saas.infra.entity.MenuDO;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class MenuRepositoryImpl extends AbstractBaseRepository implements MenuRepository {

    private final MenuMapper menuMapper;

    public MenuRepositoryImpl(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    @Override
    public List<Menu> list(Menu menu) {
        LambdaQueryWrapper<MenuDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MenuDO::getDeleted, DeleteEnum.NO.getCode());
        List<MenuDO> menuDOList = menuMapper.selectList(queryWrapper);
        return menuDOList.stream().map(MenuConverter.INSTANCE::convert).toList();
    }

    @Override
    public Long save(Menu menu) {
        MenuDO menuDO = MenuConverter.INSTANCE.convert2DO(menu);
        LocalDateTime now = DateUtils.now();
        menuDO.setCreator(menu.getOperator()).setCreateTime(now)
                .setUpdater(menu.getOperator()).setUpdateTime(now);
        menuMapper.insert(menuDO);
        return menuDO.getId();
    }

    @Override
    public Menu findById(Long id) {
        MenuDO menuDO = menuMapper.selectById(id);
        return MenuConverter.INSTANCE.convert(menuDO);
    }

    @Override
    public List<Menu> findByParentId(Long parentId) {
        LambdaQueryWrapper<MenuDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(MenuDO::getParentId, parentId);
        List<MenuDO> menuDOList = menuMapper.selectList(lambdaQueryWrapper);
        return menuDOList.stream().map(MenuConverter.INSTANCE::convert).collect(Collectors.toList());
    }

    @Override
    public List<Menu> findByIdList(Set<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return Lists.newArrayList();
        }
        LambdaQueryWrapper<MenuDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(MenuDO::getId, idList);
        List<MenuDO> menuDOList = menuMapper.selectList(lambdaQueryWrapper);
        return menuDOList.stream().map(MenuConverter.INSTANCE::convert).toList();
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
        lambdaQueryWrapper.eq(MenuDO::getDeleted, DeleteEnum.NO.getCode());
        return menuMapper.selectCount(lambdaQueryWrapper);
    }

    @Override
    public Long existsByPermission(String permission) {
        LambdaQueryWrapper<MenuDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(MenuDO::getPermission, permission);
        lambdaQueryWrapper.eq(MenuDO::getDeleted, DeleteEnum.NO.getCode());
        return menuMapper.selectCount(lambdaQueryWrapper);
    }

    @Override
    public Long countByIdList(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return 0L;
        }
        LambdaQueryWrapper<MenuDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(MenuDO::getId, idList);
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

    @Override
    public Integer removeByParentId(Long parentId, String operator) {
        LambdaUpdateWrapper<MenuDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(MenuDO::getParentId, parentId)
                .set(MenuDO::getDeleted, DeleteEnum.YES.getCode())
                .set(MenuDO::getUpdater, operator)
                .set(MenuDO::getUpdateTime, DateUtils.now());
        return menuMapper.update(updateWrapper);
    }
}
