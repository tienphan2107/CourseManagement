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
            return new ArrayList<>();
        } finally {
            // Đảm bảo đóng tất cả các tài nguyên
            try { rs.close(); } catch (Exception e) { /* Ignored */ }
            try { stmt.close(); } catch (Exception e) { /* Ignored */ }
            try { conn.close(); } catch (Exception e) { /* Ignored */ }
        }

        return studentGrades;
    }
    
    
    public int addStudentGrade(StudentGrade studentGrade) throws SQLException {
        int result = 0;
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "INSERT INTO course(CourseID, Title, Credits, DepartmentID) VALUES (?,?,?,?)";
            ps = conn.prepareStatement(query);
            ps.setInt(1, studentGrade.getEnrollmentID());
            ps.setInt(2, studentGrade.getCourseID());
            ps.setInt(3, studentGrade.getStudentID());
            ps.setDouble(4, studentGrade.getGrade());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
            db.closeConnection(conn);
        }
        return result;
    }
    
    
    public int editStudentGrade(StudentGrade studentGrade) throws SQLException {
        int result = 0;
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "UPDATE studentgrade SET Grade = ? WHERE StudentID = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, studentGrade.getEnrollmentID());
            ps.setInt(2, studentGrade.getCourseID());
            ps.setInt(3, studentGrade.getStudentID());
            ps.setDouble(4, studentGrade.getGrade());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
            db.closeConnection(conn);
        }
        return result;
    }
    
    public int deleteStudentGrade(StudentGrade studentGrade) throws SQLException {
        int result = 0;
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "DELETE FROM studentgrade WHERE StudentID = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, enrollmentID);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
            db.closeConnection(conn);
        }
        return result;
    }
    
    
}
