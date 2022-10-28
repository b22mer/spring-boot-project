package com.ssafy.home.board.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class Board {
    private int code;
    private int originNo;
    private int groupOrd;
    private int groupLayer;
    private String title;
    private String content;
    private String writer;
    private Date reg_datetime;

}