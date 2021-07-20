package com.jsh440274.book.springboot.web;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//해당 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어 준다.
//각 메소드마다 @ResponseBody를 선언했던 것과 동일한 역할.
@RestController
public class HelloController {

    //Get요청을 받을 수 있는 api의 형식으로 만들어준다.
    //"/hello"로 요청이 들어오면 문자열 hello를 반환한다.
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
