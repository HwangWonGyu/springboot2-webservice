package com.beststar.book.springboot.service.posts;

import com.beststar.book.springboot.domain.posts.Posts;
import com.beststar.book.springboot.domain.posts.PostsRepository;
import com.beststar.book.springboot.web.dto.PostsListResponseDto;
import com.beststar.book.springboot.web.dto.PostsResponseDto;
import com.beststar.book.springboot.web.dto.PostsSaveRequestDto;
import com.beststar.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)
    // @Transactional(readOnly = true)
    // 트랜잭션 범위는 유지하되 조회 기능만 남겨두어 조회 속도가 개선되기 때문에
    // 등록, 수정, 삭제 기능이 전혀 없는 서비스 메서드에서 사용하는 것을 추천
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) // map( new PostsListResponseDto(posts) ) 와 같음
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
        // JpaRepository에서 이미 delete 메서드를 지원하고 있으니 이를 활용
        // 엔티티를 파라미터로 삭제할 수도 있고, deleteById 메서드를 이용하면 id로 삭제할 수도 있음
        // 존재하는 Posts인지 확인을 위해 엔티티 조회 후 그대로 삭제
    }
}
