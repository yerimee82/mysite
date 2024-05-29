package com.poscodx.mysite.dao;

import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.GuestbookVo;
import com.poscodx.mysite.vo.UserVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {
    public int insert(BoardVo vo) {
        int result = 0;
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt1 = conn.prepareStatement("insert into board(title, contents, reg_date, hit, g_no, o_no, depth, user_no) values(?, ?, now(), ?, ?, ?, ?, ?)");
                PreparedStatement pstmt2 = conn.prepareStatement("select last_insert_id() from dual");
        ) {

            pstmt1.setString(1, vo.getTitle());
            pstmt1.setString(2, vo.getContents());
            pstmt1.setInt(3, vo.getHit());
            pstmt1.setLong(4, vo.getgNo());
            pstmt1.setLong(5, vo.getoNo());
            pstmt1.setInt(6, vo.getDepth());
            pstmt1.setLong(7, vo.getUserNo());

            result = pstmt1.executeUpdate();

            ResultSet rs = pstmt2.executeQuery();
            vo.setNo(rs.next() ? rs.getLong(1) : null);
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }

        return result;
    }

    public int findMaxGroupNo() {
        int result = 0;
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                        "select max(g_no) from board");
                ResultSet rs = pstmt.executeQuery();
        ) {
            if(rs.next()) {
                result = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }

        return result;
    }

    public static List<BoardVo> findAll() {
        List<BoardVo> result = new ArrayList<>();

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                        "select a.no ,  a.title  , a.contents , a.reg_date, a.depth , a.hit , b.no as user_no , b.name " +
                                "from board a ,user b where a.user_no = b.no order by a.g_no desc ");
                ResultSet rs = pstmt.executeQuery();
        ) {

            while(rs.next()) {
                Long no = rs.getLong(1);
                String title = rs.getString(2);
                String contents = rs.getString(3);
                String regDate = rs.getString(4);
                int depth = rs.getInt(5);
                int hit = rs.getInt(6);
                Long userNo = rs.getLong(7);
                String userName = rs.getString(8);

                BoardVo vo = new BoardVo();
                vo.setNo(no);
                vo.setTitle(title);
                vo.setContents(contents);
                vo.setRegDate(regDate);
                vo.setDepth(depth);
                vo.setHit(hit);
                vo.setUserNo(userNo);
                vo.setUserName(userName);

                result.add(vo);
            }

        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }

        return result;
    }
    public BoardVo findByNo(Long no) {
        BoardVo result = null;

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("select a.no, a.title, a.contents, a.reg_date, a.hit, " +
                        "a.g_no, a.o_no, a.depth, b.no, b.name " +
                        "from board a, user b where a.user_no = b.no and a.no = ?");
        ) {

            pstmt.setLong(1, no);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                String title = rs.getString(2);
                String content = rs.getString(3);
                String regDate = rs.getString(4);
                int hit = rs.getInt(5);
                int groupNo = rs.getInt(6);
                int orderNo = rs.getInt(7);
                int depths = rs.getInt(8);
                Long userNo = rs.getLong(9);
                String userName = rs.getString(10);

                result = new BoardVo();
                result.setNo(no);
                result.setTitle(title);
                result.setContents(content);
                result.setRegDate(regDate);
                result.setHit(hit);
                result.setgNo(groupNo);
                result.setoNo(orderNo);
                result.setDepth(depths);
                result.setUserNo(userNo);
                result.setUserName(userName);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }

        return result;
    }

    public int updateHit(Long no) {
        int result = 0;
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("update board set hit = hit + 1 where no = ?")
        ) {
            pstmt.setLong(1, no);
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
        return result;
    }

    private static Connection getConnection() throws SQLException {
        Connection conn = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            String url = "jdbc:mariadb://192.168.64.3:3306/webdb?charset=utf8";
            conn = DriverManager.getConnection(url, "webdb", "webdb");
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패:" + e);
        }

        return conn;
    }


}
