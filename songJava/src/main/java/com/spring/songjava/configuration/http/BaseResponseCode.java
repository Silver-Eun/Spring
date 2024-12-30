package com.spring.songjava.configuration.http;

public enum BaseResponseCode {

    SUCCESS(200),
    ERROR(500),
    ;

    private final int status;
    
    BaseResponseCode(int status) {
        this.status = status;
    }

    public int status() {
        return status;
    }
}
