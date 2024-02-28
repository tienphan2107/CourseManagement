/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursemanagement.BUS;

import com.mycompany.coursemanagement.DAO.PersonDAO;
import com.mycompany.coursemanagement.Models.Person;
import java.util.Date;
import java.util.List;

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
    public List<Person> GetAllStudent() throws Exception{
        return personDAO.GetAllStudent();
    }
    public boolean AddStudent(Person ps){
        if(personDAO.AddStudent(ps)) return true;
        return false;

    }
}
