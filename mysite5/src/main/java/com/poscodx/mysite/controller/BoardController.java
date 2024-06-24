package com.poscodx.mysite.controller;

import com.poscodx.mysite.service.BoardService;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.UserVo;
import com.poscodx.mysite.webUtil.WebUtil;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        return "board/index";
    }

    @RequestMapping("/view/{no}")
    public String view(@PathVariable("no") Long no,
                       @RequestParam(defaultValue = "1") int page,
                       @RequestParam(required = false, defaultValue = "") String kwd,
                       HttpServletRequest request,
                       HttpServletResponse response,
                       Model model) {

        handleHitCount(no, request, response);

        BoardVo boardVo = boardService.getContents(no);

        model.addAttribute("boardVo", boardVo);
        model.addAttribute("currentPage", page);
        model.addAttribute("kwd", kwd);

        return "board/view";
    }

    private void handleHitCount(Long no, HttpServletRequest request, HttpServletResponse response) {
        String cookieName = "viewed_" + no;

        // 쿠키 찾기
        Cookie[] cookies = request.getCookies();
        boolean found = false;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            boardService.increaseHit(no);

            Cookie newCookie = new Cookie(cookieName, "true");
            newCookie.setMaxAge(24 * 60 * 60);  // 1일
            newCookie.setPath(request.getContextPath());
            response.addCookie(newCookie);
        }
    }

    @RequestMapping("/delete/{no}")
    public String delete(Authentication authentication,
                         @PathVariable("no") Long no,
                         @RequestParam(defaultValue = "1") int page,
                         @RequestParam(value = "kwd", required = false, defaultValue = "") String kwd) {
        UserVo authUser = (UserVo)authentication.getPrincipal();
        boardService.deleteContents(no, authUser.getNo());
        return "redirect:/board?page=" + page + "&kwd=" + WebUtil.encodeURL(kwd, "UTF-8");
    }

    @RequestMapping("/modify/{no}")
    public String modify(@PathVariable("no") Long no, Model model) {

        BoardVo boardVo = boardService.getContents(no);
        model.addAttribute("boardVo", boardVo);
        return "board/modify";
    }

    @RequestMapping(value="/modify", method= RequestMethod.POST)
    public String modify(
            Authentication authentication,
            BoardVo boardVo,
            @RequestParam(value="page", defaultValue="1") int page,
            @RequestParam(value="kwd", required=false, defaultValue="") String kwd) {

        UserVo authUser = (UserVo)authentication.getPrincipal();
        boardVo.setUserNo(authUser.getNo());
        boardService.modifyContents(boardVo);
        return "redirect:/board/view/" + boardVo.getNo() +
                "?page=" + page +
                "&kwd=" + WebUtil.encodeURL( kwd, "UTF-8" );
    }

    @RequestMapping(value="/write", method=RequestMethod.GET)
    public String write() {
        return "board/write";
    }

    @RequestMapping(value="/write", method=RequestMethod.POST)
    public String write(
            Authentication authentication,
            @ModelAttribute BoardVo boardVo,
            @RequestParam(value="page", defaultValue="1") Integer page,
            @RequestParam(value="kwd", required=false, defaultValue="") String kwd) {

        UserVo authUser = (UserVo)authentication.getPrincipal();
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
            @PathVariable("no") Long no,
            Model model) {

        BoardVo boardVo = boardService.getContents(no);
        model.addAttribute("boardVo", boardVo);

        return "board/reply";
    }

    @RequestMapping(value="/reply", method = RequestMethod.POST)
    public String reply(
            UserVo authUser,
            @ModelAttribute BoardVo boardVo,
            @RequestParam(value="page", defaultValue="1") Integer page,
            @RequestParam(value="kwd", required=false, defaultValue="") String kwd
    ) {

        boardVo.setUserNo(authUser.getNo());
        boardService.doReply(boardVo);

        String redirectUrl = "redirect:/board?page=" + page;
        if (!kwd.isEmpty()) {
            redirectUrl += "&kwd=" + WebUtil.encodeURL(kwd, "UTF-8");
        }

        return redirectUrl;

    }

}
