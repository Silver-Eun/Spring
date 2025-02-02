package com.spring.songjava.framework.data.domain;

import lombok.Data;

// 페이지 요청정보와 파라메터 정보
@Data
public class PageRequestParameter<T> {

    private MySQLPageRequest pageRequest;
    private T parameter;

    public PageRequestParameter(MySQLPageRequest pageRequest, T parameter) {
        this.pageRequest = pageRequest;
        this.parameter = parameter;
    }

    public T getParameter() {
        return parameter;
    }

    public void setParameter(T parameter) {
        this.parameter = parameter;
    }
}
