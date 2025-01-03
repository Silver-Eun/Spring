package com.spring.songjava.mvc.controller;

import com.spring.songjava.configuration.GlobalConfig;
import com.spring.songjava.configuration.http.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
public class FileController {

    Logger logger = LoggerFactory.getLogger(getClass());

    private final GlobalConfig config;

    public FileController(GlobalConfig config) {
        this.config = config;
    }

    @PostMapping("/save")
    @Operation(summary = "업로드")
    public BaseResponse<Boolean> save() {
        logger.debug("config : {}", config);
        String uploadFilePath = config.getUploadPath();
        logger.debug("uploadFilePath : {}", uploadFilePath);
        return new BaseResponse<>(true);
    }
}
