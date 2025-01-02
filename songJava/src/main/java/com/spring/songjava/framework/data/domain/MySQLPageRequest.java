package com.spring.songjava.framework.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

// MySQL 페이지 요청 정보 및 계산된 값
@Data
public class MySQLPageRequest {

    @Schema(description = "Current page number", example = "1")
    private int page;

    @Schema(description = "Number of records per page", example = "10")
    private int size;

    @JsonIgnore
    @Schema(hidden = true)
    private int limit;

    @JsonIgnore
    @Schema(hidden = true)
    private int offset;

    public MySQLPageRequest(int page, int size, int limit, int offset) {
        this.page = page;
        this.size = size;
        this.limit = limit;
        this.offset = offset;
    }
}
