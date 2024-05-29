package com.poscodx.mysite.controller.action.guestbook;

import com.poscodx.mysite.controller.ActionServlet;
import com.poscodx.mysite.dao.GuestbookDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListAction implements ActionServlet.Action{

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("list", new GuestbookDao().findAll());
        req
                .getRequestDispatcher("/WEB-INF/views/guestbook/list.jsp")
                .forward(req, resp);
    }
}
