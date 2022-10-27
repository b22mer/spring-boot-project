package com.ssafy.home.board.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Board {
    private int code,originNo,groupOrd,groupLayer;
    private String title,content,writer;
    private Date reg_datetime;

}