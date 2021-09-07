package com.koscom.springboot.domain.posts;


import org.springframework.data.jpa.repository.JpaRepository;


// Posts, Long ==> 대상이 되는 Entity, PK타입
// JPA repository 상속을 받은 인터페이스는 CRUD 가 모두 자동구현된다.
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
