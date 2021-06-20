package com.lsj8367.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
    //@Target(ElementType.PARAMETER)
    //이 어노테이션이 생성될 수 있는 위치를 지정
    //파라미터로 지정했으니 메소드의 파라미터로 선언된 객체에서만 사용이 가능
    // 이외에 클래스 선언문에 쓸수 있는 타입등이 있다.

    //@interface
    //이파일을 어노테이션 클래스로 지정
    //LoginUser라는 이름을 가진 어노테이션이 생성되었다
}
