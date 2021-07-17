package com.example.springproject.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/helllo")
    public String hello(){
        return "hello";
    }
}
