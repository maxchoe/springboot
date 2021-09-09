package com.koscom.springboot.service;

import com.koscom.springboot.domain.posts.Posts;
import com.koscom.springboot.domain.posts.PostsRepository;
import com.koscom.springboot.web.dto.PostsListResponseDto;
import com.koscom.springboot.web.dto.posts.PostResponseDto;
import com.koscom.springboot.web.dto.posts.PostsSaveRequestDto;
import com.koscom.springboot.web.dto.posts.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service // spring bean 등록 & Service 클래스 선언
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        Posts post = postsRepository.save(requestDto.toEntity());
        return post.getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto updateDto){
        //값을 가져오면서 1차 캐싱을 한다. (JPA에서)
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자는 없습니다. id =" + id));

        //Dirty Checking
        entity.update(updateDto.getTitle(), updateDto.getContent());

        return entity.getId();
    }

    //조회
    public PostResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당사용자는 없습니다. id =" + id));
        return new PostResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        // java 7까지 방식
//        List<Posts> allDesc = postsRepository.findAllDesc();
//        List<PostsListResponseDto> result = new ArrayList<>();
//
//        for (Posts posts : allDesc) {
//            result.add(new PostsListResponseDto(posts));
//        }
//
//        return result;

        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

}
