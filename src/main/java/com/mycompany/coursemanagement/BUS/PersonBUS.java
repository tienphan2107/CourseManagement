/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursemanagement.BUS;

import com.mycompany.coursemanagement.DAO.PersonDAO;
import com.mycompany.coursemanagement.Models.Person;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author PC
 */
public class PersonBUS {

    private PersonDAO personDAO = new PersonDAO();

    public List<Person> GetAllTeacher() throws Exception {
        return personDAO.GetAllTeacher();
    }

    public Person GetTeacherByID(int personID) throws Exception {
        return personDAO.GetTeacherByID(personID);
    }
    public int addTeacher(Person teacher)throws Exception{
        return personDAO.addTeacher(teacher);
    } 
    public int editTeacher(Person teacher)throws Exception{
        return personDAO.editTeacher(teacher);
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

    public boolean AddStudent(Person ps) {
        if (personDAO.AddStudent(ps)) {
            return true;
        }
        return false;

    }

    public int getCurrentID() {
        return personDAO.getCurrentMaxID();
    }

    public Person getStudentByID(int id) {
        Person ps = null;
        try {
            ps = personDAO.GetStudentByID(id);
            return ps;
        }catch(Exception ex){
            ex.printStackTrace();
            return ps;
        }finally{
            return ps;
        }
    }
    public boolean EditStudent(Person person) throws Exception{
        return personDAO.EditStudent(person);
    }
    public boolean DeleteStudent(int studentID) throws SQLException{
        return personDAO.DeleteStudent(studentID);
    }
    public List<Person> FindStudent(String condition) throws Exception {
        return personDAO.FindStudent(condition);
    }public List<Person> GetAllStudent() throws Exception {
        return personDAO.GetAllStudent();
    }
}
