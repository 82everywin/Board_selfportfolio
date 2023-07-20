package com.koreait.controllers.members;

import com.koreait.models.member.MemberSaveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Join;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberSaveService saveService;

    @GetMapping("/join")
    public String join(@ModelAttribute JoinForm joinForm,  Model model){ // Model에 해당하는거 -> JoinForm


        return "member/join";
    }

    @PostMapping("/join")
    public String joinPs(@Valid JoinForm joinForm, Errors errors){

        if(errors.hasErrors()){
            return "member/join";
        }

        saveService.save(joinForm);

        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String login() {

        return "member/login";
    }
}
