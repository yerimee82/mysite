package com.poscodx.mysite.controller.action.board;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ModifyAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long no = Long.valueOf(req.getParameter("no"));
        String title = req.getParameter("title");
        String contents = req.getParameter("contents");

        new BoardDao().modifyPost(Long.valueOf(no), title, contents);

        resp.sendRedirect(req.getContextPath() + "/board?a=view&no=" + no);
    }
}
