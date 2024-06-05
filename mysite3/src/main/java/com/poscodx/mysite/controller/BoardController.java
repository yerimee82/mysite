package com.poscodx.mysite.controller;

import com.poscodx.mysite.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {
    @RequestMapping("/view/{no}")
    public String index(Model model) {
        Map map = boardService.getContentsList();

        model.addAllAttributes(map);

        return "board/index";
    }
    @RequestMapping("/view/{no}")
    public String view(@PathVariable("no") Long no) {

    }

    @RequestMapping("/view/{no}")
    public String delete(HttpSession session, @PathVariable("no") Long no) {
        // access control
        UserVo authUser = (UserVo) session.getAttribute("authUser");
        if(authUser == null) {
            return "redirect:/";
        }
        ////////////////////////
    }

}
