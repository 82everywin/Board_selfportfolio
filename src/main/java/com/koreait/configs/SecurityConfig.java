package com.koreait.configs;

import com.koreait.models.member.LoginFailureHandler;
import com.koreait.models.member.LoginSuccessHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        // 로그인 페이지에서 권한주기 .
        http.formLogin()
                .loginPage("/member/login")
                .usernameParameter("userId")
                .passwordParameter("userPw")
                .successHandler(new LoginSuccessHandler())
                .failureHandler(new LoginFailureHandler())
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("member/logout"))
                .logoutSuccessUrl("/member/login");

        http.authorizeHttpRequests()
                .requestMatchers("/mypage/**").authenticated() //회원전용 URL
                .requestMatchers("/admin/**").hasAuthority("ADMIN") //관리자 페이지 전용
                .anyRequest().permitAll(); // 그외 모든 페이지는 모든 회원이 접근 가능

        // 관리자 페이지 들어갈 때, 복잡한 절차를 거치지 않고, 정해진 URL 로만 들어 갈 수 있게끔!
        http.exceptionHandling().authenticationEntryPoint((req, res, e) ->{
            String URI = req.getRequestURI();
            String redirectURL = req.getRequestURI();
            if(URI.indexOf("/admin")!=-1){ //관리자 페이지
                redirectURL +="/error/401"; //권한 없음
            }else{
                redirectURL +="/member/login"; //회원전용일때는 로그인으로 
            }
            res.sendRedirect(redirectURL);
        });

        http.headers().frameOptions().sameOrigin(); //같은 도메인에서만 iframe 내에 통신 가능하게 (보안을 위해)

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return w -> w.ignoring().requestMatchers("/css/**","/js/**","/images/**","/errors/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
