package com.lsj8367.book.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index"; //머스테치 스타터를 gradle에 추가했기 때문에 앞의 경로와 뒤의파일 확장자는 자동으로 지정된다.
        //앞 경로 : src/main/resources/templates
        // 파일 확장자 : .mustache
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }
}
