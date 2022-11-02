package com.ssafy.home.board.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


public class BoardDto {

    private String code;
    private int articleNo;
    private int groupOrd;
    private int groupLayer;
    @NotNull
    private String title;
    @NotNull
    private String content;
    @NotNull
    private String writer;
    private int hit;
    private Date registerTime;
    private String id;
    private List<FileDTO> fileInfos;

    public BoardDto() {
    }

    public BoardDto(String code, int articleNo, int groupOrd, int groupLayer, String title, String content, String writer, int hit, Date registerTime, String id, List<FileDTO> fileInfos) {
        setCode(code);
        setArticleNo(articleNo);
        setGroupOrd(groupOrd);
        setGroupLayer(groupLayer);
        setTitle(title);
        setContent(content);
        setWriter(writer);
        setHit(hit);
        setRegisterTime(registerTime);
        setId(id);
        setFileInfos(fileInfos);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
