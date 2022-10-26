package com.ssafy.home.aop;

import com.ssafy.home.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLSyntaxErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> NullPointerException(NullPointerException n) {
        n.printStackTrace();
        ErrorResponse response = new ErrorResponse(404, n.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLSyntaxErrorException.class)
    public ResponseEntity<ErrorResponse> SqlSyntaxErrorException(SQLSyntaxErrorException s) {
        System.out.println("sql syntax error");
        ErrorResponse response = new ErrorResponse(404, s.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    public ResponseEntity<ErrorResponse> BadSqlGrammarException(BadSqlGrammarException b) {
        b.printStackTrace();
        ErrorResponse response = new ErrorResponse(404, b.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public String Exception(Exception e) {
        System.out.println("Exception");
        return "sql grammar error";
    }

    @ExceptionHandler(RuntimeException.class)
    public String RuntimeException(RuntimeException r) {
        System.out.println("run time exception");
        return "run time exception";
    }

}
