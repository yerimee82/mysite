package com.poscodx.mysite.controller;

import com.poscodx.mysite.controller.action.main.MainAction;
import com.poscodx.mysite.controller.action.user.JoinAction;
import com.poscodx.mysite.controller.action.user.JoinFormAction;
import com.poscodx.mysite.controller.action.user.JoinSuccess;
import com.poscodx.mysite.controller.action.user.LoginFormAction;
import com.sun.tools.javac.Main;

import java.util.Map;

public class UserServlet extends ActionServlet {
    private Map<String, Action> mapAction = Map.of(
            "joinform", new JoinFormAction(),
            "join", new JoinAction(),
            "joinsuccess", new JoinSuccess(),
            "loginform", new LoginFormAction()
    );
    @Override
    protected Action getAction(String actionName) {
        return mapAction.getOrDefault(actionName, new MainAction());
    }
}
