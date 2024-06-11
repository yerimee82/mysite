package com.poscodx.mysite.controller;

import com.poscodx.mysite.service.SiteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
