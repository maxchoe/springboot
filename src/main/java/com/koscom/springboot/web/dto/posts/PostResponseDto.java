package com.koscom.springboot.web.dto.posts;

import com.koscom.springboot.domain.posts.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long id; //pk (bigint, auto increment)
    private String title;
    private String content;
    private String author; //Column 이 없으면 varchar(255), nullable = true 가됨

    //변수는 private으로 막고 생성자의 형태로 값만 가져오도록 했으므로, 변경할 수 없도록 만들었다.
    //Setter를 정의하지 않아서 변경할 수는 없도록 했다.
    public PostResponseDto(Posts posts) {
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.content = posts.getContent();
        this.author = posts.getAuthor();
    }
}
