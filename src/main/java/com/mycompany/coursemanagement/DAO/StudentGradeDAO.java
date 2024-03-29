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

    public int updateMultipleStudentGrades(List<StudentGrade> studentGrades) throws SQLException {
        int result = 0;
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "UPDATE studentgrade SET Grade = ? WHERE EnrollmentID = ?";
            ps = conn.prepareStatement(query);

            for (StudentGrade studentGrade : studentGrades) {
                ps.setDouble(1, studentGrade.getGrade());
                ps.setInt(2, studentGrade.getEnrollmentID());
                ps.addBatch(); // Thêm câu lệnh vào batch
            }

            int[] updateCounts = ps.executeBatch(); // Thực thi các câu lệnh trong batch

            // Đếm số lượng bản ghi đã cập nhật thành công
            for (int count : updateCounts) {
                if (count == Statement.SUCCESS_NO_INFO) {
                    result++; // Nếu kết quả trả về là SUCCESS_NO_INFO, tăng biến result lên
                } else if (count >= 0) {
                    result += count; // Nếu kết quả trả về là số dòng bị ảnh hưởng, cộng thêm vào result
                }
            }
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

    public int getLastEnrollmentIDFromDatabase() throws SQLException {
        int lastEnrollmentID = 0;
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }

            String query = "SELECT MAX(EnrollmentID) AS LastEnrollmentID FROM studentgrade";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                lastEnrollmentID = rs.getInt("LastEnrollmentID");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            rs.close();
            ps.close();
            db.closeConnection(conn);
        }
        return lastEnrollmentID;
    }

    public List<StudentGrade> FindByStudentID(int studentID) throws SQLException {
        List<StudentGrade> resultList = new ArrayList<>();
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "SELECT * FROM studentgrade WHERE StudentID = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, studentID);
            rs = ps.executeQuery();
            while (rs.next()) {
                int enrollmentID = rs.getInt("EnrollmentID");
                int courseID = rs.getInt("CourseID");
                double grade = rs.getDouble("Grade");

                StudentGrade studentGrade = new StudentGrade(enrollmentID, courseID, studentID, grade);
                resultList.add(studentGrade);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
            rs.close();
            db.closeConnection(conn);
        }
        return resultList;
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

    public boolean isStudentIDValid(int studentID) {
        String query = "SELECT PersonID FROM person WHERE PersonID = ?";
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            ps = conn.prepareStatement(query);
            ps.setInt(1, studentID);
            rs = ps.executeQuery();
            return rs.next(); // Trả về true nếu có kết quả từ truy vấn, ngược lại trả về false
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isStudentEnrolled(int studentID, int courseID) {
        String query = "SELECT * FROM studentgrade WHERE StudentID = ? AND CourseID = ?";
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            ps = conn.prepareStatement(query);
            ps.setInt(1, studentID);
            ps.setInt(2, courseID);
            rs = ps.executeQuery();
            return rs.next(); // Trả về true nếu có kết quả từ truy vấn, ngược lại trả về false
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                rs.close();
                ps.close();
                db.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean anyEnrollmentFound(int courseId) throws SQLException {
        boolean result = false;
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "SELECT * FROM studentgrade WHERE CourseID = ?";
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

    public int getCourseIDByTitle(String title) throws SQLException {
        int courseID = 0;
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "SELECT CourseID FROM course WHERE Title = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, title);
            rs = ps.executeQuery();
            if (rs.next()) {
                courseID = rs.getInt("CourseID");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
            rs.close();
            db.closeConnection(conn);
        }
        return courseID;
    }

    public int getStudentIDByName(String Name) throws SQLException {
        int personID = 0;
        String FirstName = Name.split(" ")[1];
        String LastName = Name.split(" ")[0];
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "SELECT PersonID FROM person WHERE LastName = ? AND FirstName = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, LastName);
            ps.setString(2, FirstName);
            rs = ps.executeQuery();
            if (rs.next()) {
                personID = rs.getInt("PersonID");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
            rs.close();
            db.closeConnection(conn);
        }
        return personID;
    }
}
