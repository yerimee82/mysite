package com.poscodx.mysite.controller.action.board;

import com.poscodx.mysite.controller.ActionServlet.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PagingAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int currentPage = Integer.parseInt(req.getParameter("page"));
        System.out.println(currentPage);

        resp.sendRedirect(req.getContextPath()+"/board?a=board");
    }
}
