/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursemanagement.Models;


import java.time.LocalDateTime;

import helper.DateTimeHelper;
import java.time.LocalDateTime;
import java.util.Date;


/**
 *
 * @author PC
 */
public class Department {

    private int departmentID;
    private String name;
    private Double budget;

    private LocalDateTime startDate; // Sử dụng kiểu LocalDateTime thay cho Date

    //private Date startDate;
    private LocalDateTime localStartDate;

    private int administrator;

    public Department() {
    }

//    public Department(int departmentID, String name, Double budget, LocalDateTime startDate, int administrator) {
//        this.departmentID = departmentID;
//        this.name = name;
//        this.budget = budget;
//        this.startDate = startDate;
//        this.localStartDate = DateTimeHelper.convertDateObjToLDT(startDate);
//        this.administrator = administrator;
//    }

    public Department(int departmentID, String name, Double budget, LocalDateTime localStartDate, int administrator) {
        this.departmentID = departmentID;
        this.name = name;
        this.budget = budget;
        this.localStartDate = localStartDate;
        this.administrator = administrator;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getLocalStartDate() {
        return localStartDate;
    }

    public void setLocalStartDate(LocalDateTime localStartDate) {
        this.localStartDate = localStartDate;
    }

    public int getAdministrator() {
        return administrator;
    }

    public void setAdministrator(int administrator) {
        this.administrator = administrator;
    }

    @Override
    public String toString() {
        return "Department{" + "departmentID=" + departmentID + ", name=" + name + ", budget=" + budget + ", startDate=" + startDate + ", administrator=" + administrator + '}';
    }

}
