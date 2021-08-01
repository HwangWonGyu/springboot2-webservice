package com.beststar.book.springboot.service.posts;

import com.beststar.book.springboot.domain.posts.Posts;
import com.beststar.book.springboot.domain.posts.PostsRepository;
import com.beststar.book.springboot.web.dto.PostsResponseDto;
import com.beststar.book.springboot.web.dto.PostsSaveRequestDto;
import com.beststar.book.springboot.web.dto.PostsUpdateRequestDto;
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

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;

        // 여기서 DB에 쿼리를 날리는 부분이 없는데, 이게 가능한 이유는 JPA의 '영속성 컨텍스트' 때문

        // - 영속성 컨텍스트
        // 엔티티를 영구 저장하는 환경
        // JPA의 핵심 내용은 엔티티가 영속성 컨텍스트에 포함되어 있냐 아니냐로 갈림

        // JPA의 엔티티 매니저가 활성화된 상태로 (Spring Data Jpa를 쓴다면 기본 옵션)
        // 트랜잭선 안에서 DB에서 데이터를 가져오면
        // 이 데이터는 영속성 컨텍스트가 유지된 상태
        // 이 상태에서 해당 데이터 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영
        // 즉, Entity 객체의 값만 변경하면 별도로 Update 쿼리를 날릴 필요가 없음
        // 이 개념을 더티 체킹(Dirty Checking)이라고 함
        // https://jojoldu.tistory.com/415
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
}
