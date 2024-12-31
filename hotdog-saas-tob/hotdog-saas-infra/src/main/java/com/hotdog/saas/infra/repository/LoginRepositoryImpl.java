package com.hotdog.saas.infra.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hotdog.saas.domain.enums.common.DeleteEnum;
import com.hotdog.saas.domain.model.Login;
import com.hotdog.saas.domain.repository.LoginRepository;
import com.hotdog.saas.infra.converter.LoginConverter;
import com.hotdog.saas.infra.dao.UserMapper;
import com.hotdog.saas.infra.entity.UserDO;

import org.springframework.stereotype.Repository;

@Repository
public class LoginRepositoryImpl implements LoginRepository {

    private final UserMapper userMapper;

    public LoginRepositoryImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Login findLoginUser(String username) {
        LambdaQueryWrapper<UserDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .eq(UserDO::getDeleted, DeleteEnum.NO.getCode())
                .eq(UserDO::getUsername, username);
        UserDO userDO = userMapper.selectOne(lambdaQueryWrapper);
        return LoginConverter.INSTANCE.convert(userDO);
    }
}