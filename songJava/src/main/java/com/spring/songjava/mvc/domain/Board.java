package com.spring.songjava.mvc.domain;

import lombok.Data;

import java.util.Date;

public class Board {

    private int boardSeq;
    private String title;
    private String contents;
    private Date regDate;

    public int getBoardSeq() {
        return boardSeq;
    }
}
