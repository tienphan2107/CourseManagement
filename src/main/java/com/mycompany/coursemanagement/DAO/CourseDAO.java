/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursemanagement.DAO;

import com.mycompany.coursemanagement.Models.Course;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class CourseDAO {

    Connection conn;
    Statement stmt;
    ResultSet rs;
    DatabaseConnect db;

    public CourseDAO() {
        conn = null;
        stmt = null;
        rs = null;
        db = new DatabaseConnect();
    }

    public List<Course> Get() {
        List<Course> list = new ArrayList<>();
        try {
            conn = db.Open(); // mở kết nối csdl
            if (conn != null) {
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT * FROM course"); // lệnh truy vấn
                
                while (rs.next()) {
                    int courseID = rs.getInt("CourseID");
                    String title = rs.getString("Title");
                    int departmentID = rs.getInt("DepartmentID");
                    int credits = rs.getInt("Credits");
                    Course course = new Course(courseID, title, credits, departmentID);
                    list.add(course);
                }
            }
        } catch (Exception ex) {
            return new ArrayList<>(); // return về mảng rỗng nha, return null nó dễ bị văng lắm
        } finally {
            db.Close(conn); // đóng kết nối
        }
        return list;
    }
}
