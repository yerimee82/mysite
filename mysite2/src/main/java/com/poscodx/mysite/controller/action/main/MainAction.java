package com.poscodx.mysite.controller.action.main;

import com.poscodx.mysite.controller.ActionServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainAction implements ActionServlet.Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request
                .getRequestDispatcher("/WEB-INF/views/main/index.jsp")
                .forward(request, response);
    }
}