package com.poscodx.mysite.dao;

import com.poscodx.mysite.vo.UserVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            //1. JDBC Driver 로딩
            Class.forName("org.mariadb.jdbc.Driver");

            //2. 연결하기
            String url = "jdbc:mariadb://192.168.64.3:3306/webdb?charset=utf8";
            conn = DriverManager.getConnection(url, "webdb", "webdb");
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패:" + e);
        }

        return conn;
    }

    public int insert(UserVo vo) {
        int result = 0;

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt1 = conn.prepareStatement("insert into user values(null, ?, ?, password(?), ?, current_date())");
                PreparedStatement pstmt2 = conn.prepareStatement("select last_insert_id() from dual");
        ) {

            pstmt1.setString(1, vo.getName());
            pstmt1.setString(2, vo.getEmail());
            pstmt1.setString(3, vo.getPassword());
            pstmt1.setString(4, vo.getGender());
            result = pstmt1.executeUpdate();

            ResultSet rs = pstmt2.executeQuery();
            vo.setNo(rs.next() ? rs.getLong(1) : null);
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }

        return result;
    }
}
