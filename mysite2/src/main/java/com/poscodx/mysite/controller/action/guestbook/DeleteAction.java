package com.poscodx.mysite.controller.action.guestbook;

import com.poscodx.mysite.controller.ActionServlet;
import com.poscodx.mysite.dao.GuestbookDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteAction implements ActionServlet.Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String no = req.getParameter("no");
        String password = req.getParameter("password");
        new GuestbookDao().deleteByNoAndPassword(Long.parseLong(no), password);

        resp.sendRedirect(req.getContextPath() + "/guestbook");
    }
}
