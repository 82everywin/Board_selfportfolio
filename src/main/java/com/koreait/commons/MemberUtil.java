package com.koreait.commons;

import com.koreait.commons.constants.Role;
import com.koreait.entities.Member;
import com.koreait.models.member.MemberInfo;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 세션쪽에서 접근
 */
@Component
public class MemberUtil {

    @Autowired
    private HttpSession session;

    /**
     * 로그인 여부
     * @return
     */
    public boolean isLogin() {
        return getMember() != null ;
    }

    /**
     * 관리자 여부
     * @return
     */
    public boolean isAdmin() {
        return isLogin() && getMember().getRoles() == Role.ADMIN;
    }

    public MemberInfo getMember(){
        //세션 유지
        MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");
        return memberInfo;
    }

    public Member getEntity(){
        if(isLogin()){
            Member member = new ModelMapper().map(getMember(),Member.class);
            return member;
        }
        return null;
    }
}
