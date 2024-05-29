package com.poscodx.mysite.controller;

import com.poscodx.mysite.controller.action.main.MainAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends ActionServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        String config = getServletConfig().getInitParameter("config");
        System.out.println("MainController.init() called:" + config);

        super.init();
    }

    @Override
    protected Action getAction(String actionName) {
        return new MainAction();
    }
}

