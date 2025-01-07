package com.spring.songjava.configuration.servlet.handler;

import com.spring.songjava.configuration.exception.BaseException;
import com.spring.songjava.configuration.http.BaseResponse;
import com.spring.songjava.configuration.http.BaseResponseCode;
import com.spring.songjava.framework.data.web.bind.annotation.RequestConfig;
import com.spring.songjava.mvc.domain.MenuType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class BaseHandlerInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("preHandle requestURI : {}", request.getRequestURI());
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            logger.info("handlerMethod : {}", handlerMethod);
            RequestConfig requestConfig = handlerMethod.getMethodAnnotation(RequestConfig.class);
            if (requestConfig != null) {
                // 로그인 체크가 필수인 경우
                if (requestConfig.loginCheck()) {
                    throw new BaseException(BaseResponseCode.LOGIN_REQUIRED);
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        if (handler instanceof HandlerMethod) {
            if (modelAndView != null) {
                modelAndView.addObject("menuTypes", MenuType.values());
            }
        }
        logger.info("postHandle requestURI : {}", request.getRequestURI());
    }
}