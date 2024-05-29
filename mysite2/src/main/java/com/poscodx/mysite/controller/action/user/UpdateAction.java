package com.poscodx.mysite.controller.action.user;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.dao.UserDao;
import com.poscodx.mysite.vo.UserVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateAction implements Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // Access Control
        if(session == null) {
            resp.sendRedirect(req.getContextPath());
            return;
        }
        UserVo authUser = (UserVo)session.getAttribute("authUser");
        if(authUser == null) {
            resp.sendRedirect(req.getContextPath());
            return;
        }
        ///////////////////////////////////////////////////////////

        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String gender = req.getParameter("gender");

        UserVo vo = new UserVo();
        vo.setNo(authUser.getNo());
        vo.setName(name);
        vo.setGender(gender);

        int updatedRows = updateUserInfo(vo,password);

        if(updatedRows > 0) {
            updateAuthUser(authUser, name, gender);
            session.setAttribute("authUser", authUser);
        }

        resp.sendRedirect(req.getContextPath()+"/user?a=updatesuccess");
    }
    private int updateUserInfo(UserVo vo, String password) {
        if("".equals(password)) {
            return new UserDao().update(vo);
        }
        vo.setPassword(password);
        return new UserDao().updateWithPassword(vo);
    }

    private void updateAuthUser(UserVo authUser, String name, String gender) {
        authUser.setName(name);
        authUser.setGender(gender);
    }

}
