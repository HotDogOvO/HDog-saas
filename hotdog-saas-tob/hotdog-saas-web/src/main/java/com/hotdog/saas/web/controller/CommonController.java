package com.hotdog.saas.web.controller;

import com.hotdog.saas.application.entity.request.education.CreateEducationCourseRequest;
import com.hotdog.saas.application.entity.request.education.DeleteEducationCourseRequest;
import com.hotdog.saas.application.entity.request.education.EducationCoursePageRequest;
import com.hotdog.saas.application.entity.request.education.QueryEducationCourseRequest;
import com.hotdog.saas.application.entity.request.education.UpdateEducationCourseRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.education.EducationCourseDTO;
import com.hotdog.saas.application.facade.EducationCourseFacade;
import com.hotdog.saas.domain.foundation.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "公用接口管理")
@RestController
@RequestMapping("/api/hotdog/v1/common")
public class CommonController {

    @Autowired
    private FileService fileService;

    @Operation(summary = "创建课程")
    @PostMapping("/file/upload")
    public BaseResponse<Boolean> createEducationCourse() {
        File  file = new File("/Users/donghe.wu.o/Downloads/1.txt");
        try {
            InputStream inputStream = new FileInputStream(file);
            fileService.upload("test", inputStream, ".txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return new BaseResponse();
    }

}
