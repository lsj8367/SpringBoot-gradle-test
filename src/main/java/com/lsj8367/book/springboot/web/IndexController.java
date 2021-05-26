package com.lsj8367.book.springboot.web;

import com.lsj8367.book.springboot.service.posts.PostsService;
import com.lsj8367.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts", postsService.findAllDesc()); //index.mustache의 {{#posts}}의 posts에 해당하는 데이터
        return "index"; //머스테치 스타터를 gradle에 추가했기 때문에 앞의 경로와 뒤의파일 확장자는 자동으로 지정된다.
        //앞 경로 : src/main/resources/templates
        // 파일 확장자 : .mustache
    }

    @GetMapping("/posts/save") //등록
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}") //수정
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }

}
