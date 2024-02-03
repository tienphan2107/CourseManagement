/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursemanagement.DAO;

import com.mycompany.coursemanagement.Models.Course;
import com.mycompany.coursemanagement.Models.Department;
import com.mycompany.coursemanagement.Models.OnlineCourse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author PC
 */
public class OnlineCourseDAO {

    Connection conn;
    private PreparedStatement ps;
    Statement stmt;
    ResultSet rs;
    DatabaseConnect db;

    public OnlineCourseDAO() {
        conn = null;
        ps = null;
        stmt = null;
        rs = null;
        db = new DatabaseConnect();
    }

    public Course getOnlineCourseDetail(int id) throws SQLException {
        Course c = new Course();
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = """
                           SELECT C.CourseID, C.Title, Credits, C.DepartmentID, D.Name, url
                           FROM course C, department D, onlinecourse OLC
                           WHERE C.CourseID = OLC.CourseID AND C.DepartmentID = D.DepartmentID AND C.CourseID = ?
                           """;
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                int courseId = rs.getInt("CourseID");
                String title = rs.getString("Title");
                int credits = rs.getInt("Credits");
                int departmentId = rs.getInt("DepartmentID");
                String departmentName = rs.getString("Name");
                String url = rs.getString("url");

                Department department = new Department();
                department.setDepartmentID(departmentId);
                department.setName(departmentName);

                OnlineCourse onlineCourse = new OnlineCourse(courseId, url);
                c = new Course(courseId, title, credits, departmentId, onlineCourse, null, department);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
            rs.close();
            db.closeConnection(conn);
        }
        return c;
    }

    public int addOnlineCourse(Course course) throws SQLException {
        int result = 0;
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query1 = "INSERT INTO course(CourseID, Title, Credits, DepartmentID) VALUES (?,?,?,?)";
            String query2 = "INSERT INTO onlinecourse(CourseID, url) VALUES (?,?)";
            ps = conn.prepareStatement(query1);
            ps.setInt(1, course.getCourseID());
            ps.setString(2, course.getTitle());
            ps.setInt(3, course.getCredits());
            ps.setInt(4, course.getDepartmentID());
            result = ps.executeUpdate();
            ps = conn.prepareStatement(query2);
            ps.setInt(1, course.getCourseID());
            ps.setString(2, course.getOnlineCourse().getUrl());
            result += ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
            db.closeConnection(conn);
        }
        return result;
    }

    public int editOnlineCourse(Course course) throws SQLException {
        int result = 0;
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query1 = "UPDATE course SET Title=?,Credits=?,DepartmentID=? WHERE CourseID = ?";
            String query2 = "UPDATE onlinecourse SET url=? WHERE CourseID = ?";
            ps = conn.prepareStatement(query1);
            ps.setString(1, course.getTitle());
            ps.setInt(2, course.getCredits());
            ps.setInt(3, course.getDepartmentID());
            ps.setInt(4, course.getCourseID());
            result = ps.executeUpdate();
            ps = conn.prepareStatement(query2);
            ps.setString(1, course.getOnlineCourse().getUrl());
            ps.setInt(2, course.getCourseID());
            result += ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
            db.closeConnection(conn);
        }
        return result;
    }

    public int deleteOnlineCourse(int courseId) throws SQLException {
        int result = 0;
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query1 = "DELETE FROM onlinecourse WHERE CourseID = ?";
            String query2 = "DELETE FROM course WHERE CourseID = ?";
            ps = conn.prepareStatement(query1);
            ps.setInt(1, courseId);
            result = ps.executeUpdate();
            ps = conn.prepareStatement(query2);
            ps.setInt(1, courseId);
            result += ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
            db.closeConnection(conn);
        }
        return result;
    }
}
