package com.koreait.commons;

import org.springframework.http.HttpStatus;

import java.util.ResourceBundle;

/**
 * 공통 예외
 *
 */
public class CommonException extends RuntimeException{

    protected static ResourceBundle bundleValidation;  //메세지 코드 불러오는 번들

    protected static ResourceBundle bundleError; // 에러코드 불러오는 번들

    protected HttpStatus httpStatus;  //상태 코드

    static { //처음부터 초기화
        bundleValidation= ResourceBundle.getBundle("messages.validations");
        bundleError = ResourceBundle.getBundle("messages.errors");
    }

    public CommonException (String message){
        super(message);
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    public CommonException (String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getStatus() {
        return httpStatus;
    }


}
