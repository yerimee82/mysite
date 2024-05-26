package com.poscodx.mysite.dao;

import com.poscodx.mysite.vo.GuestbookVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestbookDao {
    public boolean deleteByNoAndPassword(Long no, String password) {
        boolean result = false;

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("delete from guestbook where no = ? and password = ?");
        ){
            pstmt.setLong(1, no);
            pstmt.setString(2, password);
            result = pstmt.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }

        return result;
    }

    public int insert(GuestbookVo vo) {
        int result = 0;
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt1 = conn.prepareStatement("insert into guestbook values(null, ?, ?, ?, now())");
                PreparedStatement pstmt2 = conn.prepareStatement("select last_insert_id() from dual");
        ) {

            pstmt1.setString(1, vo.getName());
            pstmt1.setString(2, vo.getPassword());
            pstmt1.setString(3, vo.getContents());
            result = pstmt1.executeUpdate();

            ResultSet rs = pstmt2.executeQuery();
            vo.setNo(rs.next() ? rs.getLong(1) : null);
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }

        return result;
    }

    public static List<GuestbookVo> findAll() {
        List<GuestbookVo> result = new ArrayList<>();

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                        "select no, name, contents, date_format(reg_date, '%Y/%m/%d %H:%i:%s')" +
                                "      from guestbook" +
                                "  order by reg_date desc");
                ResultSet rs = pstmt.executeQuery();
        ) {

            while(rs.next()) {
                Long no = rs.getLong(1);
                String name = rs.getString(2);
                String contents = rs.getString(3);
                String regDate = rs.getString(4);

                GuestbookVo vo = new GuestbookVo();
                vo.setNo(no);
                vo.setName(name);
                vo.setContents(contents);
                vo.setRegDate(regDate);

                result.add(vo);
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