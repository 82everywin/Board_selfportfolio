package com.koreait.controllers.members;

import com.koreait.commons.validators.MobileValidator;
import com.koreait.commons.validators.PasswordValidator;
import com.koreait.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class JoinValidator implements Validator , MobileValidator , PasswordValidator {
    // 기본적인 검증(Validation) -> @Bean validation 이용
    // 추가적인 검증 -> Validator 이용

    private final MemberRepository memberRepository; // 아이디 체크를 위해 DB접속 필요

    /**
     * 검증할 객체 클래스 반환
     *
     * @param clazz
     * @return JoinForm 클래스만 검증할 예정
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return JoinForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        /**
         * 1. 아이디 중복 여부
         * 2. 비밀번호 복잡성 체크
         *  - 대문자, 소문자
         *  - 숫자
         *  - 특수문자
         * 3. 비밀번호 / 비밀번호 확인 일치
         * 4. 휴대전화번호(선택) - 입력된 경우 형식 체크
         * 5. 휴대전화번호가 입력된 경우 숫자만 추출해서 다시 joinForm 객체(커맨드 객체)에 저장
         * 6. 필수 약관 동의 체크
         */

        // 1. 아이디 중복 여부

        JoinForm joinForm= (JoinForm) target;
        String userId= joinForm.getUserId();
        String userPw= joinForm.getUserPw();
        String userPwRe= joinForm.getUserPwRe();
        String mobile= joinForm.getMobile();

        if(userId !=null && !userId.isBlank() && memberRepository.exists(userId)){
            errors.rejectValue("userId","Validation.duplicate.userId");
        }

        // 2. 비밀번호 복잡성 체크
        /**
         *  2. 비밀번호 복잡성 체크
         *          *  - 대문자, 소문자
         *          *  - 숫자
         *          *  - 특수문자
         */

        if(userPw !=null && !userPw.isBlank() ){
            alphaCheck(userPw,true);
        }

        // 3. 비밀번호 / 비밀번호 확인 일치
        if( userPw !=null && userPwRe !=null &&
         !userPw.isBlank() && !userPwRe.isBlank() && !userPw.equals(userPwRe)){
            errors.rejectValue("userPwRe", "Validation.incorrect.userPwRe");
        }

        // 4. 휴대전화번호(선택) - 입력된 경우 형식 체크
        // 5. 휴대전화번호가 입력된 경우 숫자만 추출해서 다시 joinForm 객체(커맨드 객체)에 저장
        if(mobile !=null && !mobile.isBlank()){
            if(!mobileNumCheck(mobile)) {
                errors.rejectValue("mobile", "Validation.mobile");
            }

            mobile = mobile.replaceAll("\\D","");
            joinForm.setMobile(mobile);
        }

        // 6. 필수 약관 동의 체크
        boolean[] agrees= joinForm.getAgrees(); // 필수 약관

        if(agrees !=null && agrees.length>0 ){
            for(boolean agree : agrees){
                if(!agree){
                    errors.rejectValue("mobile","Validation.joinForm.agree");
                    break;
                }
            }
        }

    }
}
