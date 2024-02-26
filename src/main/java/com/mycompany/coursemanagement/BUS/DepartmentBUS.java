/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursemanagement.BUS;

import com.mycompany.coursemanagement.DAO.DepartmentDAO;
import com.mycompany.coursemanagement.Models.Department;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DepartmentBUS {

    private final DepartmentDAO departmentDAO = new DepartmentDAO();

    public DepartmentBUS() {

    }

    public ArrayList<Department> getDepartmentList(String name) throws SQLException {
        return departmentDAO.getDepartmentList(name);
    }


    public void addDepartment(Department department) throws SQLException {
        departmentDAO.addDepartment(department);
    }
    public int getNextDepartmentId() throws SQLException {
        return departmentDAO.getNextDepartmentId();
    }
    
    public void updateDepartment(Department department) throws SQLException {
        departmentDAO.updateDepartment(department);
    }
    
    public void deleteDepartment(int departmentId) throws SQLException{
        departmentDAO.deleteDepartment(departmentId);
    }

    public Department validateDepartment(int departmentID, String name, Double budget, LocalDateTime startDate, int administrator) throws IllegalArgumentException {
        if (departmentID <= 0) {
            throw new IllegalArgumentException("Department ID must be greater than 0");
        }

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        if (budget == null || budget < 0) {
            throw new IllegalArgumentException("Budget must be a non-negative value");
        }

        if (startDate == null || startDate.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Start date must be in the past");
        }

        if (administrator <= 0) {
            throw new IllegalArgumentException("Administrator ID must be greater than 0");
        }
        
        return new Department(departmentID, name, budget, startDate, administrator);
    }
    
    public void add(Department department) throws SQLException {
        validateAndThrow(department);
        departmentDAO.addDepartment(department);
    }
    public void update(Department department) throws SQLException {
        validateAndThrow(department);
        departmentDAO.updateDepartment(department);
    }
    private void validateAndThrow(Department department) {
        validateDepartment(department.getDepartmentID(), department.getName(), department.getBudget(), department.getStartDate(), department.getAdministrator());
    }
}
