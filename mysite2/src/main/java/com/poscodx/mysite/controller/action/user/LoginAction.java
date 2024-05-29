package com.poscodx.mysite.controller.action.user;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.dao.UserDao;
import com.poscodx.mysite.vo.UserVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserVo authUser = new UserDao().findByNoAndPassword(email, password);
        if(authUser == null) {
            req.setAttribute("email", email);
            req.setAttribute("result", "fail");
            req.getRequestDispatcher("/WEB-INF/views/user/loginform.jsp").forward(req,resp);
            return;
        }
        // login 처리

        HttpSession session = req.getSession();
        session.setAttribute("authUser", authUser);

        // redirect to main
        resp.sendRedirect(req.getContextPath());

    }
}
