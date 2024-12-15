package com.hotdog.saas.infra.repository;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.hotdog.saas.domain.model.UserRole;
import com.hotdog.saas.domain.repository.UserRoleRepository;
import com.hotdog.saas.infra.converter.UserRoleConverter;
import com.hotdog.saas.infra.dao.UserRoleMapper;
import com.hotdog.saas.infra.entity.UserDO;
import com.hotdog.saas.infra.entity.UserRoleDO;
import io.jsonwebtoken.lang.Collections;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRoleRepositoryImpl extends AbstractBaseRepository implements UserRoleRepository {

    private final UserRoleMapper userRoleMapper;

    public UserRoleRepositoryImpl(UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public Integer save(UserRole userRole) {
        if(Collections.isEmpty(userRole.getRoleIdList())) {
            return 0;
        }
        Long userId = userRole.getUserId();
        List<UserRoleDO> list = userRole.getRoleIdList().stream()
                .map(x -> new UserRoleDO().setUserId(userId).setRoleId(x))
                .toList();
        return userRoleMapper.insert(list).size();
    }

    @Override
    public List<UserRole> findByUserId(Long userId) {
        LambdaQueryWrapper<UserRoleDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserRoleDO::getUserId, userId);
        List<UserRoleDO> userRoleDOList = userRoleMapper.selectList(lambdaQueryWrapper);
        return userRoleDOList.stream().map(UserRoleConverter.INSTANCE::convert).toList();
    }

    @Override
    public Integer removeByUserId(Long userId) {
        LambdaQueryWrapper<UserRoleDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserRoleDO::getUserId, userId);
        return userRoleMapper.delete(lambdaQueryWrapper);
    }
}
