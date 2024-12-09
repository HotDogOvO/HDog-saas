package com.hotdog.saas.infra.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hotdog.saas.domain.model.SystemProperties;
import com.hotdog.saas.domain.repository.SystemPropertiesRepository;
import com.hotdog.saas.infra.converter.SystemPropertiesConverter;
import com.hotdog.saas.infra.dao.SystemPropertiesMapper;
import com.hotdog.saas.infra.entity.po.SystemPropertiesPO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
