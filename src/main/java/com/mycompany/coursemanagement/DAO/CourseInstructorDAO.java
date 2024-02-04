/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursemanagement.DAO;

import com.mycompany.coursemanagement.Models.CourseInstructor;
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
            rs.close();
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
            rs.close();
            ps.close();
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(conn);
        }
        return result;
    }

    public int Delete(int courseID) throws Exception {
        int result = 0;
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "DELETE FROM CourseInstructor\n"
                    + "WHERE CourseID = "+courseID+";";
            ps = conn.prepareStatement(query);
            result += ps.executeUpdate();
            rs.close();
            ps.close();
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(conn);
        }
        return result;
    }
}
