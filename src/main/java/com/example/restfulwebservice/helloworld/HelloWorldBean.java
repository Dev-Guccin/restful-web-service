package com.example.restfulwebservice.helloworld;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// lombok => 빈 클래스를 만들때 get,set,constructor를 자동으로 생성해준다.
@Data // 이 선언만으로 HelloWorldBean에 대한 생성자, 세터,게터 전부 생성됨.
@AllArgsConstructor // 알아서 초기화 해준다. 변수에 대한 생성자 생성
@NoArgsConstructor // 매개변수가 없는 생성자를 생성
public class HelloWorldBean {
    private String message;
}
