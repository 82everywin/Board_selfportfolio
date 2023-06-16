package com.koreait.configs.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 사이트 설정 유지
 */
@Component
public class SiteConfigInterceptor implements HandlerInterceptor {

    /**
     *  반환값 boolean -> True, False :: 다음에 불러올 것을 통제할 수 있음
     *  if 반환값 false : 다음 controller(Bean 객체)를 불러오지 않음
     *  -> 요청처리 하지 않음 :: 통제.제어 기능을 할 수 있음
     *
     *  --> 주로 인가, 인증 관련된 페이지/기능에서 사용함.
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        request.setAttribute("cssJsVersion",1);

        return true;
    }
}
