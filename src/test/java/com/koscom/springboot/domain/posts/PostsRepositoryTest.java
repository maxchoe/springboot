package com.koscom.springboot.domain.posts;


import com.koscom.springboot.service.PostsService;
import com.koscom.springboot.web.dto.posts.PostsUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
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

    @Test
    public void 등록시간_수정시간이_저장된다(){
        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());
        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>> createDate="+posts.getCreatedDate()+", modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }


    @Test
    void Posts를_수정하면_수정시간이_갱신된다() {
        // 미리 저장된 값을 하나 생성해둠 ("1", "2");
        Posts save = postsRepository.save(Posts.builder()
                .title("1")
                .content("2")
                .build());
        LocalDateTime beforeTime = save.getModifiedDate();

        System.out.println("beforeTime=" + beforeTime);

        postsService.update(save.getId(), PostsUpdateRequestDto.builder()
                .title("test")
                .content("test2")
                .build());

        List<Posts> result = postsRepository.findAll();
        LocalDateTime newTime = result.get(0).getModifiedDate();

        System.out.println("newTime="+newTime);
        assertThat(newTime).isAfter(beforeTime);
    }

}
