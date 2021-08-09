package com.jsh440274.book.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    //머스태치 스타터가 자동으로 src/main/resources/templates/index.mustache로 반환해줌.
    //앞에있는 디폴트 경로 + 리턴값 + .mustache확장자
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }
}
