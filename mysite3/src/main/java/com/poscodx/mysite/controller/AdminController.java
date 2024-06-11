package com.poscodx.mysite.controller;

import com.poscodx.mysite.security.Auth;
import com.poscodx.mysite.vo.SiteVo;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;

@Controller
@Auth(role = "ADMIN")
@RequestMapping("/admin")
public class AdminController {

    private final ServletContext servletContext;
    private final ApplicationContext applicationContext;

    public AdminController(ServletContext servletContext, ApplicationContext applicationContext) {
        this.servletContext = servletContext;
        this.applicationContext = applicationContext;
    }

    @RequestMapping("")
    public String main() {
        return "admin/main";
    }

    @RequestMapping("/main/update")
    public String update(SiteVo siteVo) {
        return "redirect:/admin";
    }

    @RequestMapping("/guestbook")
    public String guestbook() {
        return "admin/guestbook";
    }

    @RequestMapping("/board")
    public String board() {
        return "admin/board";
    }

    @RequestMapping("/user")
    public String user() {
        return "admin/user";
    }
}
