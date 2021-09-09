package com.koscom.springboot.web.dto;

import com.koscom.springboot.config.auth.dto.SessionUser;
import com.koscom.springboot.config.auth.login.LoginUser;
import com.koscom.springboot.domain.posts.Posts;
import com.koscom.springboot.domain.posts.user.User;
import com.koscom.springboot.service.PostsService;
import com.koscom.springboot.web.dto.posts.PostResponseDto;
import com.koscom.springboot.web.dto.posts.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){ //ModelAndView
        postsService.save(new PostsSaveRequestDto("test","test","test"));
        model.addAttribute("posts", postsService.findAllDesc());

        /////////////
        //SessionUser user = (SessionUser) httpSession.getAttribute("user"); // (1)
        if (user != null) { // (2)
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

}
