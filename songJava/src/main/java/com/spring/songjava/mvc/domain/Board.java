package com.spring.songjava.mvc.domain;

import java.util.Date;

public class Board {

    private int boardSeq;
    private BoardType boardType;
    private String title;
    private String contents;
    private Date regDate;

    public int getBoardSeq() {
        return boardSeq;
    }

}
