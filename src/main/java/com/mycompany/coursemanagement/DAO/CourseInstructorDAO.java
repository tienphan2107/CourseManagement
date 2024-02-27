/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursemanagement.DAO;

import com.mycompany.coursemanagement.Models.CourseInstructor;
import com.mycompany.coursemanagement.Models.Person;
import java.sql.Connection;
import java.sql.Date;
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
public class CourseInstructorDAO {

    private Connection conn;
    private PreparedStatement ps;
    private Statement stmt;
    private ResultSet rs;
    private DatabaseConnect db;

    public CourseInstructorDAO() {
        conn = null;
        ps = null;
        stmt = null;
        rs = null;
        db = new DatabaseConnect();
    }

    public List<CourseInstructor> Get() throws Exception {
        List<CourseInstructor> list = new ArrayList<>();
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "SELECT * FROM courseinstructor";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int courseID = rs.getInt("CourseID");
                int personID = rs.getInt("PersonID");
                list.add(new CourseInstructor(courseID, personID));
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(conn);
        }
        return list;
    }

    public List<Person> GetTeacherDidNotTeachThisCourse(int courseID) throws Exception {
        List<Person> list = new ArrayList<>();
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "SELECT p.* FROM courseinstructor ci join person p on p.PersonID != ci.PersonID\n"
                    + "WHERE ci.CourseID="+courseID+" and p.HireDate IS NOT null;";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int personID = rs.getInt("PersonID");
                String lastName = rs.getString("Lastname");
                String firstName = rs.getString("Firstname");
                Date hireDate = rs.getDate("Hiredate");
                Date enrollmentDate = null;
                list.add(new Person(personID, lastName, firstName, hireDate, enrollmentDate));
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(conn);
        }
        return list;
    }

//    public List<Integer> GetCourseIDHaveNoInstructor() throws Exception { // hàm này lấy ra danh sách các courseID chưa có giáo viên dạy
//        List<Integer> list = new ArrayList<>();
//        try {
//            conn = db.getConnection();
//            if (conn == null) {
//                throw new SQLException("Connection error");
//            }
//            String query = "SELECT c.CourseID\n"
//                    + " FROM courseinstructor ci RIGHT JOIN course c ON ci.CourseID=c.CourseID\n"
//                    + " WHERE ci.CourseID IS NULL";
//            ps = conn.prepareStatement(query);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                int courseID = rs.getInt("CourseID");
//                list.add(courseID);
//            }
//            rs.close();
//            ps.close();
//        } catch (Exception ex) {
//            throw ex;
//        } finally {
//            db.closeConnection(conn);
//        }
//        return list;
//    }

    public int Add(CourseInstructor courseInstructor) throws Exception {
        int result = 0;
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "INSERT INTO CourseInstructor (CourseID, PersonID)\n"
                    + "VALUES (" + courseInstructor.getCourseID() + ", " + courseInstructor.getPersonID() + ");";
            ps = conn.prepareStatement(query);
            result += ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(conn);
        }
        return result;
    }

    public int Edit(CourseInstructor courseInstructor) throws Exception {
        int result = 0;
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "UPDATE CourseInstructor\n"
                    + "SET PersonID = " + courseInstructor.getPersonID() + "\n"
                    + "WHERE CourseID = " + courseInstructor.getCourseID() + ";";
            ps = conn.prepareStatement(query);
            result += ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(conn);
        }
        return result;
    }

    public int Delete(int courseID, int personID) throws Exception {
        int result = 0;
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "DELETE FROM CourseInstructor\n"
                    + "WHERE CourseID = " + courseID + " AND PersonID = " + personID + " ;";
            ps = conn.prepareStatement(query);
            result += ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(conn);
        }
        return result;
    }
    
    public boolean anyInstructorFound(int courseId) throws SQLException {
        boolean result = false;
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "SELECT * FROM courseinstructor WHERE CourseID = ?";
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
}
