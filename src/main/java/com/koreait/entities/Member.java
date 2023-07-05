package com.koreait.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Member extends BaseEntity{

    @Id @GeneratedValue
    private Long userNo; // 회원번호

    @Column(length=0, nullable=false, unique=true )
    private String userId; // 회원 아이디

    @Column(length = 65,nullable = false)
    private String userPw;  // 회원 비밀번호\

    @Column(length=40,nullable = false)
    private String userNm; // 회원명

    @Column(length = 100, nullable = false)
    private String email; // 이메일

    private String mobile; // 휴대전화번호

    private String termsAgree; // 약관 동의 내역

}
