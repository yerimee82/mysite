package com.poscodx.mysite.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Controller
public class MainController {
    @RequestMapping({"/", "/main"})
    public String index() {
        return "main/index";
    }
}
