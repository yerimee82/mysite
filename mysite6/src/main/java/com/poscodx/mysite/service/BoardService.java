package com.poscodx.mysite.service;

import com.poscodx.mysite.repository.BoardRepository;
import com.poscodx.mysite.vo.BoardVo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void doFirstWrite(BoardVo vo) {
        int maxGroupNo = boardRepository.findMaxGroupNo();
        vo.setgNo(maxGroupNo + 1);
        vo.setoNo(1);
        vo.setDepth(1);
        vo.setHit(0);

        addContents(vo);
    }

    public void doReply(BoardVo vo) {
        boardRepository.adjustOrderNo(vo.getgNo(), vo.getoNo());
        vo.setoNo(vo.getoNo() + 1);
        vo.setDepth(vo.getDepth() + 1);
        vo.setHit(0);

        addContents(vo);
    }

    public void addContents(BoardVo vo) {
        boardRepository.insert(vo);
    }

    public BoardVo getContents(Long no) {
        return boardRepository.findByNo(no);
    }

    public void modifyContents(BoardVo vo) {
        boardRepository.modifyPost(vo.getNo(), vo.getTitle(), vo.getContents());
    }

    public void deleteContents(Long boardNo, Long userNo) {
        boardRepository.deleteByNo(boardNo, userNo);
    }

    public void increaseHit(Long no) {
        boardRepository.updateHit(no);
    }

    public Map<String, Object> getContentsList(int currentPage, String keyword) {
        int limit = 5;
        int offset = (currentPage - 1) * limit;
        List<BoardVo> list = null;
        int totalPosts;

        if (keyword != null && !keyword.isEmpty()) {
            totalPosts = boardRepository.countTotalPosts(keyword);
            list = boardRepository.searchWithKeywords(keyword, limit, offset);
        } else {
            totalPosts = boardRepository.countTotalPosts();
            list = boardRepository.findByLimitAndOffset(limit, offset);
        }

        int totalPages = (int) Math.ceil((double) totalPosts / limit);
        int maxPage = 5;
        int startPage = Math.max(1, currentPage - maxPage / 2);
        int endPage = Math.min(totalPages, startPage + maxPage - 1);

        if (endPage - startPage < maxPage - 1) {
            startPage = Math.max(1, endPage - maxPage + 1);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("totalPosts", totalPosts);
        map.put("totalPages", totalPages);
        map.put("currentPage", currentPage);
        map.put("startPage", startPage);
        map.put("endPage", endPage);
        map.put("kwd", keyword);

        return map;
    }


}
