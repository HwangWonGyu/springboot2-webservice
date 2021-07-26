package com.example.springproject.web;

import com.example.springproject.service.posts.PostsService;
import com.example.springproject.web.dto.PostsResponseDto;
import com.example.springproject.web.dto.PostsSaveRequestDto;
import com.example.springproject.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }
}

// RestController와 Controller의 차이
// 나는 뷰를 찾느냐 안찾느냐로 이해했다.
