package com.poscodx.mysite.controller;

import com.poscodx.mysite.service.GuestbookService;
import com.poscodx.mysite.vo.GuestbookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
    private final GuestbookService guestbookService;

    @Autowired
    public GuestbookController(GuestbookService guestbookService) {
        this.guestbookService = guestbookService;
    }

    @RequestMapping("")
    public String index(Model model) {
        List<GuestbookVo> contentsList = guestbookService.getContentsList();
        model.addAttribute("list", contentsList);
        return "guestbook/index";
    }

    @RequestMapping("/add")
    public String index(GuestbookVo vo) {
        guestbookService.addContents(vo);
        return "redirect:/guestbook";
    }

    @RequestMapping(value = "/delete/{no2}", method = RequestMethod.GET)
    public String delete(@PathVariable("no2") Long no, Model model) {
//        model.addAttribute("no", no);
        return "guestbook/delete";
    }

    @RequestMapping(value = "/delete/{no}", method = RequestMethod.POST)
    public String delete(@PathVariable("no") Long no, @RequestParam(value="password", required=true, defaultValue="") String password) {
        guestbookService.deleteContents(no, password);
        return "redirect:/guestbook";
    }
}
