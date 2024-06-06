package com.poscodx.mysite.controller;

import com.poscodx.mysite.service.BoardService;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.UserVo;
import com.poscodx.mysite.webUtil.WebUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @RequestMapping("")
    public String index(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(value = "kwd", required = false) String kwd,
                       Model model) {
        Map<String, Object> map = boardService.getContentsList(page, kwd);
        model.addAllAttributes(map);
        model.addAttribute("kwd", kwd);
        return "board/index";
    }

    @RequestMapping("/view/{no}")
    public String view(@PathVariable("no") Long no,
                       @RequestParam(defaultValue = "1") int page,
                       @RequestParam(required = false, defaultValue = "") String kwd,
                       Model model) {

        BoardVo boardVo = boardService.getContents(no);
        model.addAttribute("boardVo", boardVo);
        model.addAttribute("currentPage", page);
        model.addAttribute("kwd", kwd);

        return "board/view";
    }

    @RequestMapping("/delete/{no}")
    public String delete(HttpSession session, @PathVariable("no") Long no,
                         @RequestParam(defaultValue = "1") int page,
                         @RequestParam(value = "kwd", required = false) String kwd) {
        // access control
        UserVo authUser = (UserVo) session.getAttribute("authUser");
        if(authUser == null) {
            return "redirect:/";
        }
        ////////////////////////
        boardService.deleteContents(no, authUser.getNo());
        return "redirect:/board?page=" + page + "&kwd=" + WebUtil.encodeURL(kwd, "UTF-8");
    }

    @RequestMapping("/modify/{no}")
    public String modify(HttpSession session, @PathVariable("no") Long no, Model model) {

    }

}
