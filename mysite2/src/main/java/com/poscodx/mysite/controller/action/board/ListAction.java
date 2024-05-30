package com.poscodx.mysite.controller.action.board;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int currentPage = 1; // 기본값으로 1 설정

        // 요청 파라미터인 "page"가 null이 아닌 경우에만 현재 페이지 값을 가져옴
        String pageParam = req.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            currentPage = Integer.parseInt(pageParam);
        }

        int totalPosts = new BoardDao().countTotalPosts();
        int limit = 5;
        int offset = (currentPage - 1) * limit;
        int totalPages = (int) Math.ceil((double) totalPosts / limit);

        List<BoardVo> list = new BoardDao().findByLimitAndOffset(limit, offset);

        // 페이징 정보를 계산하여 전달
        int startPage = Math.max(1, currentPage - 2);
        int endPage = Math.min(totalPages, currentPage + 2);

        req.setAttribute("list", list);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("startPage", startPage);
        req.setAttribute("endPage", endPage);

        req
                .getRequestDispatcher("/WEB-INF/views/board/list.jsp")
                .forward(req, resp);
    }
}
