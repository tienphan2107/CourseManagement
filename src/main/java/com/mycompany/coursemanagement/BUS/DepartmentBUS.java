/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursemanagement.BUS;

import com.mycompany.coursemanagement.DAO.DepartmentDAO;
import com.mycompany.coursemanagement.Models.Department;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class DepartmentBUS {

    private final DepartmentDAO departmentDAO = new DepartmentDAO();

    public DepartmentBUS() {

    }

    public ArrayList<Department> getDepartmentList(String name) throws SQLException {
        return departmentDAO.getDepartmentList(name);
    }
}
