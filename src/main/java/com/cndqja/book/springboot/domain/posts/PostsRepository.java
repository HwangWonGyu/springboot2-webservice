package com.cndqja.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// @Repository를 추가할 필요도 없이 Entity 클래스와 기본 Entity Repository가 함께 위치해 있으면 된다.
// 신기하다. 이게 가능한 것은 extends JpaRepository인가??
// JpaRepository의 어떤 코드가 이걸 가능하게 하는걸까??
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
// 그리고 Spring Framework나 라이브러리들 중 많은 것들이 내부적으로 리플렉션이 사용되어 있다고들 하는데,
// 리플렉션이 정확히 무엇일까?? 파일을 읽어오는거다라고 대충 알고 있었는데,,

// https://medium.com/msolo021015/%EC%9E%90%EB%B0%94-reflection%EC%9D%B4%EB%9E%80-ee71caf7eec5
// JVM에서 실행되는 애플리케이션의 런타임 동작을 검사하거나 수정할 수 있는 기능이 필요한 프로그램에서 사용한다고 한다.
// "Posts".getClass();와 같은 식으로 사용하고, 클래스를 직접 명시하여 가져오는 것이다.
