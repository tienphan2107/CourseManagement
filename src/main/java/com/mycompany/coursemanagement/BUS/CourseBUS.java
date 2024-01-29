/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursemanagement.BUS;

import com.mycompany.coursemanagement.DAO.CourseDAO;
import com.mycompany.coursemanagement.Models.Course;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class CourseBUS {
    private final CourseDAO courseDAO = new CourseDAO();
    
    public CourseBUS() {
        
    }
    
    public ArrayList<Course> getCourseList(String courseTitle) throws SQLException {
        return courseDAO.getCourseList(courseTitle);
    }
}
