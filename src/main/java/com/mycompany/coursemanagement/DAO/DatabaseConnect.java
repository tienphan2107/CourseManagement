package com.mycompany.coursemanagement.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnect {

    private Connection conn = null;

    public DatabaseConnect() {
        conn = null;
    }

    public Connection Open() {
        try {
            String dbURL = "jdbc:mysql://localhost:3306/School";
            String username = "root";
            String password = "";
            conn = DriverManager.getConnection(dbURL, username, password);
        } catch (SQLException ex) {
            System.out.println("Lỗi kết nối: " + ex.getMessage());
            return null;
        }
        return conn;
    }

    public void Close(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi đóng kết nối: " + ex.getMessage());
        }
    }
}
