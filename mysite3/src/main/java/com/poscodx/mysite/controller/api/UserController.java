package com.poscodx.mysite.controller.api;

import com.poscodx.mysite.service.UserService;
import com.poscodx.mysite.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController("userApiController")
@RequestMapping("/user/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping("/checkemail")
    public Object checkEmail(
            @RequestParam(value = "email", defaultValue = "") String email) {
        UserVo vo = userService.getUser(email);
        return Map.of("exist", vo != null);
    }
}