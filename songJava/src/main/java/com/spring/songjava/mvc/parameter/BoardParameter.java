package com.spring.songjava.mvc.parameter;

public class BoardParameter {
    private int boardSeq;
    private String title;
    private String contents;
    private boolean delYn;

    public int getBoardSeq() {
        return boardSeq;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public BoardParameter() {
    }

    public BoardParameter(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
