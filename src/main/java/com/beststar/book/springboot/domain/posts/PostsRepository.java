package com.beststar.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// JpaRepository<Entity 클래스, PK 타입> -> 이를 상속하면 기본적인 CRUD 메서드가 자동으로 생성됨
// @Repository를 추가할 필요도 없음

// Entity 클래스와 기본 Entity Repository는 함께 위치해야함
// 나중에 프로젝트 규모가 커져 도메인별로 프로젝트를 분리해야 한다면
// 이때 Entity 클래스와 기본 Repository는 함께 움직여야 하므로
// 도메인 패키지에서 함께 관리
public interface PostsRepository extends JpaRepository<Posts, Long> {

    // SpringDataJpa에서 제공하지 않는 메서드는 아래처럼 쿼리로 작성해도 됨
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}

// 규모가 있는 프로젝트에서의 데이터 조회는 FK의 조인, 복잡한 조건 등으로 인해
// 이런 Entity 클래스만으로 처리하기 어려워서 조회용 프레임워크를 추가로 사용
// 대표적 예로 Querydsl, jooQ, MyBatis 등이 있음
// 조회는 위 3가지 프레임워크 중 하나를 통해 조회하고,
// 등록/수정/삭제 등은 SpringDataJpa를 통해 진행

// 저자가 Querydsl을 추천하는 이유
// 1. 타입 안정성이 보장
// 단순한 문자열로 쿼리를 생성하는 것이 아니라,
// 메서드를 기반으로 쿼리를 생성하기 때문에
// 오타나 존재하지 않는 컬럼명을 명시할 경우 IDE에서 자동으로 검출됨
// 이 장점은 jooQ에서도 지원하지만 MyBatis에서는 지원하지 않음
// 2. 국내 많은 회사에서 사용중
// 쿠팡, 배민 등 JPA를 적극적으로 사용하는 회사에서는 Querydsl를 적극적으로 사용중
// 3. 레퍼런스가 많음
// 앞 2번의 장점에서 이어지는 것인데, 많은 회사와 개발자들이 사용하다보니 그만큼 국내 자료가 많음
// 어떤 문제가 발생했을 때 여러 커뮤니티에 질문하고 그에 대한 답변을 들을 수 있다는 것은 큰 장점