package com.spring.songjava.configuration.web.bind.annotation;

import com.spring.songjava.configuration.exception.BaseException;
import com.spring.songjava.configuration.http.BaseResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class BaseControllerAdvice {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = {BaseException.class})
    @ResponseStatus(HttpStatus.OK)
    @RequestBody
    private BaseResponse<?> handleBaseException(BaseException e, WebRequest request) {
        return new BaseResponse<String>(e.getResponseCode(), messageSource.getMessage(e.getResponseCode().name(), e.getArgs(), null));
    }
}
