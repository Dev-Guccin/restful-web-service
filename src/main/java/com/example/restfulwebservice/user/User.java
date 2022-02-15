package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
//@JsonIgnoreProperties(value = {"password"}) // 필드를 외부로 노출시키지 않는다.
@NoArgsConstructor
@JsonFilter("UserInfo")
public class User {
    private Integer id;

    @Size(min=2, message ="Name은 2글자 이상 입력해 주세요.")
    private String name;

    @Past
    private Date joinDate;

//    @JsonIgnore // 전달할때 무시하고 전달한다. 즉, 노출되지 않는다.
    private String password;
    private String ssn;
}
