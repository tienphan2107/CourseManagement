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
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourseInstructorBUS {

    private CourseInstructorDAO courseInstructorDAO = new CourseInstructorDAO();
    private CourseDAO courseDAO = new CourseDAO();
    private PersonDAO personDAO = new PersonDAO();
    
    public CourseInstructorBUS() {
        
    }

    public List<CourseInstructor> Get() throws Exception {
        return courseInstructorDAO.Get();
    }
    public List<Person> GetTeachersDidNotTeachThisCourse(int courseID) throws Exception{
        return courseInstructorDAO.GetTeacherDidNotTeachThisCourse(courseID);
    }
    
//    public List<Integer> GetCourseIDHaveNoInstructor() throws Exception{
//        return courseInstructorDAO.GetCourseIDHaveNoInstructor();
//    }
    
    public Course GetCourseByID(int courseID) throws Exception {
        return courseDAO.GetCourseByID("", courseID);
    }

    public Person GetTeacherByID(int personID) throws Exception {
        return personDAO.GetTeacherByID(personID);
    }
    
    public Person GetStudentByID(int personID) throws Exception {
        return personDAO.GetStudentByID(personID);
    }

    public List<CourseInstructor> Find(String condition) throws Exception { // hàm tìm kiếm theo id, name, title có phân biệt chữ hoa.
        List<CourseInstructor> resultList = new ArrayList<>();
        for (CourseInstructor ci : Get()) {
            String name = GetTeacherByID(ci.getPersonID()).getFirstName() + GetTeacherByID(ci.getPersonID()).getLastName();
            if (Integer.toString(ci.getCourseID()).contains(condition) || Integer.toString(ci.getPersonID()).contains(condition)) {
                resultList.add(ci);
            } else if (GetCourseByID(ci.getCourseID()).getTitle().contains(condition) || name.contains(condition)) {
                resultList.add(ci);
            }
        }
        return resultList;
    }
    
    public int Delete(int courseID, int personID) throws Exception{
        if(GetCourseByID(courseID)==null || GetTeacherByID(personID)==null){
            throw new IOException("Cannot find the Instructor");
        }
        return courseInstructorDAO.Delete(courseID,personID);
    }
    
    public int Add(CourseInstructor courseInstructor) throws Exception{
        if(courseInstructor == null){
            throw new IOException("Cannot find the Instructor");
        }
        return courseInstructorDAO.Add(courseInstructor);
    }
    
    public int Edit(CourseInstructor courseInstructor) throws Exception{
        if(courseInstructor == null){
            throw new IOException("Cannot find the Instructor");
        }
        return courseInstructorDAO.Edit(courseInstructor);
    }
    
    public boolean anyInstructorFound(int courseId) throws SQLException {
        return courseInstructorDAO.anyInstructorFound(courseId);
    }
}
