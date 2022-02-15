package com.example.restfulwebservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data // @Getter,@Setter,@RequiredArgsConstructor,@ToString,@EqualsAndHashCode 모든접근자,설정자 생성
@AllArgsConstructor // 생성자 자동으로 설정
@NoArgsConstructor // 파라미터 없는 생성자
public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String details;
}
