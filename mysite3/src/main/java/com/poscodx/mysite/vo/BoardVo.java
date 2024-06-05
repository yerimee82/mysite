package com.poscodx.mysite.vo;

public class BoardVo {
    private Long no;
    private String title;
    private String contents;
    private String regDate;
    private int hit;
    private int gNo;
    private int oNo;
    private int depth;
    private Long userNo;
    private String userName;

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public int getgNo() {
        return gNo;
    }

    public void setgNo(int gNo) {
        this.gNo = gNo;
    }

    public int getoNo() {
        return oNo;
    }

    public void setoNo(int oNo) {
        this.oNo = oNo;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public Long getUserNo() {
        return userNo;
    }

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "BoardVo{" +
                "no=" + no +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", regDate='" + regDate + '\'' +
                ", hit=" + hit +
                ", gNo=" + gNo +
                ", oNo=" + oNo +
                ", depth=" + depth +
                ", userNo=" + userNo +
                ", userName='" + userName + '\'' +
                '}';
    }
}
