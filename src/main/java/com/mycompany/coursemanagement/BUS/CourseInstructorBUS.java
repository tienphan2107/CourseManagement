/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursemanagement.BUS;

import com.mycompany.coursemanagement.DAO.CourseDAO;
import com.mycompany.coursemanagement.DAO.CourseInstructorDAO;
import com.mycompany.coursemanagement.DAO.PersonDAO;
import com.mycompany.coursemanagement.Models.Course;
import com.mycompany.coursemanagement.Models.CourseInstructor;
import com.mycompany.coursemanagement.Models.Person;
import java.sql.SQLException;
import java.util.List;

public class CourseInstructorBUS {

    CourseInstructorDAO courseInstructorDAO = new CourseInstructorDAO();
    CourseDAO courseDAO = new CourseDAO();
    PersonDAO personDAO = new PersonDAO();
    
    public List<CourseInstructor> Get() throws Exception {
        return courseInstructorDAO.Get();
    }

    public Course GetCourseByID(int courseID) throws SQLException {
        return courseDAO.GetCourseByID("", courseID);
    }
    
    public Person GetTeacherByID(int personID) throws Exception{
        return personDAO.GetTeacherByID(personID);
        
    }
}
