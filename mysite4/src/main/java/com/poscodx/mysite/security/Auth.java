package com.poscodx.mysite.security;

import com.poscodx.mysite.vo.BoardVo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Auth {
//    public String value() default  "test";
    public String role() default  "USER";   // value 가 아닌 다른 값의 이름은 생략 불가능
    public boolean test() default  true;

}
