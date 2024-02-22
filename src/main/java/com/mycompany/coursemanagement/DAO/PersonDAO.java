 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursemanagement.DAO;

import com.mycompany.coursemanagement.Models.Person;
import com.mycompany.coursemanagement.Models.StudentGrade;
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
public class PersonDAO {

    Connection conn;
    private PreparedStatement ps;
    Statement stmt;
    ResultSet rs;
    DatabaseConnect db;

    public PersonDAO() {
        conn = null;
        ps = null;
        stmt = null;
        rs = null;
        db = new DatabaseConnect();
    }

    public List<Person> GetAllTeacher() throws Exception {
        List<Person> list = new ArrayList<>();
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "SELECT * FROM Person WHERE EnrollmentDate IS NULL";
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

    public Person GetTeacherByID(int personID) throws Exception {
        Person result = new Person();
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "SELECT * FROM person WHERE PersonID = " + personID + " AND HireDate IS NOT null;";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int personIDResult = rs.getInt("PersonID");
                String lastName = rs.getString("Lastname");
                String firstName = rs.getString("Firstname");
                Date hireDate = rs.getDate("Hiredate");
                Date enrollmentDate = null;
                result = new Person(personID, lastName, firstName, hireDate, enrollmentDate);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(conn);
        }
        return result;
    }
    //Lấy danh sách person
    public List<Person> GetAllPerson() throws Exception {
        List<Person> list = new ArrayList<>();
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "SELECT * FROM Person";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int personID = rs.getInt("PersonID");
                String lastName = rs.getString("Lastname");
                String firstName = rs.getString("Firstname");
                Date hireDate = rs.getDate("Hiredate");
                Date enrollmentDate = rs.getDate("EnrollmentDate");
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
    //Hàm thêm Teacher
    public int addTeacher(Person teacher) throws SQLException {
        int result = 0;
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "INSERT INTO person(PersonID, Lastname, Firstname, HireDate, EnrollmentDate) VALUES (?,?,?,?,?)";
            ps = conn.prepareStatement(query);
            ps.setInt(1, teacher.getPersonID());
            ps.setString(2, teacher.getLastName());
            ps.setString(3, teacher.getFirstName());
            ps.setDate(4, teacher.getHireDate());
            ps.setDate(5, null);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
            db.closeConnection(conn);
        }
        return result;
    }
    //Hàm sửa Teacher
    public int editTeacher(Person teacher) throws SQLException {
        int result = 0;
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "UPDATE Person SET Lastname = ?, Firstname = ?, HireDate = ?, EnrollmentDate = ? WHERE PersonID = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, teacher.getLastName());
            ps.setString(2, teacher.getFirstName());
            ps.setDate(3, teacher.getHireDate());
            ps.setDate(4, null);
            ps.setInt(5, teacher.getPersonID());

            result = ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
            db.closeConnection(conn);
        }
        return result;
    }
    //Hàm xóa Teacher
    public int deleteTeacher(int personID) throws SQLException {
        int result = 0;
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "DELETE FROM Person WHERE PersonID = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, personID);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
            db.closeConnection(conn);
        }
        return result;
    }
    //Hàm tìm kiếm
    public List<Person> findTeacher(String value) throws Exception{
        List<Person> list = new ArrayList<>();
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "SELECT * FROM Person Where (Lastname LIKE ? OR Firstname LIKE ?) AND HireDate IS NOT null";
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + value + "%");
            ps.setString(2, "%" + value + "%");
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
}
