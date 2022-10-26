package com.ssafy.home.aop;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionAdvice {

    @ExceptionHandler(Exception.class) // 예외를 핸들링 하는 메서드 , 이름은 상관 없다
    public String a(Exception e) {
        // 모든 예외를 받는 메서드
        e.printStackTrace();
        return "fail";
    }
}
