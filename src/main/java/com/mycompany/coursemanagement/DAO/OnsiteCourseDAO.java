/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursemanagement.DAO;

import com.mycompany.coursemanagement.Models.Course;
import com.mycompany.coursemanagement.Models.Department;
import com.mycompany.coursemanagement.Models.OnsiteCourse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalTime;

/**
 *
 * @author PC
 */
public class OnsiteCourseDAO {

    Connection conn;
    private PreparedStatement ps;
    Statement stmt;
    ResultSet rs;
    DatabaseConnect db;

    public OnsiteCourseDAO() {
        conn = null;
        ps = null;
        stmt = null;
        rs = null;
        db = new DatabaseConnect();
    }

    public Course getOnsiteCourseDetail(int id) throws SQLException {
        Course c = new Course();
        try {
            conn = db.Open();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = """
                           SELECT C.CourseID, C.Title, Credits, C.DepartmentID, D.Name, Location, Days, OSC.Time
                           FROM course C, department D, onsitecourse OSC
                           WHERE C.CourseID = OSC.CourseID AND C.DepartmentID = D.DepartmentID AND C.CourseID = ?
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
                String location = rs.getString("Location");
                String days = rs.getString("Days");
                Time time = rs.getTime("Time");
                LocalTime localTime = time.toLocalTime();

                Department department = new Department();
                department.setDepartmentID(departmentId);
                department.setName(departmentName);

                OnsiteCourse onsiteCourse = new OnsiteCourse(courseId, location, days, localTime);
                c = new Course(courseId, title, credits, departmentId, null, onsiteCourse, department);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
            rs.close();
            db.Close(conn);
        }
        return c;
    }

    public int addOnsiteCourse(Course course) throws SQLException {
        int result = 0;
        try {
            conn = db.Open();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query1 = "INSERT INTO course(CourseID, Title, Credits, DepartmentID) VALUES (?,?,?,?)";
            String query2 = "INSERT INTO onsitecourse(CourseID, Location, Days, Time) VALUES (?,?,?,?)";
            ps = conn.prepareStatement(query1);
            ps.setInt(1, course.getCourseID());
            ps.setString(2, course.getTitle());
            ps.setInt(3, course.getCredits());
            ps.setInt(4, course.getDepartmentID());
            result = ps.executeUpdate();
            ps = conn.prepareStatement(query2);
            ps.setInt(1, course.getCourseID());
            ps.setString(2, course.getOnsiteCourse().getLocation());
            ps.setString(3, course.getOnsiteCourse().getDays());
            Time time = Time.valueOf(course.getOnsiteCourse().getLocalTime());
            ps.setTime(4, time);
            result += ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
            db.Close(conn);
        }
        return result;
    }

    public int editOnsiteCourse(Course course) throws SQLException {
        int result = 0;
        try {
            conn = db.Open();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query1 = "UPDATE course SET Title=?,Credits=?,DepartmentID=? WHERE CourseID = ?";
            String query2 = "UPDATE onsitecourse SET Location=?,Days=?,Time=? WHERE CourseID = ?";
            ps = conn.prepareStatement(query1);
            ps.setString(1, course.getTitle());
            ps.setInt(2, course.getCredits());
            ps.setInt(3, course.getDepartmentID());
            ps.setInt(4, course.getCourseID());
            result = ps.executeUpdate();
            ps = conn.prepareStatement(query2);
            ps.setString(1, course.getOnsiteCourse().getLocation());
            ps.setString(2, course.getOnsiteCourse().getDays());
            Time time = Time.valueOf(course.getOnsiteCourse().getLocalTime());
            ps.setTime(3, time);
            ps.setInt(4, course.getCourseID());
            result += ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
            db.Close(conn);
        }
        return result;
    }

    public int deleteOnsiteCourse(int courseId) throws SQLException {
        int result = 0;
        try {
            conn = db.Open();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query1 = "DELETE FROM onsitecourse WHERE CourseID = ?";
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
            db.Close(conn);
        }
        return result;
    }
}
