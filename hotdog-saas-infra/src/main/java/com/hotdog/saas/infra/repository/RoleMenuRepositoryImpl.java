package com.hotdog.saas.infra.repository;

import com.google.common.collect.Sets;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hotdog.saas.domain.model.RoleMenu;
import com.hotdog.saas.domain.repository.RoleMenuRepository;
import com.hotdog.saas.infra.converter.RoleMenuConverter;
import com.hotdog.saas.infra.converter.UserRoleConverter;
import com.hotdog.saas.infra.dao.RoleMenuMapper;
import com.hotdog.saas.infra.entity.RoleMenuDO;
import com.hotdog.saas.infra.entity.UserRoleDO;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class RoleMenuRepositoryImpl extends AbstractBaseRepository implements RoleMenuRepository {

    private final RoleMenuMapper roleMenuMapper;

    public RoleMenuRepositoryImpl(RoleMenuMapper roleMenuMapper) {
        this.roleMenuMapper = roleMenuMapper;
    }

    @Override
    public Integer save(RoleMenu roleMenu) {
        if (CollectionUtils.isEmpty(roleMenu.getMenuIdList())) {
            return 0;
        }
        Long roleId = roleMenu.getRoleId();
        List<RoleMenuDO> list = roleMenu.getMenuIdList().stream()
                .map(x -> new RoleMenuDO().setRoleId(roleId).setMenuId(x))
                .toList();
        return roleMenuMapper.insert(list).size();
    }

    @Override
    public List<RoleMenu> findByRoleId(Long roleId) {
        LambdaQueryWrapper<RoleMenuDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(RoleMenuDO::getRoleId, roleId);
        List<RoleMenuDO> roleMenuDOList = roleMenuMapper.selectList(lambdaQueryWrapper);
        return roleMenuDOList.stream().map(RoleMenuConverter.INSTANCE::convert).toList();
    }

    @Override
    public Set<RoleMenu> findByRoleIdList(List<Long> roleIdList) {
        if(CollectionUtils.isEmpty(roleIdList)) {
            return Sets.newHashSet();
        }
        LambdaQueryWrapper<RoleMenuDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(RoleMenuDO::getRoleId, roleIdList);
        List<RoleMenuDO> roleMenuDOList = roleMenuMapper.selectList(lambdaQueryWrapper);
        return roleMenuDOList.stream().map(RoleMenuConverter.INSTANCE::convert).collect(Collectors.toSet());
    }

    @Override
    public Integer removeByRoleId(Long roleId) {
        LambdaQueryWrapper<RoleMenuDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(RoleMenuDO::getRoleId, roleId);
        return roleMenuMapper.delete(lambdaQueryWrapper);
    }
}
