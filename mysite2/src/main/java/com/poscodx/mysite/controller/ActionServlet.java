package com.poscodx.mysite.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public abstract class ActionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected abstract Action getAction(String actionName);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String actionName = Optional.ofNullable(req.getParameter("a")).orElse("");

        Action action = getAction(actionName);
        if(action == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        action.execute(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public static interface Action {
        void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    }
}
