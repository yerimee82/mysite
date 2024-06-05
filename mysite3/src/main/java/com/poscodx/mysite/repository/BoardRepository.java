package com.poscodx.mysite.repository;

import com.poscodx.mysite.vo.BoardVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
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
        return sqlSession.selectList("board.findByLimitAndOffset", Map.of("limit", limit, "offset", offset));
    }

    public List<BoardVo> searchWithKeywords(String keyword, int limit, int offset) {
        return sqlSession.selectList("searchWithKeywords", Map.of(
                "keyword", "%" + keyword + "%",
                "limit", limit,
                "offset", offset
        ));
    }

    public int countTotalPosts() {
        return sqlSession.selectOne("board.countTotalPosts");
    }

    public int countTotalPosts(String keyword) {
        return sqlSession.selectOne("board.countTotalPostsWithKeyword", keyword);
    }

    public BoardVo findByNo(Long no) {
        return sqlSession.selectOne("board.findByNo", no);
    }

    public int updateHit(Long no) {
        return sqlSession.update("board.updateHit", no);
    }

    public int modifyPost(Long no, String title, String contents) {
        return sqlSession.update("board.modifyPost", Map.of("no", no, "title", title, "contents", contents));
    }

    public int deleteByNo(Long no) {
        return sqlSession.delete("board.deleteByNo", no);
    }

    public int adjustOrderNo(int gNo, int oNo) {
        return sqlSession.update("boardMapper.adjustOrderNo", Map.of("gNo", gNo, "oNo", oNo));
    }
}
