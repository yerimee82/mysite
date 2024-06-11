package com.poscodx.mysite.interceptor;

import com.poscodx.mysite.service.SiteService;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SiteInterceptor implements HandlerInterceptor {
    private final SiteService siteService;

    public SiteInterceptor(SiteService siteService) {
        this.siteService = siteService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        return true;
    }
}
