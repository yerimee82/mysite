package com.poscodx.mysite.controller;

import com.poscodx.mysite.service.UserService;
import com.poscodx.mysite.vo.UserVo;
import com.sun.jna.platform.win32.Netapi32Util;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String join() {
        return "user/join";
    }
    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String join(UserVo vo) {
        userService.join(vo);
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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, UserVo vo, Model model) {
        UserVo authUser = userService.getUser(vo.getEmail(), vo.getPassword());
        if(authUser == null) {
            model.addAttribute("email", vo.getEmail());
            model.addAttribute("result", "fail");

            return "user/login";
        }

        // login 처리
        session.setAttribute("authUser", authUser);

        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("authUser");
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(HttpSession session, Model model) {
        UserVo authUser = (UserVo) session.getAttribute("authUser");
        if(authUser == null) {
            return "redirect:/";
        }
        UserVo vo = userService.getUser(authUser.getNo());
        model.addAttribute("userVo", vo);

        return "user/update";
    }

    @RequestMapping(value="/update", method=RequestMethod.POST)
    public String update(HttpSession session, UserVo vo) {
        // access control
        UserVo authUser = (UserVo)session.getAttribute("authUser");
        if(authUser == null) {
            return "redirect:/";
        }

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
