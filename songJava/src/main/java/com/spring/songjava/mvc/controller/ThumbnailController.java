package com.spring.songjava.mvc.controller;

import com.spring.songjava.configuration.exception.BaseException;
import com.spring.songjava.configuration.http.BaseResponseCode;
import com.spring.songjava.mvc.domain.ThumbnailType;
import com.spring.songjava.mvc.domain.UploadFile;
import com.spring.songjava.mvc.service.UploadFileService;
import jakarta.servlet.http.HttpServletResponse;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
@RequestMapping("/thumbnail")
public class ThumbnailController {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UploadFileService uploadFileService;

    @GetMapping("/make/{uploadFIleSeq}/{thumbnailType}")
    public void make(@PathVariable int uploadFIleSeq,
                     @PathVariable ThumbnailType thumbnailType,
                     HttpServletResponse response) {
        UploadFile uploadFile = uploadFileService.get(uploadFIleSeq);
        if (uploadFile == null) {
            throw new BaseException(BaseResponseCode.UPLOAD_FILE_IS_NULL);
        }
        String pathname = uploadFile.getPathname();
        File file = new File(pathname);
        if (!file.isFile()) {
            throw new BaseException((BaseResponseCode.UPLOAD_FILE_IS_NULL));
        }
        try {
            String thumbPathname = uploadFile.getPathname()
                    .replace(".", "_" + thumbnailType.width() + "_" + thumbnailType.height() + ".");
            File thumbnailFile = new File(thumbPathname);
            if (!thumbnailFile.isFile()) {
                Thumbnails.of(pathname)
                        .size(thumbnailType.width(), thumbnailType.height())
                        .toFile(thumbPathname);
            }
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
            FileCopyUtils.copy(new FileInputStream(thumbnailFile), response.getOutputStream());
            logger.info("thumbPathname : {}", thumbPathname);
        } catch (IOException e) {
            logger.error("e", e);
        }
    }
}
