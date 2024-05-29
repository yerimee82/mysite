package com.poscodx.mysite.web;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import java.io.IOException;

public class EncodingFilter extends HttpFilter implements Filter {
    private String encoding;

    @Override
    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("encoding");
        if(encoding == null) {
            encoding = "utf-8";
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        /* request */
        req.setCharacterEncoding(encoding);

        /* response 처리 */
        res.setContentType("text/html; charset=" + encoding);
        res.setCharacterEncoding(encoding);

        chain.doFilter(req, res);
    }
}
