package com.hotdog.saas.infra.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.hotdog.saas.domain.model.SystemProperties;
import com.hotdog.saas.domain.repository.SystemPropertiesRepository;
import com.hotdog.saas.infra.converter.SystemPropertiesConverter;
import com.hotdog.saas.infra.dao.SystemPropertiesMapper;
import com.hotdog.saas.infra.entity.SystemPropertiesDO;

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
        LambdaQueryWrapper<SystemPropertiesDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SystemPropertiesDO::getName, name);
        SystemPropertiesDO systemPropertiesDO = systemPropertiesMapper.selectOne(queryWrapper);
        return SystemPropertiesConverter.INSTANCE.convert(systemPropertiesDO);
    }

    @Override
    public Boolean edit(SystemProperties systemProperties) {
        LambdaUpdateWrapper<SystemPropertiesDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(SystemPropertiesDO::getName, systemProperties.getName())
                .set(SystemPropertiesDO::getValue, systemProperties.getValue())
                .set(SystemPropertiesDO::getUpdater, systemProperties.getOperator())
                .set(SystemPropertiesDO::getUpdateTime, LocalDateTime.now());
        return systemPropertiesMapper.update(updateWrapper) > 0;
    }
}
