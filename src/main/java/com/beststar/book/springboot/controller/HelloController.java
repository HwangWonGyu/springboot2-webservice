package com.beststar.book.springboot.controller;

import com.beststar.book.springboot.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
* @RestController
* 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어줌
* 예전에는 @ResponseBody를 각 메서드마다 선언했던 것을 한번에 사용할 수 있게 해줌
* */

@RestController
public class HelloController {

    /*
    * @GetMapping
    * HTTP Method인 Get의 요청을 받을 수 있는 API를 만들어 줌
    * 예전에는 @RequestMapping(method=RequestMethod.GET)으로 사용됐음
    * */
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }

    // @RequestParam
    // 외부에서 API로 넘긴 파라미터를 가져오는 애노테이션
    // 여기서는 외부에서 name (@RequestParam("name")) 이란 이름으로 넘긴 파라미터를
    // 메서드 파라미터 name(String name)에 저장하게 됨
    // - 장점
    // 적용된 파라미터마다 세부 설정(required, defaultValue 등) 가능
    // - 단점
    // 데이터 필드수가 많을수록 선언할 파라미터도 많아져 유지보수성 떨어짐
    // 타입검증을 하지 않아 TypeMisMatch 예외를 발생시켜 사용자에게 4xx Status Code를 줌
}
