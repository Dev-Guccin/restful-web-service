package com.example.restfulwebservice.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUserController {

    private UserDaoService service;

    public AdminUserController(UserDaoService service){
        this.service = service;
    }

    @GetMapping("/users")
    public MappingJacksonValue retrieveAllUsers(){
        List<User> users = service.findAll();

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate","password", "ssn");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(users);
        mapping.setFilters(filters);
        return mapping;
    }

    // GET /admin/users/1 or /admin/v1/users/10 -> String 으로 전달
//    @GetMapping("/v1/users/{id}")
//    @GetMapping(value="/users/{id}/", params="version=1")
//    @GetMapping(value="/users/{id}", headers = "X-API-VERSIONS=1")
    @GetMapping(value="/users/{id}", produces="application/vnd.company.appv1+json")
    public MappingJacksonValue retrieveUserV1(@PathVariable int id){ // int로 매핑해서 받는다.
        User user = service.findOne(id);
        if(user == null){
            // 직접 예외클래스를 생성하여 원하는 에러메세지를 전송한다.
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate","password", "ssn");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(filters);

        return mapping;
    }
//    @GetMapping("/v2/users/{id}")
//    @GetMapping(value="/users/{id}/", params="version=2")
//    @GetMapping(value="/users/{id}", headers = "X-API-VERSIONS=2"
    @GetMapping(value="/users/{id}", produces="application/vnd.company.appv2+json")
    public MappingJacksonValue retrieveUserV2(@PathVariable int id){ // int로 매핑해서 받는다.
        User user = service.findOne(id);

        if(user == null){
            // 직접 예외클래스를 생성하여 원하는 에러메세지를 전송한다.
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        // User -> User2
        UserV2 userV2 = new UserV2();
        BeanUtils.copyProperties(user,userV2); // 빈 들의 유틸을 도와주는 클래스이다.지금은
        userV2.setGrade("VIP");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate","grade");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(userV2);
        mapping.setFilters(filters);

        return mapping;
    }

}
