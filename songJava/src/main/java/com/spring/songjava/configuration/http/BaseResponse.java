package com.spring.songjava.configuration.http;

import lombok.Data;

@Data
public class BaseResponse<T> {
    private BaseResponseCode code;
    private String message;
    private T data;

    public BaseResponse(T data) {
        this.code = BaseResponseCode.SUCCESS;
        this.data = data;
    }

    public BaseResponse(BaseResponseCode code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResponseCode getCode() {
        return code;
    }

    public void setCode(BaseResponseCode code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
