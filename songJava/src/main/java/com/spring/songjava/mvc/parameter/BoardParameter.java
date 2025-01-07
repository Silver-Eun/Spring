package com.spring.songjava.mvc.parameter;

import com.spring.songjava.mvc.domain.BoardType;
import lombok.Data;

@Data
public class BoardParameter {
    private int boardSeq;
    private String title;
    private String contents;
    private boolean delYn;
    private BoardType boardType;

    public BoardParameter() {
    }

    public BoardParameter(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
