package com.koreait.commons.rests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class JSONData<T> {

    // 오류가 있거나 할 때 정보를 알기 위해서!
    // 형식을 통일화 해서 관리하기 쉽게 ..

    private boolean success;  // 주로 False 일때 주로 사용

    private HttpStatus status =HttpStatus.OK ; //응답코드 OK=200

    private String message; //오류 메세지 보내는거..

    private T data;  // 다양한 타입으로 나올 수 있어서 제네릭타입 T 로 함.
}
