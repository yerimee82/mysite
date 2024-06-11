package com.poscodx.mysite.repository;

import com.poscodx.mysite.vo.SiteVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SiteRepository {
    private final SqlSession sqlSession;

    public SiteRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public SiteVo find(Long no) {
        return sqlSession.selectOne("site.find-site", no);
    }

    public void update(SiteVo vo) {
        sqlSession.update("site.update-site", vo);
    }
}
