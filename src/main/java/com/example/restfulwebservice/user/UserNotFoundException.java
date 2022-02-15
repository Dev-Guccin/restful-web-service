package com.example.restfulwebservice.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

// HTTP status Code
// 2xx -> OK
// 4xx -> Client mista  ke
// 5xx -> Server mistake
// 5xx -> not found로 바꾸어 줘야 안전함.
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);// 부모 생성자가 실행된다. // RuntimeException의 생성자 호출
    }
}
