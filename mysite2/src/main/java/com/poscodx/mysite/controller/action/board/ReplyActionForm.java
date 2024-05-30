package com.poscodx.mysite.controller.action.board;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.UserVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ReplyActionForm implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long no = Long.valueOf(req.getParameter("no"));

        HttpSession session = req.getSession();
        UserVo authUser = (UserVo) session.getAttribute("authUser");

        if(authUser == null) {
            resp.sendRedirect(req.getContextPath()+"/board");
            return;
        }
        BoardVo vo = new BoardDao().findByNo(no);

        req.setAttribute("vo", vo);

        req
                .getRequestDispatcher("/WEB-INF/views/board/reply.jsp")
                .forward(req, resp);

    }
}
