/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursemanagement.Models;

import java.sql.Date;

/**
 *
 * @author PC
 */
public class OfficeAssignment {
    private int instructorID;
    private String location;
    private Date timeStamp;

    public OfficeAssignment() {
    }

    public OfficeAssignment(int instructorID, String location, Date timeStamp) {
        this.instructorID = instructorID;
        this.location = location;
        this.timeStamp = timeStamp;
    }

    public int getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(int instructorID) {
        this.instructorID = instructorID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "OfficeAssignment{" + "instructorID=" + instructorID + ", location=" + location + ", timeStamp=" + timeStamp + '}';
    }
    
}
