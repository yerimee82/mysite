package com.poscodx.mysite.controller;

import com.poscodx.mysite.security.Auth;
import com.poscodx.mysite.security.AuthUser;
import com.poscodx.mysite.service.UserService;
import com.poscodx.mysite.vo.UserVo;
import com.sun.jna.platform.win32.Netapi32Util;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String join(@ModelAttribute UserVo vo) {
        return "user/join";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String join(@ModelAttribute @Valid UserVo vo, BindingResult result, Model model) {
//        userService.join(vo);
        if(result.hasErrors()) {
//            model.addAttribute("userVo", vo);
//            List<ObjectError> errors = result.getAllErrors();
//            for (ObjectError error : errors) {
//                System.out.println(error);
//            }
            Map<String, Object> map = result.getModel();
            model.addAllAttributes(map);
            return "user/join";
        }
        return "redirect:/user/joinsuccess";
    }

    @RequestMapping(value="/joinsuccess", method=RequestMethod.GET)
    public String joinsuccess() {
        return "user/joinsuccess";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "user/login";
    }

    @Auth
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(@AuthUser UserVo authUser, Model model) {
        UserVo vo = userService.getUser(authUser.getNo());
        model.addAttribute("userVo", vo);

        return "user/update";
    }

    @Auth
    @RequestMapping(value="/update", method=RequestMethod.POST)
    public String update(@AuthUser UserVo authUser, UserVo vo) {

        vo.setNo(authUser.getNo());
        userService.update(vo);

        authUser.setName(vo.getName());
        return "redirect:/user/updatesuccess";
    }

    @RequestMapping(value="/updatesuccess", method=RequestMethod.GET)
    public String updatesuccess() {
        return "user/updatesuccess";
    }
}
