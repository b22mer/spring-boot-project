package com.ssafy.home.dto;

import lombok.Data;

@Data
public class ResponseDTO {
    private String msg, errMsg, status;
    private Object body;
}
