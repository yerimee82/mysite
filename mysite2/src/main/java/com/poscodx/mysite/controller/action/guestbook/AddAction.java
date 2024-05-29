package com.poscodx.mysite.controller.action.guestbook;

import com.poscodx.mysite.controller.ActionServlet;
import com.poscodx.mysite.dao.GuestbookDao;
import com.poscodx.mysite.vo.GuestbookVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddAction implements ActionServlet.Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String contents = req.getParameter("contents");

        GuestbookVo vo = new GuestbookVo();
        vo.setName(name);
        vo.setPassword(password);
        vo.setContents(contents);

        new GuestbookDao().insert(vo);
        resp.sendRedirect(req.getContextPath() + "/guestbook");
    }
}
