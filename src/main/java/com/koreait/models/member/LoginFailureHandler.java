package com.koreait.models.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {

        HttpSession session = request.getSession();

        String userId= request.getParameter("userId");
        String userPw= request.getParameter("userPw");

        try{
            if (userId == null || userId.isBlank()){
                throw new LoginValidationException("userId","NotBlank.userId");
            }

            if (userPw == null || userPw.isBlank()){
                throw new LoginValidationException("userPw","NotBlank.userPw");
            }

            throw new LoginValidationException("global","validation.login.fail");
        }catch(LoginValidationException e){
            session.setAttribute(e.getField(),e.getMessage());
        }
    }
}
