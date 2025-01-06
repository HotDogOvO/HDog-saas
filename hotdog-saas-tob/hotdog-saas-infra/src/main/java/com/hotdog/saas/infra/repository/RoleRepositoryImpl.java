package com.hotdog.saas.infra.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.hotdog.saas.domain.enums.common.DeleteEnum;
import com.hotdog.saas.domain.model.Role;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;
import com.hotdog.saas.domain.repository.RoleRepository;
import com.hotdog.saas.domain.utils.DateUtils;
import com.hotdog.saas.infra.converter.RoleConverter;
import com.hotdog.saas.infra.dao.RoleMapper;
import com.hotdog.saas.infra.entity.RoleDO;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import io.micrometer.common.util.StringUtils;

@Repository
public class RoleRepositoryImpl extends AbstractBaseRepository implements RoleRepository {

    private final RoleMapper roleMapper;

    public RoleRepositoryImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public Long save(Role role) {
        RoleDO roleDO = RoleConverter.INSTANCE.convert2DO(role);
        LocalDateTime now = DateUtils.now();
        roleDO.setCreator(role.getOperator()).setCreateTime(now)
                .setUpdater(role.getOperator()).setUpdateTime(now);
        roleMapper.insert(roleDO);
        return roleDO.getId();
    }

    @Override
    public PageResponse<List<Role>> listPage(Role role, PageRequest pageRequest) {
        Page<RoleDO> page = new Page<>(pageRequest.getPageIndex(), pageRequest.getPageSize());
        LambdaQueryWrapper<RoleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleDO::getTenantId, role.getTenantId());
        queryWrapper.eq(RoleDO::getDeleted, DeleteEnum.NO.getCode());
        queryWrapper.eq(StringUtils.isNotEmpty(role.getName()), RoleDO::getName, role.getName());
        queryWrapper.eq(Objects.nonNull(role.getStatus()), RoleDO::getStatus, role.getStatus());
        queryWrapper.orderByDesc(RoleDO::getCreateTime);

        Page<RoleDO> pageResult = roleMapper.selectPage(page, queryWrapper);
        List<Role> list = pageResult.getRecords().stream().map(RoleConverter.INSTANCE::convert).toList();

        PageResponse<List<Role>> listPageResponse = pageConverter(pageResult);
        listPageResponse.setData(list);
        return listPageResponse;

    }

    @Override
    public Role findById(Long id) {
        RoleDO roleDO = roleMapper.selectById(id);
        return RoleConverter.INSTANCE.convert(roleDO);
    }

    @Override
    public List<Role> findList(Role role) {
        LambdaQueryWrapper<RoleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleDO::getTenantId, role.getTenantId());
        queryWrapper.eq(RoleDO::getDeleted, DeleteEnum.NO.getCode());
        List<RoleDO> roleDOList = roleMapper.selectList(queryWrapper);
        return roleDOList.stream().map(RoleConverter.INSTANCE::convert).toList();
    }

    @Override
    public List<Role> findByIdList(List<Long> idList) {
        if(CollectionUtils.isEmpty(idList)){
            return Lists.newArrayList();
        }
        LambdaQueryWrapper<RoleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(RoleDO::getId, idList);
        List<RoleDO> roleDOList = roleMapper.selectList(queryWrapper);
        return roleDOList.stream().map(RoleConverter.INSTANCE::convert).toList();
    }

    @Override
    public Long exists(Long id) {
        LambdaQueryWrapper<RoleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleDO::getDeleted, DeleteEnum.NO.getCode())
                .eq(RoleDO::getId, id);
        return roleMapper.selectCount(queryWrapper);
    }

    @Override
    public Long existsByName(String name, Long tenantId) {
        LambdaQueryWrapper<RoleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleDO::getDeleted, DeleteEnum.NO.getCode()).eq(RoleDO::getName, name);
        queryWrapper.eq(RoleDO::getTenantId, tenantId);
        return roleMapper.selectCount(queryWrapper);
    }

    @Override
    public Long countByIdList(List<Long> idList) {
        if(CollectionUtils.isEmpty(idList)){
            return 0L;
        }
        LambdaQueryWrapper<RoleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(RoleDO::getId, idList);
        return roleMapper.selectCount(queryWrapper);
    }

    @Override
    public Integer modify(Role role) {
        RoleDO roleDO = RoleConverter.INSTANCE.convert2DO(role)
                .setUpdater(role.getOperator())
                .setUpdateTime(LocalDateTime.now());
        return roleMapper.updateById(roleDO);
    }

    @Override
    public Integer remove(Long id, String operator) {
        RoleDO roleDO = new RoleDO()
                .setId(id)
                .setDeleted(DeleteEnum.YES.getCode())
                .setUpdater(operator)
                .setUpdateTime(DateUtils.now());
        return roleMapper.updateById(roleDO);
    }
}
