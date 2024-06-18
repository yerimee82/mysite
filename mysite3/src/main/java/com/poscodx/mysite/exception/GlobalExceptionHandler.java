package com.poscodx.mysite.exception;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.poscodx.mysite.dto.JsonResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

@Controller
public class GlobalExceptionHandler {
    private static final Log logger = LogFactory.getLog(GlobalExceptionHandler.class);
    @ExceptionHandler(Exception.class)
    public void handler(
            HttpServletRequest request,
            HttpServletResponse response,
            Exception e) throws ServletException, IOException {
        // 1. 로깅(logging)
        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));
        logger.error(errors.toString());

        // 2. 요청 구분
        // json 요청 : request header 에 application/json(o)
        // html 요청 :request header 에 application/json(X)
        String accpet = request.getHeader("accpet");

        if(accpet.matches(".*application/json.*")) {
            // 3. Json 응답
            JsonResult jsonResult = JsonResult.fail(errors.toString());
            String jsonString = new ObjectMapper().writeValueAsString(jsonResult);

            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json; charset=utf-8");
            OutputStream os = response.getOutputStream();
            os.write("{}".getBytes("utf-8"));
            os.close();
        } else {
            // 4. 사과(종료)
            request.setAttribute("error", errors.toString());
            request.getRequestDispatcher("/WEB-INF/views/errors/exception.jsp")
                            .forward(request,response);
        }
    }
}
