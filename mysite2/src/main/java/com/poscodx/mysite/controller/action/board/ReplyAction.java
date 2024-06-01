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

public class ReplyAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserVo authUser = (UserVo) session.getAttribute("authUser");

        int gNo = Integer.parseInt(req.getParameter("gNo"));
        int oNo = Integer.parseInt(req.getParameter("oNo"));
        int depth = Integer.parseInt(req.getParameter("depth"));

        String title = req.getParameter("title");
        String contents = req.getParameter("contents");

        new BoardDao().adjustOrderNo(gNo, oNo);
        oNo += 1;
        depth += 1;

        BoardVo vo = new BoardVo();
        vo.setTitle(title);
        vo.setContents(contents);
        vo.setHit(0);
        vo.setgNo(gNo);
        vo.setoNo(oNo);
        vo.setDepth(depth);
        vo.setUserNo(authUser.getNo());

        new BoardDao().insertReply(vo);

        resp.sendRedirect(req.getContextPath()+"/board?a=board");

    }
}
