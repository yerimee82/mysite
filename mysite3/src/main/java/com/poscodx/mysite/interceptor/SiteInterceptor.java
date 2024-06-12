package com.poscodx.mysite.interceptor;

import com.poscodx.mysite.service.SiteService;
import com.poscodx.mysite.vo.SiteVo;
import org.springframework.beans.factory.annotation.Autowired;
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
        SiteVo siteVo = (SiteVo)request.getServletContext().getAttribute("siteVo");
        if(siteVo == null) {
            siteVo = siteService.getSite(1L);
            System.out.println(siteVo);
            request.getServletContext().setAttribute("siteVo", siteVo);
        }
        return true;
    }
}
