package com.poscodx.mysite.controller.action.board;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long no = Long.valueOf(req.getParameter("no"));

        BoardVo boardVo = new BoardDao().findByNo(no);

        String cookieName = "viewed_" + no;

        // 쿠키 찾기
        Cookie[] cookies = req.getCookies();
        boolean found = false;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    found = true;
                    break;
                }
            }
        }

        // 쿠키가 없을 경우 설정
        if (!found) {
            new BoardDao().updateHit(no);

            Cookie newCookie = new Cookie(cookieName, "true");
            newCookie.setMaxAge(24 * 60 * 60);  // 1일
            newCookie.setPath(req.getContextPath());
            resp.addCookie(newCookie);
        }

        req.setAttribute("title", boardVo.getTitle());
        req.setAttribute("contents", boardVo.getContents());
        req.setAttribute("no", no);
        req.setAttribute("userNo", boardVo.getUserNo());

        req
                .getRequestDispatcher("/WEB-INF/views/board/view.jsp")
                .forward(req, resp);
    }
}