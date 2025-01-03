package com.spring.songjava.mvc.parameter;

import lombok.Data;

@Data
public class UploadFileParameter {
    private String pathname;
    private String filename;
    private String originalFilename;
    private int size;
    private String contentType;
    private String resourcePathname;

    public void setPathname(String pathname) {
        this.pathname = pathname;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setResourcePathname(String resourcePathname) {
        this.resourcePathname = resourcePathname;
    }
}
