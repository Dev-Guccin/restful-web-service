package com.example.restfulwebservice.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    // GET
    // /hello-world (endpoint)
    // @RequestMapping()
    @GetMapping(path="/hello-world") // 어떤 메소드인지 명시할 수 있다.
    public String helloWorld(){
        return "hello";
    }

    @GetMapping(path="/hello-world-bean") // 어떤 메소드인지 명시할 수 있다.
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World"); // 객체 반환의 경우 JSON으로 바꿔서 리턴한다.
    }

    @GetMapping(path="/hello-world-bean/path-variable/{name}") // 어떤 메소드인지 명시할 수 있다.
    public HelloWorldBean helloWorldBean(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World, %s",name)); // 객체 반환의 경우 JSON으로 바꿔서 리턴한다.
    }
}
