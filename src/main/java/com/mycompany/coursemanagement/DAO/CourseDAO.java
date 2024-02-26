/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursemanagement.DAO;

import com.mycompany.coursemanagement.Models.Course;
import com.mycompany.coursemanagement.Models.Department;
import com.mycompany.coursemanagement.Models.OnlineCourse;
import com.mycompany.coursemanagement.Models.OnsiteCourse;
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
public class CourseDAO {

    Connection conn;
    private PreparedStatement ps;
    Statement stmt;
    ResultSet rs;
    DatabaseConnect db;

    public CourseDAO() {
        conn = null;
        ps = null;
        stmt = null;
        rs = null;
        db = new DatabaseConnect();
    }

    public List<Course> Get() {
        List<Course> list = new ArrayList<>();
        try {
            conn = db.getConnection(); // mở kết nối csdl
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
            db.closeConnection(conn); // đóng kết nối
        }
        return list;
    }

    public ArrayList<Course> getCourseList(String courseTitle) throws SQLException {
        ArrayList<Course> list = new ArrayList<>();
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = """
                           SELECT C.CourseID, Title, Credits, C.DepartmentID, D.Name, Days
                           FROM course C
                           	JOIN department D ON D.DepartmentID = C.DepartmentID
                           	LEFT JOIN onsitecourse OSC ON C.CourseID = OSC.CourseID
                           WHERE Title LIKE ?
                           ORDER BY Title ASC
                           """;
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + courseTitle + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                int courseId = rs.getInt("CourseID");
                String title = rs.getString("Title");
                int credits = rs.getInt("Credits");
                int departmentId = rs.getInt("DepartmentID");
                String departmentName = rs.getString("Name");
                String days = rs.getString("Days"); // Use to check if course type is online/onsite

                Department department = new Department();
                department.setDepartmentID(departmentId);
                department.setName(departmentName);

                OnlineCourse onlineCourse = new OnlineCourse();
                OnsiteCourse onsiteCourse = new OnsiteCourse();
                onsiteCourse.setDays(days);

                list.add(new Course(courseId, title, credits, departmentId, onlineCourse, onsiteCourse, department));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
            rs.close();
            db.closeConnection(conn);
        }
        return list;
    }

    public boolean checkCourseExisted(int courseId) throws SQLException {
        boolean result = false;
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "SELECT Title FROM course WHERE CourseID = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, courseId);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
            rs.close();
            db.closeConnection(conn);
        }
        return result;
    }

    //-------------------------------------------------Ham nay Tien viet nha-------------------------------------------------------
    public Course GetCourseByID(String courseTitle, int courseID) throws SQLException {
        Course result = new Course();
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = """
                           SELECT C.CourseID, Title, Credits, C.DepartmentID, D.Name, Days
                           FROM course C
                           	JOIN department D ON D.DepartmentID = C.DepartmentID
                           	LEFT JOIN onsitecourse OSC ON C.CourseID = OSC.CourseID
                           WHERE Title LIKE ? AND C.CourseID = ?
                           ORDER BY Title ASC
                           """;
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + courseTitle + "%");
            ps.setString(2, courseID + "");
            rs = ps.executeQuery();
            while (rs.next()) {
                int courseId = rs.getInt("CourseID");
                String title = rs.getString("Title");
                int credits = rs.getInt("Credits");
                int departmentId = rs.getInt("DepartmentID");
                String departmentName = rs.getString("Name");
                String days = rs.getString("Days"); // Use to check if course type is online/onsite

                Department department = new Department();
                department.setDepartmentID(departmentId);
                department.setName(departmentName);

                OnlineCourse onlineCourse = new OnlineCourse();
                OnsiteCourse onsiteCourse = new OnsiteCourse();
                onsiteCourse.setDays(days);

                result = new Course(courseId, title, credits, departmentId, onlineCourse, onsiteCourse, department);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
            rs.close();
            db.closeConnection(conn);
        }
        return result;
    }

    public String getTitleByCourseID(int courseID) throws SQLException {
        String title = null;
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "SELECT Title FROM course WHERE CourseID = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, courseID);
            rs = ps.executeQuery();
            if (rs.next()) {
                title = rs.getString("Title");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
            rs.close();
            db.closeConnection(conn);
        }
        return title;
    }
}
