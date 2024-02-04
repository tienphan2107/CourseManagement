/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.sql.Connection;

import Database.JDBCUtil;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author DELL
 */
public class TestJDBCUtil {

    public static void main(String[] args) {
        try {
            Connection connection = JDBCUtil.getConnection();

            Statement st = connection.createStatement();

            String sql = "INSERT INTO studentgrade(EnrollmentID, CourseID, StudentID, Grade)"
                    + "VALUES (41, 2021, 31, '3.00')";

            int check = st.executeUpdate(sql);

            System.out.println("Số dòng thay đổi: " + check);
            if (check > 0) {
                System.out.println("Thêm dữ liệu thành công!");
            } else {
                System.out.println("Thêm dữ liệu thất bại!");
            }

            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
