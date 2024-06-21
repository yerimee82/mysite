package com.poscodx.mysite.controller;

import com.poscodx.mysite.security.Auth;
import com.poscodx.mysite.service.FileUploadService;
import com.poscodx.mysite.service.SiteService;
import com.poscodx.mysite.vo.SiteVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;

@Controller
@Auth(role = "ADMIN")
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ServletContext servletContext;
    private final ApplicationContext applicationContext;
    private final SiteService siteService;
    private final FileUploadService fileUploadService;

    @RequestMapping("")
    public String main(Model model) {
        SiteVo siteVo = siteService.getSite(1L);
        model.addAttribute("siteVo", siteVo);
        return "admin/main";
    }

    @RequestMapping("/main/update")
    public String update(@ModelAttribute SiteVo siteVo, MultipartFile file) {
        String profile = fileUploadService.restore(file);
        if (profile!= null) {
            siteVo.setProfile(profile);
        }

        siteService.updateSite(siteVo);
        servletContext.setAttribute("siteVo", siteVo);
        
        SiteVo site = applicationContext.getBean(SiteVo.class);
//        site.setTitle(siteVo.getTitle());
//        site.setWelcome(siteVo.getWelcome());
//        site.setProfile(siteVo.getProfile());
//        site.setDescription(siteVo.getDescription());
        BeanUtils.copyProperties(siteVo, site);

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
