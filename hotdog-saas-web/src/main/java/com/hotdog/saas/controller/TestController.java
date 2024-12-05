package com.hotdog.saas.controller;

import com.hotdog.saas.application.facade.TestFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TestController {

    @Autowired
    private TestFacade testFacade;

    @PostMapping("/test")
    public String test() {
        testFacade.test();
        return "test";
    }
}
