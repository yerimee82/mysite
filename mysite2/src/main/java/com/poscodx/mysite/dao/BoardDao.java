package com.poscodx.mysite.dao;

import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.GuestbookVo;

import java.sql.*;

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
