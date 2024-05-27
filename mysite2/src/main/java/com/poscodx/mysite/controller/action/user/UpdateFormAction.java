package com.poscodx.mysite.controller.action.user;

import com.poscodx.mysite.controller.ActionServlet;
import com.poscodx.mysite.dao.UserDao;
import com.poscodx.mysite.vo.UserVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateFormAction implements ActionServlet.Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // Access Control
        if(session == null) {
            resp.sendRedirect(req.getContextPath());
            return;
        }
        UserVo authUser = (UserVo) session.getAttribute("authUser");
        if(authUser == null) {
            resp.sendRedirect(req.getContextPath());
            return;
        }
        ////////////////////////////////////////////////////////////////
        UserVo userVo = new UserDao().findByNo(authUser.getNo());
        req.setAttribute("userVo", userVo);
        req
                .getRequestDispatcher("/WEB-INF/views/user/updateform.jsp")
                .forward(req, resp);
    }
}
