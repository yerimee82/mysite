package com.poscodx.mysite.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BoardService {
    public void addContents(BoardVo vo) {
        if(vo.getGroupNo() != null) {
            boardRepository.updateOrderNo()
        }

        boardRepository.insert(vo);

    }

    public BoardVo getContents(Long no) {
        BoardVo vo = boardRepository.findByNo(no);
        if(vo != null) {
            boardRepository.updateHit();
        }
    }

    public BoardVo getContents(Long boardNo, Long userNo) { // 수정

    }

    public void updateContents(BoardVo vo) {

    }

    public void deleteContents(Long boardNo, Long userNo) {

    }

    public Map<String, Object> getContentsList(int currentPage, String keyword) {
        List<BoardVo> list = null;
        Map<String, Object> map = null;

        map.put("list", list);
        map.put("keyword", 0);
        map.put("totalCount", 0);
        map.put("listSize", 0);
        map.put("currentPage", 0);
        map.put("beginPage", 0);
        map.put("endPage", 0);
        map.put("prevPage", 0);
        map.put("nextPage", 0);


        return map;
    }


}
