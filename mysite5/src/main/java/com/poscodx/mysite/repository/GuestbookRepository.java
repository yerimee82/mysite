package com.poscodx.mysite.repository;

import com.poscodx.mysite.vo.GuestbookVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Map;

@Repository
public class GuestbookRepository {
    private final SqlSession sqlSession;
    @Autowired
    public GuestbookRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public int deleteByNoAndPassword(Long no, String password) {
        return sqlSession.delete("guestbook.deleteByNoAndPassword", Map.of("no", no, "password", password));
    }

    public int insert(GuestbookVo vo) {
        return sqlSession.insert("guestbook.insert", vo);
    }

    public List<GuestbookVo> findAll() {

        return sqlSession.selectList("guestbook.findAll");
    }
}
