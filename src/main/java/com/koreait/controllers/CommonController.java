package com.koreait.controllers;

import com.koreait.commons.CommonException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.korea.it.controllers")
public class CommonController {

    @ExceptionHandler(Exception.class)
    public String errorHandler(Exception e, Model model, HttpServletResponse response){

        int status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        /**
         * 여기서 발생하는 error 가 아니라 CommonException 에서 발생하는 오류가 있을 경우
         */
        if(e instanceof CommonException){
            CommonException commonException = (CommonException) e;
            status = commonException.getStatus().value();

        }
        response.setStatus(status);

        model.addAttribute("status",status);
        model.addAttribute("message",e.getMessage());
        model.addAttribute("exception",e);

        return "error/common";
    }
}
