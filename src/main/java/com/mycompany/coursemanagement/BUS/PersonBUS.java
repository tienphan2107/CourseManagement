/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursemanagement.BUS;

import com.mycompany.coursemanagement.DAO.PersonDAO;
import com.mycompany.coursemanagement.Models.Person;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author PC
 */
public class PersonBUS {
    private PersonDAO personDAO = new PersonDAO();
    
    public List<Person> GetAllTeacher() throws Exception{
        return personDAO.GetAllTeacher();
    }

    public Person GetTeacherByID(int personID) throws Exception {
        return personDAO.GetTeacherByID(personID);
    }
    public int addTeacher(Person teacher)throws Exception{
        
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher object cannot be null");
        }
        // Kiểm tra các thuộc tính hợp lệ của giáo viên
        if (!Pattern.matches("^[0-9]+$", teacher.getPersonID()+"")) {
            throw new IllegalArgumentException("Person ID must be a positive integer");
        }

        if (teacher.getLastName()== null || teacher.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }

        if (teacher.getFirstName()== null || teacher.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }

        if (teacher.getHireDate() == null) {
            throw new IllegalArgumentException("Hire date cannot be null");
        }
        int rs;
        try {
            rs = personDAO.addTeacher(teacher);
        } catch (SQLException e) {
            throw e;
        }
        return rs;
    } 
    public int editTeacher(Person teacher)throws Exception{
        if (teacher.getLastName()== null || teacher.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }

        if (teacher.getFirstName()== null || teacher.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        int rs;
        try {
            rs = personDAO.editTeacher(teacher);
        } catch (SQLException e) {
            throw e;
        }
        return rs;
    }
    public int deleteTeacher(int personID)throws Exception{
        return personDAO.deleteTeacher(personID);
    }
    public List<Person> GetAllPerson() throws Exception{
        return personDAO.GetAllPerson();
    } 
    public List<Person> findTeacher(String value) throws Exception{
        return personDAO.findTeacher(value);
    }
    public List<Person> GetAllStudent() throws Exception{
        return personDAO.GetAllStudent();
    }
    public boolean AddStudent(Person ps){
        if(personDAO.AddStudent(ps)) return true;
        return false;

    }
}
