package com.hotdog.saas.application.facade.impl;

import com.hotdog.saas.application.facade.TestFacade;
import com.hotdog.saas.application.processor.TestProcessor;
import com.hotdog.saas.application.template.BaseProcess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestFacadeImpl extends BaseProcess implements TestFacade {

    @Autowired
    private TestProcessor testProcessor;

    @Override
    public void test() {
        this.doBiz(this.testProcessor);
    }
}
