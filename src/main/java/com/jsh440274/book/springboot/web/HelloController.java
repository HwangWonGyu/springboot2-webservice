package com.jsh440274.book.springboot.web;
import com.jsh440274.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(
            //RequestParam은 외부 API에서 넘긴 파라미터를 가져오는 어노테이션이다.
            //외부 API에서 name이라고 넘긴 파라미터를 String name에 저장하게 됨.
            @RequestParam("name") String name,
            @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }
}
