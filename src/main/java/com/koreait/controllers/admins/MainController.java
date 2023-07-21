package com.koreait.controllers.admins;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("adminMainController")
@RequestMapping("/admin")
public class MainController {

    /**
     * 관리자 메인 페이지
     * @return
     */
    @GetMapping
    public String index(){
        return "admin/index";
    }

}
