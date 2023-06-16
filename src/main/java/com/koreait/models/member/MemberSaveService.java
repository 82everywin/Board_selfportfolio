package com.koreait.models.member;

import com.koreait.controllers.members.JoinForm;
import com.koreait.entities.Member;
import com.koreait.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *  회원 정보 추가, 수정
 *  - 수정시 비밀번호는 값이 있을 때만 수정
 */
@Service
@RequiredArgsConstructor
public class MemberSaveService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    /**
     * 기존게 없으면 추가, 있으면 수정
     * @param joinForm
     */
    public void save(JoinForm joinForm){
        Member member = new ModelMapper().map(joinForm,Member.class);
        member.setUserPw(passwordEncoder.encode(joinForm.getUserPw()));

        memberRepository.saveAndFlush(member);
    }
}
