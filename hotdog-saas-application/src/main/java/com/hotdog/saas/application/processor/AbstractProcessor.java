package com.hotdog.saas.application.processor;

import com.hotdog.saas.application.template.TestProcessorTemplate;

public abstract class AbstractProcessor implements TestProcessorTemplate {

    protected void print(){
        System.out.println("this is abstract processor");
    }
}
