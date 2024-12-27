package com.hotdog.saas.infra.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotdog.saas.domain.enums.common.DeleteEnum;
import com.hotdog.saas.domain.model.User;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;
import com.hotdog.saas.domain.repository.UserRepository;
import com.hotdog.saas.domain.utils.DateUtils;
import com.hotdog.saas.infra.converter.UserConverter;
import com.hotdog.saas.infra.dao.UserMapper;
import com.hotdog.saas.infra.entity.UserDO;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Repository
public class UserRepositoryImpl extends AbstractBaseRepository implements UserRepository {

    private final UserMapper userMapper;

    public UserRepositoryImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Integer save(User user) {
        UserDO userDO = UserConverter.INSTANCE.convert2DO(user);
        LocalDateTime now = DateUtils.now();
        userDO.setCreator(user.getOperator()).setCreateTime(now)
                .setUpdater(user.getOperator()).setUpdateTime(now);

        return userMapper.insert(userDO);
    }

    @Override
    public PageResponse<List<User>> listPage(User user, PageRequest pageRequest) {
        Page<UserDO> page = new Page<>(pageRequest.getPageIndex(), pageRequest.getPageSize());
        LambdaQueryWrapper<UserDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserDO::getTenantId, user.getTenantId());
        queryWrapper.eq(UserDO::getDeleted, DeleteEnum.NO.getCode());
        queryWrapper.orderByDesc(UserDO::getCreateTime);

        Page<UserDO> pageResult = userMapper.selectPage(page, queryWrapper);
        List<User> list = pageResult.getRecords().stream().map(UserConverter.INSTANCE::convert).toList();

        PageResponse<List<User>> listPageResponse = pageConverter(pageResult);
        listPageResponse.setData(list);
        return listPageResponse;
    }

    @Override
    public User findById(Long id) {
        UserDO userDO = userMapper.selectById(id);
        return UserConverter.INSTANCE.convert(userDO);
    }

    @Override
    public Long exists(Long id) {
        LambdaQueryWrapper<UserDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserDO::getDeleted, DeleteEnum.NO.getCode())
                .eq(UserDO::getId, id);
        return userMapper.selectCount(queryWrapper);
    }

    @Override
    public Long existsByUsername(String username, Long tenantId) {
        LambdaQueryWrapper<UserDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserDO::getDeleted, DeleteEnum.NO.getCode())
                .eq(UserDO::getUsername, username);
        if (Objects.nonNull(tenantId)) {
            queryWrapper.eq(UserDO::getTenantId, tenantId);
        }
        return userMapper.selectCount(queryWrapper);
    }

    @Override
    public Integer modify(User user) {
        UserDO userDO = UserConverter.INSTANCE.convert2DO(user)
                .setUpdater(user.getOperator())
                .setUpdateTime(LocalDateTime.now());
        return userMapper.updateById(userDO);
    }

    @Override
    public Integer remove(Long id, String operator) {
        UserDO userDO = new UserDO()
                .setId(id)
                .setDeleted(DeleteEnum.YES.getCode())
                .setUpdater(operator)
                .setUpdateTime(DateUtils.now());
        return userMapper.updateById(userDO);
    }
}
