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

    public List<StudentGrade> Get() {
        List<StudentGrade> list = new ArrayList<>();
        try {
            conn = db.getConnection(); // mở kết nối csdl
            if (conn != null) {
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT * FROM studentgrade"); // lệnh truy vấn

                while (rs.next()) {
                    int enrollmentID = rs.getInt("EnrollmentID");
                    int courseID = rs.getInt("CourseID");
                    int studentID = rs.getInt("StudentID");
                    double grade = rs.getDouble("Grade");
                    StudentGrade studentgrade = new StudentGrade(enrollmentID, courseID, studentID, grade);
                    list.add(studentgrade);
                }
            }
        } catch (Exception ex) {
            return new ArrayList<>(); // return về mảng rỗng nha, return null nó dễ bị văng lắm
        } finally {
            db.closeConnection(conn); // đóng kết nối
        }
        return list;
    }

    public StudentGrade getStudentGradeByCourseAndStudent(int enrollmentID) throws SQLException {
        StudentGrade result = new StudentGrade();

        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "SELECT * FROM studentgrade WHERE EnrollmentID = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, enrollmentID);
            System.out.print(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int enrollmentId = rs.getInt("EnrollmentID");
                int CourseID = rs.getInt("CourseID");
                int StudentID = rs.getInt("StudentID");
                double grade = rs.getDouble("Grade");

                result = new StudentGrade(enrollmentId, CourseID, StudentID, grade);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
            if (rs != null) {
                rs.close();
            }
            db.closeConnection(conn);
        }
        return result;
    }

    public int addStudentGrade(StudentGrade studentGrade) throws SQLException {
        int result = 0;
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "INSERT INTO studentgrade(EnrollmentID, CourseID, StudentID, Grade) VALUES (?,?,?,?)";
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
            String query = "UPDATE studentgrade SET Grade = ? WHERE EnrollmentID = ? AND CourseID = ? AND StudentID = ?";
            ps = conn.prepareStatement(query);
            ps.setDouble(1, studentGrade.getGrade());
            ps.setInt(2, studentGrade.getEnrollmentID());
            ps.setInt(3, studentGrade.getCourseID());
            ps.setInt(4, studentGrade.getStudentID());

            result = ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
            db.closeConnection(conn);
        }
        return result;
    }

    public int deleteStudentGrade(int courseID, int studentID) throws SQLException {
        int result = 0;
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "DELETE FROM studentgrade WHERE CourseID = ? AND StudentID = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, courseID);
            ps.setInt(2, studentID);
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
