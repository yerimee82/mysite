package com.poscodx.mysite.controller;

import com.poscodx.mysite.service.SiteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    private final SiteService siteService;

    public MainController(SiteService siteService) {
        this.siteService = siteService;
    }

    @RequestMapping({"/", "/main"})
    public String index() {
        return "main/index";
    }

    @ResponseBody
    @RequestMapping("/msg01")
    public String message01() {
        return "Hello World";
    }
}
