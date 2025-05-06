package DTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/quanlycuahangquat";
    private static String USER = "root";      // mặc định
    private static String PASS = "";          // mặc định

    private static Connection conn;

    private DBConnection() { }

    /** Gọi sau khi login thành công để cập nhật user/pass mới */
    public static void setCredentials(String user, String pass) {
        USER = user;
        PASS = pass;
        // reset connection cũ
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ignored) { }
            conn = null;
        }
    }

    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(URL, USER, PASS);
        }
        return conn;
    }
     public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                conn = null; // Đảm bảo kết nối được giải phóng
            }
        }
    }
    
}
