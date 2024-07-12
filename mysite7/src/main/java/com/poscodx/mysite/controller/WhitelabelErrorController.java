package com.poscodx.mysite.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class WhitelabelErrorController implements ErrorController {
    @RequestMapping("/404")
    public String _404() {
        return "errors/404";
    }
    @RequestMapping("/500")
    public String _500() {
        return "errors/500";
    }

    @RequestMapping("")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if(status != null) {
            int statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "errors/404";
            } else if(statusCode == HttpStatus.BAD_REQUEST.value()) {
                return "errors/400";
            } else if(statusCode == HttpStatus.FORBIDDEN.value()) {
                return "errors/403";
            } else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "errors/500";
            }
        }

        return "errors/unknown";
    }

}