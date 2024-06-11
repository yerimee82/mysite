package com.poscodx.mysite.security;

import com.poscodx.mysite.vo.UserVo;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        // 1. handler 종류 확인
        if(!(handler instanceof HandlerMethod)) {
            // DefaultServletHandler 가 처리하는 경우(정적자원, /assets/**, mapping 이 안되어 있는 URL)
            return true;
        }

        // 2. casting
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        // 3. Handler Method의 @Auth 가져오기
        Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

        // 4. Handler Method 에 @Auth가 없는 경우
        if(auth == null) {
            return true;
        }

        // 5. @Auth가 붙어 있기 때문에 인증(Authentication) 확인
        HttpSession session = request.getSession();
        UserVo authUser = (UserVo) session.getAttribute("authUser");


        if(authUser == null) {
            response.sendRedirect(request.getContextPath() + "/user/login");
            return false;
        }

        // 6. 인증이 안되어 있는 경우
        return true;
    }

}
