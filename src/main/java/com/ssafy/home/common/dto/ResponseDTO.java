package com.ssafy.home.common.dto;

import lombok.Data;

@Data
public class ResponseDTO {
    private String msg, errMsg, status;
    private Object body;
}
