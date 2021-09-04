package com.jsh440274.book.springboot.web;

import com.jsh440274.book.springboot.config.auth.LoginUser;
import com.jsh440274.book.springboot.config.auth.dto.SessionUser;
import com.jsh440274.book.springboot.service.posts.PostsService;
import com.jsh440274.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    //머스태치 스타터가 자동으로 src/main/resources/templates/index.mustache로 반환해줌.
    //앞에있는 디폴트 경로 + 리턴값 + .mustache확장자
//    @GetMapping("/")
//    public String index(Model model){
//        model.addAttribute("posts", postsService.findAllDesc());
//        SessionUser user = (SessionUser) httpSession.getAttribute("user");
//        if(user != null){
//            model.addAttribute("userName", user.getName());
//        }
//        return "index";
//    }

    //어노테이션 기반으로 개선
    //세션값을 일일히 가져오던 방식에서 어노테이션을 사용해서 가져올 수 있도록 개션.(중복을 줄이고 유지보수성을 늘리는 방향으로)
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());
        if(user != null){
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
