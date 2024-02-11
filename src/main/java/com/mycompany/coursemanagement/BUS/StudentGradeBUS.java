/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursemanagement.BUS;

import com.mycompany.coursemanagement.DAO.StudentGradeDAO;
import com.mycompany.coursemanagement.Models.StudentGrade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class StudentGradeBUS {
    private final StudentGradeDAO studentGradeDAO = new StudentGradeDAO();
    
    public StudentGradeBUS() {

    }

    public List<StudentGrade> Get() throws Exception {
        return studentGradeDAO.Get();
    }
    
    public int Delete(int courseID, int studentID) throws Exception{
        return studentGradeDAO.deleteStudentGrade(courseID, studentID);
    }
    
    public StudentGrade getStudentGradeByCourseAndStudent(int enrollmentID) throws SQLException {
        return studentGradeDAO.getStudentGradeByCourseAndStudent(enrollmentID);
    }
    
    public int Edit(StudentGrade studentGrade) throws Exception{
        return studentGradeDAO.editStudentGrade(studentGrade);
    }
    
    public int getLastEnrollmentIDFromDatabase() throws Exception {
        return studentGradeDAO.getLastEnrollmentIDFromDatabase();
    }
    
}
