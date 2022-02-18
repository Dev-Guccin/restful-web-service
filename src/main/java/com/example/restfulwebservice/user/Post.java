package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    private Integer id;

    private String description;

    // User:Post -> 1:(0~N), User가 Main, Post가 Sub가 된다.
    // User==Parent, Post==Child
    @ManyToOne(fetch = FetchType.LAZY) // 지연로딩방식을 통해 포스트가 로딩되는 시점에 데이터를 가져온다.
    @JsonIgnore // json 전송시 감춘다.
    private User user;
}
