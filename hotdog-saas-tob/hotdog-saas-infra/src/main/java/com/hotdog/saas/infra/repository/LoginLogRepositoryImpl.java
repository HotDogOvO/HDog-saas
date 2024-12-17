package com.hotdog.saas.infra.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotdog.saas.domain.model.LoginLog;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;
import com.hotdog.saas.domain.repository.LoginLogRepository;
import com.hotdog.saas.domain.utils.DateUtils;
import com.hotdog.saas.infra.converter.LoginLogConverter;
import com.hotdog.saas.infra.dao.LoginLogMapper;
import com.hotdog.saas.infra.entity.LoginLogDO;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class LoginLogRepositoryImpl extends AbstractBaseRepository implements LoginLogRepository {

    private final LoginLogMapper loginLogMapper;

    public LoginLogRepositoryImpl(LoginLogMapper loginLogMapper) {
        this.loginLogMapper = loginLogMapper;
    }

    @Override
    public Integer save(LoginLog loginLog) {
        LoginLogDO loginLogDO = LoginLogConverter.INSTANCE.convert2DO(loginLog);
        LocalDateTime now = DateUtils.now();
        loginLogDO.setCreator(loginLog.getOperator()).setCreateTime(now);

        return loginLogMapper.insert(loginLogDO);

    }

    @Override
    public PageResponse<List<LoginLog>> listPage(LoginLog loginLog, PageRequest pageRequest) {
        Page<LoginLogDO> page = new Page<>(pageRequest.getPageIndex(), pageRequest.getPageSize());
        LambdaQueryWrapper<LoginLogDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(LoginLogDO::getTenantId, loginLog.getTenantId());
        queryWrapper.orderByDesc(LoginLogDO::getCreateTime);

        Page<LoginLogDO> pageResult = loginLogMapper.selectPage(page, queryWrapper);
        List<LoginLog> list = pageResult.getRecords().stream().map(LoginLogConverter.INSTANCE::convert).toList();

        PageResponse<List<LoginLog>> listPageResponse = pageConverter(pageResult);
        listPageResponse.setData(list);
        return listPageResponse;

    }
}
