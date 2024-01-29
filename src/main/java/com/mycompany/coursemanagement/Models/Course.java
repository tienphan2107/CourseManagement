/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursemanagement.Models;

public class Course {
    private int courseID;
    private String title;
    private int credits;
    private int departmentID;
    private OnlineCourse onlineCourse;
    private OnsiteCourse onsiteCourse;
    private Department department;

    public Course() {
    }

    public Course(int courseID, String title, int credits, int departmentID) {
        this.courseID = courseID;
        this.title = title;
        this.credits = credits;
        this.departmentID = departmentID;
        this.onlineCourse = null;
        this.onsiteCourse = null;
        this.department = null;
    }

    public Course(int courseID, String title, int credits, int departmentID, OnlineCourse onlineCourse, OnsiteCourse onsiteCourse, Department department) {
        this.courseID = courseID;
        this.title = title;
        this.credits = credits;
        this.departmentID = departmentID;
        this.onlineCourse = onlineCourse;
        this.onsiteCourse = onsiteCourse;
        this.department = department;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public OnlineCourse getOnlineCourse() {
        return onlineCourse;
    }

    public void setOnlineCourse(OnlineCourse onlineCourse) {
        this.onlineCourse = onlineCourse;
    }

    public OnsiteCourse getOnsiteCourse() {
        return onsiteCourse;
    }

    public void setOnsiteCourse(OnsiteCourse onsiteCourse) {
        this.onsiteCourse = onsiteCourse;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    
    @Override
    public String toString() {
        return "Course{" + "courseID=" + courseID + ", title=" + title + ", credits=" + credits + ", departmentID=" + departmentID + '}';
    }
    
    
}
