package com.poscodx.mysite.controller;

import com.poscodx.mysite.dao.UserDao;
import com.poscodx.mysite.vo.UserVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String action = req.getParameter("a");

        if("joinform".equals(action)) {
            req
                    .getRequestDispatcher("/WEB-INF/views/user/joinform.jsp")
                    .forward(req, resp);
        } else if ("join".equals(action)) {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String gender = req.getParameter("gender");

            UserVo vo = new UserVo();
            vo.setName(name);
            vo.setEmail(email);
            vo.setPassword(password);
            vo.setGender(gender);

            System.out.println(vo);
            new UserDao().insert(vo);

            resp.sendRedirect(req.getContextPath()+"/user?a=joinsuccess");

        } else if ("joinsuccess".equals(action)) {
            req
                    .getRequestDispatcher("/WEB-INF/views/user/joinsuccess.jsp")
                    .forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath());
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
