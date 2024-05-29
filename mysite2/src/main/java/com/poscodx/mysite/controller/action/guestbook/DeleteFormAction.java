package com.poscodx.mysite.controller.action.guestbook;

import com.poscodx.mysite.controller.ActionServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteFormAction implements ActionServlet.Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req
                .getRequestDispatcher("/WEB-INF/views/guestbook/deleteform.jsp")
                .forward(req, resp);
    }
}
