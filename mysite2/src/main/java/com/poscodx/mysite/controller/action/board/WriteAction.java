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

public class WriteAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserVo authUser = (UserVo) session.getAttribute("authUser");

        if(authUser == null) {
            resp.sendRedirect(req.getContextPath()+"/board");
            return;
        }

        String title = req.getParameter("title");
        String contents = req.getParameter("contents");
        int maxGroupNo = new BoardDao().findMaxGroupNo();
        Long userNo = authUser.getNo();

        BoardVo boardVo = setBoardVo(maxGroupNo, title, contents, userNo);

        new BoardDao().insert(boardVo);

        System.out.println(authUser.getNo() +":" + authUser.getName());

        resp.sendRedirect(req.getContextPath()+"/board?a=board");
    }

    private BoardVo setBoardVo(int maxGroupNo, String title, String contents, Long userNo) {
        BoardVo vo = new BoardVo();
        vo.setTitle(title);
        vo.setContents(contents);
        vo.setHit(0);
        vo.setDepth(1);
        vo.setgNo(maxGroupNo + 1);
        vo.setoNo(1);
        vo.setUserNo(userNo);

        return vo;
    }
}
