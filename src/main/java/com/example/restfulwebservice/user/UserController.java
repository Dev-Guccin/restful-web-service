package com.example.restfulwebservice.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private UserDaoService service;

    public UserController(UserDaoService service){
        this.service = service;
    }
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    // GET /users/1 or /users/10 -> String 으로 전달
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){ // int로 매핑해서 받는다.
        User user = service.findOne(id);
        if(user == null){
            // 직접 예외클래스를 생성하여 원하는 에러메세지를 전송한다.
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){ // 도메인으로 지정한 엔티티값을 반환가능하다.
        User savedUser = service.save(user);

        // 사용자가 요청한 값을 확인할 수 있는 uri를 반환한다.
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        // ResponseEntity는 http 헤더와 바디를 포함하는 클래스이다.
        return ResponseEntity.created(location).build(); // 201을 반환한다.
        // 좋은 api는 적절한 상태의 http status를 반환해주는게 좋은 api이다.
        // 모든 요청을 POST 혹은 200으로만 준다면 좋지 못한 api이다.
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteById(id);

        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
    }
}
