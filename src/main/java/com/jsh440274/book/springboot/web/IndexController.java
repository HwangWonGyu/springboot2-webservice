package com.jsh440274.book.springboot.web;

import com.jsh440274.book.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    //머스태치 스타터가 자동으로 src/main/resources/templates/index.mustache로 반환해줌.
    //앞에있는 디폴트 경로 + 리턴값 + .mustache확장자
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }
}
