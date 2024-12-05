package com.hotdog.saas.application.processor;

import com.hotdog.saas.domain.repository.TestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestProcessor extends AbstractProcessor {

    @Autowired
    private TestRepository testRepository;

    @Override
    public void test() {
        print();
        testRepository.test();
    }
}
