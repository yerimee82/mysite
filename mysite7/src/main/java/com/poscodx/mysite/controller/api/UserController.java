package com.poscodx.mysite.controller.api;

import com.poscodx.mysite.dto.JsonResult;
import com.poscodx.mysite.service.UserService;
import com.poscodx.mysite.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController("userApiController")
@RequestMapping("/user/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping("/checkemail")
    public JsonResult checkEmail(
            @RequestParam(value = "email", defaultValue = "") String email) {
        UserVo vo = userService.getUser(email);

        return JsonResult.success(vo != null);
    }
}
