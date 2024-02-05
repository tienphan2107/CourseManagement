/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursemanagement.BUS;

import com.mycompany.coursemanagement.DAO.PersonDAO;
import com.mycompany.coursemanagement.Models.Person;
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
}
