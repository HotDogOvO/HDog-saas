package com.hotdog.saas.infra.dao;

import com.hotdog.saas.domain.repository.entity.po.TestPO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {

    TestPO pageQuery();
}
