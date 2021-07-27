package com.beststar.book.springboot.service.posts;

import com.beststar.book.springboot.domain.posts.PostsRepository;
import com.beststar.book.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// @RequiredArgsConstructor
// final이 선언된 모든 필드를 인자값으로 하는 생성자를 대신
// 해당 클래스의 의존성 관계가 변경될 떄마다 생성자 코드를 계속해서 수정하는 번거로움을 해결할 수 있음
@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
}
