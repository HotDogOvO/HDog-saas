package com.hotdog.saas.domain.repository;

import com.hotdog.saas.domain.model.SystemProperties;

public interface SystemPropertiesRepository {

    SystemProperties findByName(String name);

    Boolean edit(SystemProperties systemProperties);
}
