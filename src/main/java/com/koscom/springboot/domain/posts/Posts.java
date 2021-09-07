package com.koscom.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor //디폴트 생성자를 만들어준다. - 개발자가 생성자를 생성하는 경우 디폴트 생성자가 안생기는 경우를 염두하여
@Entity

public class Posts {
    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK 채번 방식
    private Long id; //pk (bigint, auto increment)

    @Column(length = 500, nullable = false)
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;

    private String author; //Column 이 없으면 varchar(255), nullable = true 가됨

    //builer는 왜?
    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    //title과 content만 수정가능한다.

    public void update(String title, String content){
        this.title=title;
        this.content=content;

    }


}
