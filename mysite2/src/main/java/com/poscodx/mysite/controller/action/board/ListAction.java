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

        String action = req.getParameter("a");
        if (action == null || action.isEmpty()) {
            req.getSession().removeAttribute("kwd");
            req.getSession().removeAttribute("page");
        }

        int currentPage = getCurrentPage(req);
        int limit = 5;
        int offset = (currentPage - 1) * limit;

        String keyword = req.getParameter("kwd");
        List<BoardVo> list;
        int totalPosts;

        BoardDao boardDao = new BoardDao();
        if (keyword!= null &&!keyword.isEmpty()) {
            totalPosts = boardDao.countTotalPosts(keyword);

            list = boardDao.searchWithKeywords(keyword, limit, offset);
        } else {
            totalPosts = boardDao.countTotalPosts();
            list = BoardDao.findByLimitAndOffset(limit, offset);
        }

        int totalPages = (int) Math.ceil((double) totalPosts / limit);
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
        req.setAttribute("kwd", keyword);

        req
                .getRequestDispatcher("/WEB-INF/views/board/list.jsp")
                .forward(req, resp);
    }
    private int getCurrentPage(HttpServletRequest req) {
        String pageParam = req.getParameter("page");
        if (pageParam!= null &&!pageParam.isEmpty()) {
            return Integer.parseInt(pageParam);
        }
        return 1;
    }
}