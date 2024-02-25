package com.mycompany.coursemanagement.DAO;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnect {

    private Connection c = null;

    public DatabaseConnect() {
        c = null;
    }

    public Connection getConnection() {
		Connection c = null;
		
		try {
			// Đăng ký MySQL Driver với DriverManager
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			
			// Các thông số
			String url = "jdbc:mysql://localhost:3306/school";
			String username = "root";
			String password = "299792458";
			
			// Tạo kết nối
			c = DriverManager.getConnection(url, username, password);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}

    public void closeConnection(Connection c) {
        try {
            if (c != null && !c.isClosed()) {
                c.close();
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi đóng kết nối: " + ex.getMessage());
        }
    }
    
    public static void printInfo(Connection c) {
		try {
			if(c!=null) {
				DatabaseMetaData mtdt = c.getMetaData();
				System.out.println(mtdt.getDatabaseProductName());
				System.out.println(mtdt.getDatabaseProductVersion());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
