package com.ssafy.home.error;

import lombok.Getter;

import java.util.List;

@Getter
public class ErrorResponse {
    private String message;
    private int status;
    private List<FieldError> errors;
    private String code;

    public ErrorResponse(int i, String message) {
        this.status = i;
        this.message = message;
    }


    @Getter
    public static class FieldError {
        private String field;
        private String value;
        private String reason;
    }
}
