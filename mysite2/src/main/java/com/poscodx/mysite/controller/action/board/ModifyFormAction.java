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
import java.util.Objects;

public class ModifyFormAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long no = Long.valueOf(req.getParameter("no"));
        BoardVo boardVo = new BoardDao().findByNo(no);

        HttpSession session = req.getSession();
        UserVo authUser = (UserVo) session.getAttribute("authUser");

        if(authUser == null) {
            resp.sendRedirect(req.getContextPath()+"/board");
            return;
        }

        Long userNo = authUser.getNo();
        Long boardVoUserNo = boardVo.getUserNo();

        System.out.println("boardVoUserNo = " + boardVoUserNo + "userNo = " + userNo);

        if(!Objects.equals(userNo, boardVoUserNo)) {
            resp.sendRedirect(req.getContextPath()+"/board");
            return;
        }

        req.setAttribute("vo", boardVo);

        req
                .getRequestDispatcher("/WEB-INF/views/board/modify.jsp")
                .forward(req, resp);


    }
}
