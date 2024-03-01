/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursemanagement.Models;

public class CourseInstructor {
    private int courseID;
    private int personID;

    public CourseInstructor() {
    }

    public CourseInstructor(int courseID, int personID) {
        this.courseID = courseID;
        this.personID = personID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    @Override
    public String toString() {
        return "CourseInstructor{" + "courseID=" + courseID + ", personID=" + personID + '}';
    }
    
    
}
