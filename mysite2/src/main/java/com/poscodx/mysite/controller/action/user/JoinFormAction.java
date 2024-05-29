package com.poscodx.mysite.controller.action.user;

import com.poscodx.mysite.controller.ActionServlet.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JoinFormAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req
                .getRequestDispatcher("/WEB-INF/views/user/joinform.jsp")
                .forward(req, resp);
    }
}
