/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursemanagement.BUS;

import com.mycompany.coursemanagement.DAO.PersonDAO;
import com.mycompany.coursemanagement.DAO.StudentGradeDAO;
import com.mycompany.coursemanagement.Models.Person;
import com.mycompany.coursemanagement.Models.StudentGrade;
import helper.EmptyFieldException;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author PC
 */
public class StudentGradeBUS {

    private final StudentGradeDAO studentGradeDAO = new StudentGradeDAO();
    private PersonDAO personDAO = new PersonDAO();

    public StudentGradeBUS() {

    }

    public List<StudentGrade> Get() throws Exception {
        return studentGradeDAO.Get();
    }

    public int Add(StudentGrade studentGrade) throws Exception {
        return studentGradeDAO.addStudentGrade(studentGrade);
    }

    public int Delete(int courseID, int studentID) throws Exception {
        return studentGradeDAO.deleteStudentGrade(courseID, studentID);
    }

    public StudentGrade getStudentGradeByCourseAndStudent(int enrollmentID) throws SQLException {
        return studentGradeDAO.getStudentGradeByCourseAndStudent(enrollmentID);
    }

    public Person GetStudentByID(int personID) throws Exception {
        return personDAO.GetStudentByID(personID);
    }

    public int Edit(StudentGrade studentGrade) throws Exception {
        return studentGradeDAO.editStudentGrade(studentGrade);
    }

    public int getLastEnrollmentIDFromDatabase() throws Exception {
        return studentGradeDAO.getLastEnrollmentIDFromDatabase();
    }

    public List<StudentGrade> FindByStudentID(int studentID) throws Exception {
        return studentGradeDAO.FindByStudentID(studentID);
    }

    public boolean isStudentIDValid(int studentID) throws Exception {
        return studentGradeDAO.isStudentIDValid(studentID);
    }

    public StudentGrade validateStudentGradeInfo(int EnrollmentID, int CourseID, String StudentID, String Grade) throws Exception {
        if (StudentID.isEmpty()) {
            throw new EmptyFieldException("StudentId", "You must provide student ID");
        }
        if (Grade.isEmpty()) {
            throw new EmptyFieldException("Grade", "You must provide grade");
        }

        if (!Pattern.matches("^[0-9]+$", StudentID)) {
            throw new IllegalArgumentException("Student ID must be an integer");
        }
        
        if (!Pattern.matches("^[0-9]+$", Grade)) {
            throw new IllegalArgumentException("Grade must be an integer");
        }

        int id = Integer.parseInt(StudentID);

        if (!isStudentIDValid(id)) {
            throw new IllegalArgumentException("Student ID is not existed");
        }

        return new StudentGrade(EnrollmentID, CourseID, id, Double.parseDouble(Grade));
    }
    
    public int add(int EnrollmentID, int CourseID, String StudentID, String Grade) throws Exception {
        StudentGrade s = validateStudentGradeInfo(EnrollmentID, CourseID, StudentID, Grade);
        int result;
        try {
            result = studentGradeDAO.addStudentGrade(s);
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }
}
