package com.lsj8367.book.springboot.config.auth.dto;

import com.lsj8367.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        // 인증된 사용자 정보만 필요하므로 세개만 필드로 선언함
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
