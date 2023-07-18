# 스프링보트 포트폴리오 ( 개별 ) 

## 초기설정 
- 스프링 설정
- 오라클 driver/mysql driver
- querydsl 의존성 JPA/ APT 5.0.0 추가 
- ModelMapper(3.0.0)
- application.properties
- application-test.properties 설정 추가
***

## 스프링 시큐리티 / 타임리프 설정 
-타임리프 레이아웃 템플릿 완성
-스프링 시큐리티 설정 
	- 로그인 양식
	- UserDetails, UserDetailsService 인터페이스 구현 클래스 

* 기본 에러 응답코드 처리
	- 템플릿 경로 /error/응답코드.html
  		- timestamp : 오류 발생 시각
    	- status : HTTP 상태 코드
        - error : 오류 발생 원인
        - exception : 예외 객체
        - errors : Errors 객체
        - trace : printStackTrace()
        - path : 오류의 유입 URL

-관리자페이지 
	- 기본설정
	- 게시판 설정 