/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursemanagement.Models;

/**
 *
 * @author PC
 */
public class OnlineCourse {
    private int courseID;
    private String url;

    public OnlineCourse() {
    }

    public OnlineCourse(int courseID, String url) {
        this.courseID = courseID;
        this.url = url;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "OnlineCourse{" + "courseID=" + courseID + ", url=" + url + '}';
    }
    
}
