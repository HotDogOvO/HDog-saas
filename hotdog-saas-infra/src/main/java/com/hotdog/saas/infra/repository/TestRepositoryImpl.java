package com.hotdog.saas.infra.repository;

import com.hotdog.saas.domain.repository.TestRepository;
import com.hotdog.saas.domain.repository.entity.po.TestPO;
import com.hotdog.saas.infra.dao.TestMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestRepositoryImpl implements TestRepository {

    @Autowired
    private TestMapper testMapper;

    @Override
    public void test() {
        TestPO testPO = testMapper.pageQuery();
        System.out.println(testPO);
    }
}
