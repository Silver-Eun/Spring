package com.spring.songjava.mvc.domain;

public enum MenuType {

    communit(BoardType.COMMUNITY, "menu.community", "/community"),
    notice(BoardType.NOTICE, "menu.notice", "/notice"),
    faq(BoardType.FAQ, "menu.faq", "/faq"),
    inquiry(BoardType.INQUIRY, "menu.inquiry", "/inquiry"),
    ;

    private final BoardType boardType;
    private String menuCode;
    private String url;

    MenuType(BoardType boardType, String menuCode, String url) {
        this.boardType = boardType;
        this.menuCode = menuCode;
        this.url = url;
    }

    public BoardType boardType() {
        return boardType;
    }

    public String menuCode() {
        return menuCode;
    }

    public String url() {
        return url;
    }
}
