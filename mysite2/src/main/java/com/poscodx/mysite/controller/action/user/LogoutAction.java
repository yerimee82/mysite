package com.poscodx.mysite.controller.action.user;

import com.poscodx.mysite.controller.ActionServlet.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session == null) {
            // 로그아웃 처리
            session.removeAttribute("authUser");
            session.invalidate();
        }

        // redirect to main
        resp.sendRedirect(req.getContextPath());

    }
}
