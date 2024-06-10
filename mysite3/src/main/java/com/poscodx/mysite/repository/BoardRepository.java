package com.poscodx.mysite.repository;

import com.poscodx.mysite.vo.BoardVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardRepository {
    private final SqlSession sqlSession;
    @Autowired
    public BoardRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public int insert(BoardVo vo) {
        return sqlSession.insert("board.insert", vo);
    }

    public int findMaxGroupNo() {
        return sqlSession.selectOne("board.findMaxGroupNo");
    }

    public List<BoardVo> findByLimitAndOffset(int limit, int offset) {
        Map<String, Object> params = new HashMap<>();
        params.put("limit", limit);
        params.put("offset", offset);
        return sqlSession.selectList("board.findByLimitAndOffset", params);
    }

    public List<BoardVo> searchWithKeywords(String keyword, int limit, int offset) {
        Map<String, Object> params = new HashMap<>();
        params.put("keyword","%" + keyword + "%");
        params.put("limit",limit);
        params.put("offset",offset);

        return sqlSession.selectList("board.searchWithKeywords", params);
    }

    public int countTotalPosts() {
        return sqlSession.selectOne("board.countTotalPosts");
    }

    public int countTotalPosts(String keyword) {
        return sqlSession.selectOne("board.countTotalPostsWithKeyword", Map.of("keyword", "%" + keyword+"%"));
    }

    public BoardVo findByNo(Long no) {
        return sqlSession.selectOne("board.findByNo", no);
    }

    public int updateHit(Long no) {
        return sqlSession.update("board.updateHit", no);
    }

    public int modifyPost(Long no, String title, String contents) {
        Map<String, Object> params = new HashMap<>();
        params.put("no", no);
        params.put("title", title);
        params.put("contents", contents);
        return sqlSession.update("board.modifyPost", params);
    }

    public int deleteByNo(Long no, Long userNo) {
        Map<String, Object> params = new HashMap<>();
        params.put("no", no);
        params.put("userNo", userNo);
        return sqlSession.delete("board.deleteByNo", params);
    }

    public int adjustOrderNo(int gNo, int oNo) {
        Map<String, Object> params = new HashMap<>();
        params.put("gNo", gNo);
        params.put("oNo", oNo);
        return sqlSession.update("board.adjustOrderNo", params);
    }
}
