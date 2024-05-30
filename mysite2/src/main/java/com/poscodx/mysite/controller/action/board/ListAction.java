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
        int currentPage = 1;

        String pageParam = req.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            currentPage = Integer.parseInt(pageParam);
        }

        int totalPosts = new BoardDao().countTotalPosts();
        System.out.println(totalPosts);
        int limit = 5;
        int offset = (currentPage - 1) * limit;
        int totalPages = (int) Math.ceil((double) totalPosts / limit);

        List<BoardVo> list = new BoardDao().findByLimitAndOffset(limit, offset);

        int maxPage = 5;
        int startPage = Math.max(1, currentPage - maxPage / 2);
        int endPage = Math.min(totalPages, startPage + maxPage - 1);


        if (endPage - startPage < maxPage - 1) {
            startPage = Math.max(1, endPage - maxPage + 1);
        }

        req.setAttribute("list", list);
        req.setAttribute("totalPosts", totalPosts);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("startPage", startPage);
        req.setAttribute("endPage", endPage);

        req
                .getRequestDispatcher("/WEB-INF/views/board/list.jsp")
                .forward(req, resp);
    }
}
