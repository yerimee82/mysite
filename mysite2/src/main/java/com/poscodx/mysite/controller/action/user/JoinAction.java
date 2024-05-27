package com.poscodx.mysite.controller.action.user;

import com.poscodx.mysite.controller.ActionServlet;
import com.poscodx.mysite.dao.UserDao;
import com.poscodx.mysite.vo.UserVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JoinAction implements ActionServlet.Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
    }
}
