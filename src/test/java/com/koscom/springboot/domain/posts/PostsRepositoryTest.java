package com.koscom.springboot.domain.posts;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    //spring boot에서 테스트 환경을 한번 만들고 유지해서 사용하는데 이를 초기화해주기우해
    //@AfterEach
    void teardown() {
        System.out.println("tear");
        postsRepository.deleteAll();
    }

    @Test
    void 게시글저장_불러오기(){

        System.out.println("test1");
        String title = "테스트 타이틀1";
        String content = "테스트 분석1";

        postsRepository.save(Posts.builder()
                        .title(title)
                        .content(content)
                .build());

        List<Posts> result = postsRepository.findAll();

        System.out.println(result.get(0).getId()); //DB가 만들어준값
        System.out.println(result.get(0).getTitle());

        assertThat(result.get(0).getTitle()).isEqualTo(title);
        assertThat(result.get(0).getContent()).isEqualTo(content);

    }

    @Test
    void 게시글저장_불러오기2() {

        System.out.println("test2");
        String title = "테스트 타이틀2";
        String content = "테스트 본문2";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .build());

        List<Posts> result = postsRepository.findAll();

        System.out.println(result.size());
        assertThat(result).hasSize(1);
    }

    @Test
    void 게시글저장_불러오기3() {

        System.out.println("test3");
        String title = "테스트 타이틀";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .build());

        List<Posts> result = postsRepository.findAll();

        System.out.println(result.size());
        assertThat(result).hasSize(1);
    }
}
