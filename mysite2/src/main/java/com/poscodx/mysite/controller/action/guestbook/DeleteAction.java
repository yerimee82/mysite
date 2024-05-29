package com.poscodx.mysite.controller.action.guestbook;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.dao.GuestbookDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String no = req.getParameter("no");
        String password = req.getParameter("password");
        String message = getMessageAfterDeletionAttempt(no, password);

        if (!"".equals(message)) {
            req.setAttribute("message", message);
            new DeleteFormAction().execute(req, resp);
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/guestbook");
    }

    private String getMessageAfterDeletionAttempt(String no, String password) {
        boolean isDeleted = new GuestbookDao().deleteByNoAndPassword(Long.parseLong(no), password);

        String message = "";
        if (!isDeleted) {
            message = "비밀번호가 틀렸습니다.";
        }
        return message;
    }
}
