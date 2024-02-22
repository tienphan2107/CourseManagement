/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursemanagement.DAO;

import com.mycompany.coursemanagement.Models.Department;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class DepartmentDAO {

    Connection conn;
    private PreparedStatement ps;
    Statement stmt;
    ResultSet rs;
    DatabaseConnect db;

    public DepartmentDAO() {
        conn = null;
        ps = null;
        stmt = null;
        rs = null;
        db = new DatabaseConnect();
    }

    public ArrayList<Department> getDepartmentList(String name) throws SQLException {
        ArrayList<Department> list = new ArrayList<>();
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "SELECT * FROM department WHERE Name LIKE ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                int departmentId = rs.getInt("DepartmentID");
                String departmentName = rs.getString("Name");
                Double budget = rs.getDouble("Budget");
                Timestamp timestamp = rs.getTimestamp("StartDate");
                LocalDateTime startDate = timestamp.toLocalDateTime();
                int administrator = rs.getInt("Administrator");
                list.add(new Department(departmentId, departmentName, budget, startDate, administrator));
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

    public void addDepartment(Department department) throws SQLException {
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }

            String query = "INSERT INTO department (DepartmentID, Name, Budget, StartDate, Administrator) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setInt(1, department.getDepartmentID());
            ps.setString(2, department.getName());
            ps.setDouble(3, department.getBudget());
            ps.setTimestamp(4, Timestamp.valueOf(department.getStartDate()));
            ps.setInt(5, department.getAdministrator());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            db.closeConnection(conn);
        }
    }

    public int getNextDepartmentId() throws SQLException {
        int nextId = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }

            String query = "SELECT MAX(DepartmentID) AS MaxId FROM department";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                nextId = rs.getInt("MaxId") + 1;
            } else {
                nextId = 1; // If no departments exist yet, start with 1
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    db.closeConnection(conn);
                }
            } catch (SQLException ex) {
                // Log or handle exception
            }
        }

        return nextId;
    }

    public void updateDepartment(Department department) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }

            String query = "UPDATE department SET Name = ?, Budget = ?, StartDate = ?, Administrator = ? WHERE DepartmentID = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, department.getName());
            ps.setDouble(2, department.getBudget());
            ps.setTimestamp(3, Timestamp.valueOf(department.getStartDate()));
            ps.setInt(4, department.getAdministrator());
            ps.setInt(5, department.getDepartmentID());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Update failed, department with ID " + department.getDepartmentID() + " not found");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                db.closeConnection(conn);
            }
        }
    }

    public void deleteDepartment(int departmentId) throws SQLException {
        try {
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Connection error");
            }
            String query = "DELETE FROM department WHERE DepartmentID = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, departmentId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Failed to delete department. DepartmentID not found: " + departmentId);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            db.closeConnection(conn);
        }
    }

}
