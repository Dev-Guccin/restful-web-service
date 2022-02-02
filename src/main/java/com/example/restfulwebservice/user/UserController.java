package com.example.restfulwebservice.user;

import org.springframework.web.bind.annotation.*;

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
        return service.findOne(id);
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user){
        User savedUser = service.save(user);

    }
}
