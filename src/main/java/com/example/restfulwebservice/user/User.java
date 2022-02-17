package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
//@JsonIgnoreProperties(value = {"password"}) // 필드를 외부로 노출시키지 않는다.
@NoArgsConstructor
//@JsonFilter("UserInfo")
@ApiModel(description="사용자 상세 정보를 위한 도메인 객체")
@Entity // 이를 통해 JPA에서 클래스의 정보를 분석하여 테이블로 만든다.
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min=2, message ="Name은 2글자 이상 입력해 주세요.")
    @ApiModelProperty(notes="사용자 이름을 입력해 주세요.")
    private String name;

    @Past
    @ApiModelProperty(notes="사용자 등록일을 입력해 주세요.")
    private Date joinDate;

//    @JsonIgnore // 전달할때 무시하고 전달한다. 즉, 노출되지 않는다.
    @ApiModelProperty(notes="사용자 패스워드를 입력해 주세요.")
    private String password;
    @ApiModelProperty(notes="사용자 주민번호를 입력해 주세요.")
    private String ssn;
}
