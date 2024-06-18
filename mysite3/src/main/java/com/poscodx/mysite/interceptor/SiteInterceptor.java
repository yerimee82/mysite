package com.poscodx.mysite.interceptor;

import com.poscodx.mysite.service.SiteService;
import com.poscodx.mysite.vo.SiteVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class SiteInterceptor implements HandlerInterceptor {
    private final SiteService siteService;
    private final LocaleResolver localeResolver;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SiteVo siteVo = (SiteVo)request.getServletContext().getAttribute("siteVo");
        if(siteVo == null) {
            siteVo = siteService.getSite(1L);
            System.out.println(siteVo);
            request.getServletContext().setAttribute("siteVo", siteVo);
        }

        // Locale
        System.out.println("resolver-locale: " + localeResolver.resolveLocale(request).getLanguage());
        request.setAttribute("language", localeResolver.resolveLocale(request).getLanguage());

        return true;
    }
}
