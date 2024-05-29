package com.poscodx.mysite.controller;

import com.poscodx.mysite.controller.action.guestbook.AddAction;
import com.poscodx.mysite.controller.action.guestbook.DeleteAction;
import com.poscodx.mysite.controller.action.guestbook.DeleteFormAction;
import com.poscodx.mysite.controller.action.guestbook.ListAction;
import com.poscodx.mysite.dao.GuestbookDao;
import com.poscodx.mysite.vo.GuestbookVo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class GuestbookServlet extends ActionServlet {
    private static final long serialVersionUID = 1L;

    private Map<String, Action> mapAction = Map.of(
            "deleteform", new DeleteFormAction(),
            "add", new AddAction(),
            "delete", new DeleteAction()
    );

    @Override
    protected Action getAction(String actionName) {
        return mapAction.getOrDefault(actionName, new ListAction());
    }
}