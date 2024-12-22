package com.hotdog.saas.infra.converter;

import com.hotdog.saas.domain.model.SystemProperties;
import com.hotdog.saas.infra.entity.SystemPropertiesDO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SystemPropertiesConverter {

    SystemPropertiesConverter INSTANCE = Mappers.getMapper(SystemPropertiesConverter.class);

    SystemProperties convert(SystemPropertiesDO systemPropertiesDO);

}
