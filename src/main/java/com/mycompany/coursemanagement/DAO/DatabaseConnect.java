package com.mycompany.coursemanagement.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnect {

    private Connection conn = null;

    public DatabaseConnect() {
        conn = null;
    }

    public static Connection getConnection() {
		Connection c = null;
		
		try {
			// Đăng ký MySQL Driver với DriverManager
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			
			// Các thông số
			String url = "jdbc:mysql://localhost:3306/shool";
			String username = "root";
			String password = "";
			
			// Tạo kết nối
			c = DriverManager.getConnection(url, username, password);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}

    public void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi đóng kết nối: " + ex.getMessage());
        }
    }
}
