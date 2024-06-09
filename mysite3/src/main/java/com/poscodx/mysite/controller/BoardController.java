package com.poscodx.mysite.controller;

import com.poscodx.mysite.service.BoardService;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.UserVo;
import com.poscodx.mysite.webUtil.WebUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
                       @RequestParam(value = "kwd", required = false, defaultValue = "") String kwd,
                       Model model) {
        Map<String, Object> map = boardService.getContentsList(page, kwd);
        model.addAllAttributes(map);
        model.addAttribute("totalPosts", map.get("totalPosts"));
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
                         @RequestParam(value = "kwd", required = false, defaultValue = "") String kwd) {
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
        // access control
        UserVo authUser = (UserVo)session.getAttribute("authUser");
        if(authUser == null) {
            return "redirect:/";
        }
        ////////////////////////

        BoardVo boardVo = boardService.getContents(no);
        model.addAttribute("boardVo", boardVo);
        return "board/modify";
    }

    @RequestMapping(value="/modify", method= RequestMethod.POST)
    public String modify(
            HttpSession session,
            BoardVo boardVo,
            @RequestParam(value="page", defaultValue="1") int page,
            @RequestParam(value="kwd", required=false, defaultValue="") String kwd) {
        // access control
        UserVo authUser = (UserVo)session.getAttribute("authUser");
        if(authUser == null) {
            return "redirect:/";
        }
        ////////////////////////

        boardVo.setUserNo(authUser.getNo());
        boardService.modifyContents(boardVo);
        return "redirect:/board/view/" + boardVo.getNo() +
                "?page=" + page +
                "&kwd=" + WebUtil.encodeURL( kwd, "UTF-8" );
    }

    @RequestMapping(value="/write", method=RequestMethod.GET)
    public String write(HttpSession session) {
        // access control
        UserVo authUser = (UserVo)session.getAttribute("authUser");
        if(authUser == null) {
            return "redirect:/";
        }
        ////////////////////////
        return "board/write";
    }

    @RequestMapping(value="/write", method=RequestMethod.POST)
    public String write(
            HttpSession session,
            @ModelAttribute BoardVo boardVo,
            @RequestParam(value="page", defaultValue="1") Integer page,
            @RequestParam(value="kwd", required=false, defaultValue="") String kwd) {
        // access control
        UserVo authUser = (UserVo)session.getAttribute("authUser");
        if(authUser == null) {
            return "redirect:/";
        }
        ////////////////////////

        boardVo.setUserNo(authUser.getNo());
        boardService.doFirstWrite(boardVo);

        String redirectUrl = "redirect:/board?page=" + page;
        if (!kwd.isEmpty()) {
            redirectUrl += "&kwd=" + WebUtil.encodeURL(kwd, "UTF-8");
        }

        return redirectUrl;
    }

    @RequestMapping(value="/reply/{no}")
    public String reply(
            HttpSession session,
            @PathVariable("no") Long no,
            Model model) {
        // access control
        UserVo authUser = (UserVo)session.getAttribute("authUser");
        if(authUser == null) {
            return "redirect:/";
        }
        ////////////////////////

        BoardVo boardVo = boardService.getContents(no);
        model.addAttribute("boardVo", boardVo);

        return "board/reply";
    }

    @RequestMapping(value="/reply", method = RequestMethod.POST)
    public String reply(
            HttpSession session,
            @ModelAttribute BoardVo boardVo,
            @RequestParam(value="page", defaultValue="1") Integer page,
            @RequestParam(value="kwd", required=false, defaultValue="") String kwd
    ) {
        // access control
        UserVo authUser = (UserVo)session.getAttribute("authUser");
        if(authUser == null) {
            return "redirect:/";
        }
        ////////////////////////
        boardVo.setUserNo(authUser.getNo());
        boardService.doReply(boardVo);

        String redirectUrl = "redirect:/board?page=" + page;
        if (!kwd.isEmpty()) {
            redirectUrl += "&kwd=" + WebUtil.encodeURL(kwd, "UTF-8");
        }

        return redirectUrl;

    }

}
