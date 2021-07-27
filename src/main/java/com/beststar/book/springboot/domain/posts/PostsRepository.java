package com.beststar.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<Entity 클래스, PK 타입> -> 이를 상속하면 기본적인 CRUD 메서드가 자동으로 생성됨
// @Repository를 추가할 필요도 없음

// Entity 클래스와 기본 Entity Repository는 함께 위치해야함
// 나중에 프로젝트 규모가 커져 도메인별로 프로젝트를 분리해야 한다면
// 이때 Entity 클래스와 기본 Repository는 함께 움직여야 하므로
// 도메인 패키지에서 함께 관리
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
