package com.hotdog.saas.infra.repository;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hotdog.saas.domain.model.SystemProperties;
import com.hotdog.saas.domain.repository.SystemPropertiesRepository;
import com.hotdog.saas.infra.converter.SystemPropertiesConverter;
import com.hotdog.saas.infra.dao.SystemPropertiesMapper;
import com.hotdog.saas.infra.entity.po.SystemPropertiesPO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class SystemPropertiesRepositoryImpl implements SystemPropertiesRepository {

    private final SystemPropertiesMapper systemPropertiesMapper;

    public SystemPropertiesRepositoryImpl(SystemPropertiesMapper systemPropertiesMapper) {
        this.systemPropertiesMapper = systemPropertiesMapper;
    }

    @Override
    public SystemProperties findByName(String name) {
        LambdaQueryWrapper<SystemPropertiesPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SystemPropertiesPO::getName, name);
        SystemPropertiesPO systemPropertiesPO = systemPropertiesMapper.selectOne(queryWrapper);
        return SystemPropertiesConverter.INSTANCE.convert(systemPropertiesPO);
    }

    @Override
    public Boolean edit(SystemProperties systemProperties) {
        LambdaUpdateWrapper<SystemPropertiesPO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(SystemPropertiesPO::getName, systemProperties.getName())
                .set(SystemPropertiesPO::getValue, systemProperties.getValue())
                .set(SystemPropertiesPO::getUpdater, systemProperties.getOperator())
                .set(SystemPropertiesPO::getUpdateTime, LocalDateTime.now());
        return systemPropertiesMapper.update(updateWrapper) > 0;
    }
}
