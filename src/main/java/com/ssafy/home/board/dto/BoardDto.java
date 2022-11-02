package com.ssafy.home.board.dto;

import java.util.Date;
import java.util.List;

public class BoardDto {

    private int code;
    private int articleNo;
    private int groupOrd;
    private int groupLayer;
    private String title;
    private String content;
    private String writer;
    private int hit;
    private Date registerTime;
    private String id;
    private List<FileDTO> fileInfos;

    @Override
    public String toString() {
        return "BoardDto{" +
                "code=" + code +
                ", articleNo=" + articleNo +
                ", groupOrd=" + groupOrd +
                ", groupLayer=" + groupLayer +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", hit=" + hit +
                ", registerTime=" + registerTime +
                ", id='" + id + '\'' +
                ", fileInfos=" + fileInfos +
                '}';
    }

    public BoardDto() {
    }

    public BoardDto(int code, int articleNo, int groupOrd, int groupLayer, String title, String content, String writer, int hit, Date registerTime, String id, List<FileDTO> fileInfos) {
        this.code = code;
        this.articleNo = articleNo;
        this.groupOrd = groupOrd;
        this.groupLayer = groupLayer;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.hit = hit;
        this.registerTime = registerTime;
        this.id = id;
        this.fileInfos = fileInfos;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getArticleNo() {
        return articleNo;
    }

    public void setArticleNo(int articleNo) {
        this.articleNo = articleNo;
    }

    public int getGroupOrd() {
        return groupOrd;
    }

    public void setGroupOrd(int groupOrd) {
        this.groupOrd = groupOrd;
    }

    public int getGroupLayer() {
        return groupLayer;
    }

    public void setGroupLayer(int groupLayer) {
        this.groupLayer = groupLayer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<FileDTO> getFileInfos() {
        return fileInfos;
    }

    public void setFileInfos(List<FileDTO> fileInfos) {
        this.fileInfos = fileInfos;
    }
}
