package com.poscodx.mysite.controller;



import com.poscodx.mysite.controller.action.board.ListAction;
import com.poscodx.mysite.controller.action.board.ViewAction;
import com.poscodx.mysite.controller.action.board.WriteAction;
import com.poscodx.mysite.controller.action.board.WriteFormAction;

import java.util.Map;

public class BoardServlet extends ActionServlet{

    private static final long serialVersionUID = 1L;

    private Map<String, Action> mapAction = Map.of(
            "view", new ViewAction(),
            "writeform", new WriteFormAction(),
            "write", new WriteAction()

    );

    @Override
    protected Action getAction(String actionName) {
        return mapAction.getOrDefault(actionName, new ListAction());
    }
}
