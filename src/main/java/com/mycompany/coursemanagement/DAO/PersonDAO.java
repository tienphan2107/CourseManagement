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
            String query = "SELECT * FROM person WHERE EnrollmentDate IS NULL";
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

    public List<Person> GetAllStudent() throws Exception {
        List<Person> list = new ArrayList<>();
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "SELECT * FROM person WHERE HireDate IS NULL";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int personID = rs.getInt("PersonID");
                String lastName = rs.getString("Lastname");
                String firstName = rs.getString("Firstname");
                Date hireDate = null;
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

    //Lấy danh sách person
    public List<Person> GetAllPerson() throws Exception {
        List<Person> list = new ArrayList<>();
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "SELECT * FROM person";

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

    public Person GetStudentByID(int personID) throws Exception {
        Person result = new Person();
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "SELECT * FROM person WHERE PersonID = " + personID + " AND HireDate IS null;";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int personIDResult = rs.getInt("PersonID");
                String lastName = rs.getString("Lastname");
                String firstName = rs.getString("Firstname");
                Date hireDate = null;
                Date enrollmentDate = rs.getDate("EnrollmentDate");
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

    public List<Person> GetStudentID() throws Exception {
        List<Person> list = new ArrayList<>();
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "SELECT PersonID FROM person WHERE HireDate IS null;";
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

    public boolean AddStudent(Person person) {
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "INSERT INTO person (PersonID, Firstname, Lastname, HireDate, EnrollmentDate) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, person.getPersonID());
                stmt.setString(2, person.getFirstName());
                stmt.setString(3, person.getLastName());
                stmt.setNull(4, java.sql.Types.DATE);
                stmt.setDate(5, person.getEnrollmentDate());
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    db.closeConnection(conn);
                    return true;
                } else {
                    db.closeConnection(conn);
                    return false;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            db.closeConnection(conn);
        }
    }

    //Hàm sửa Teacher
    public int editTeacher(Person teacher) throws SQLException {
        int result = 0;
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "UPDATE person SET Lastname = ?, Firstname = ?, HireDate = ?, EnrollmentDate = ? WHERE PersonID = ?";
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
            String query = "DELETE FROM person WHERE PersonID = ?";
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
    public List<Person> findTeacher(String value) throws Exception {
        List<Person> list = new ArrayList<>();
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "SELECT * FROM person Where (Lastname LIKE ? OR Firstname LIKE ?) AND HireDate IS NOT null";
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

    public int getCurrentMaxID() {
        int id = -1;
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "SELECT MAX(PersonID) AS max_id FROM person";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                id = rs.getInt("max_id");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return id;
        } finally {
            db.closeConnection(conn);
            return id;
        }
    }

    public boolean EditStudent(Person person) throws Exception {
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "UPDATE person\n"
                    + "SET Firstname = ?, Lastname = ? WHERE PersonID = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, person.getFirstName());
            ps.setString(2, person.getLastName());
            ps.setInt(3, person.getPersonID());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public boolean DeleteStudent(int studentID) throws SQLException {
        try {
            if (hasReferences(studentID) == 0) {
                // Nếu không có tham chiếu, xóa đối tượng person
                deletePersonById(studentID);
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    // Phương thức để kiểm tra xem có tham chiếu từ bảng khác không

    private int hasReferences(int personId) {
        // Viết truy vấn SQL để kiểm tra tham chiếu từ bảng studentgrade
        String query = "SELECT COUNT(*) AS Ref FROM studentgrade WHERE StudentID = "+personId;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            int id = 0;
            if (rs.next()) {
                id = rs.getInt("Ref");
            }
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private void deletePersonById(int personId) {//Hàm nhỏ để gọi thôi
        // Viết truy vấn SQL để xóa đối tượng person từ bảng persons
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "DELETE FROM person WHERE PersonID = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, personId);
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public List<Person> FindStudent(String condition) throws SQLException {
        List<Person> list = new ArrayList<>();
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "SELECT * FROM `person` WHERE HireDate IS NULL AND (PersonID LIKE '%" + condition + "%' OR Firstname LIKE '%" + condition + "%' OR Lastname LIKE '%" + condition + "%' OR EnrollmentDate LIKE '%" + condition + "%')";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int personID = rs.getInt("PersonID");
                String lastName = rs.getString("Lastname");
                String firstName = rs.getString("Firstname");
                Date hireDate = null;
                Date enrollmentDate = rs.getDate("EnrollmentDate");
                list.add(new Person(personID, lastName, firstName, hireDate, enrollmentDate));
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
            return list;
        }
    }
    public static void main(String[] args) {
        PersonDAO person = new PersonDAO();
        person.conn = person.db.getConnection();
        System.out.println("" + person.hasReferences(2));
    }
}
