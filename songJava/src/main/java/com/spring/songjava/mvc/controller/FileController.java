package com.spring.songjava.mvc.controller;

import com.spring.songjava.configuration.GlobalConfig;
import com.spring.songjava.configuration.exception.BaseException;
import com.spring.songjava.configuration.http.BaseResponse;
import com.spring.songjava.configuration.http.BaseResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

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
    public BaseResponse<Boolean> save(@RequestParam("uploadFile") MultipartFile multipartFile) {
        logger.debug("multipartFile : {}", multipartFile);
        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new BaseException(BaseResponseCode.DATA_IS_NULL);
        }
        String uploadFilePath = config.getUploadPath();
        logger.debug("uploadFilePath : {}", uploadFilePath);
        String prefix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1, multipartFile.getOriginalFilename().length());
        String filename = UUID.randomUUID().toString() + "." + prefix;
        logger.info("filename : {}", filename);

        File folder = new File(uploadFilePath);
        // 폴더가 없다면 생성
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        String pathname = uploadFilePath + filename;
        File dest = new File(pathname);
        try {
            multipartFile.transferTo(dest);
        } catch (IllegalStateException | IOException e) {
            logger.error("e", e);
        }

        return new BaseResponse<>(true);
    }
}
