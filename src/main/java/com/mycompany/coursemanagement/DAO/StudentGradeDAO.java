/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursemanagement.DAO;

import com.mycompany.coursemanagement.Models.StudentGrade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class StudentGradeDAO {
    
    Connection conn;
    private PreparedStatement ps;
    Statement stmt;
    ResultSet rs;
    DatabaseConnect db;


    public StudentGradeDAO() {
        conn = null;
        ps = null;
        stmt = null;
        rs = null;
        db = new DatabaseConnect();
    }
    
    public List<StudentGrade> getAllStudentGrades() {
        List<StudentGrade> studentGrades = new ArrayList<>();

        try {
            conn = db.getConnection();
            String query = "SELECT * FROM studentgrade";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int enrollmentID = rs.getInt("EnrollmentID");
                int courseID = rs.getInt("CourseID");
                int studentID = rs.getInt("StudentID");
                double grade = rs.getDouble("Grade");

                StudentGrade studentGrade = new StudentGrade(enrollmentID, courseID, studentID, grade);
                studentGrades.add(studentGrade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo đóng tất cả các tài nguyên
            try { rs.close(); } catch (Exception e) { /* Ignored */ }
            try { stmt.close(); } catch (Exception e) { /* Ignored */ }
            try { conn.close(); } catch (Exception e) { /* Ignored */ }
        }

        return studentGrades;
    }
    
    
}
