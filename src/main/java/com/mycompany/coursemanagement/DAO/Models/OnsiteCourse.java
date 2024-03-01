/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursemanagement.Models;

import java.sql.Time;
import java.time.LocalTime;

/**
 *
 * @author PC
 */
public class OnsiteCourse {

    private int courseID;
    private String location;
    private String days;
    private Time time;
    private LocalTime localTime;

    public OnsiteCourse() {
    }

    public OnsiteCourse(int courseID, String location, String days, Time time) {
        this.courseID = courseID;
        this.location = location;
        this.days = days;
        this.time = time;
        this.localTime = time.toLocalTime();
    }

    public OnsiteCourse(int courseID, String location, String days, LocalTime localTime) {
        this.courseID = courseID;
        this.location = location;
        this.days = days;
        this.localTime = localTime;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    @Override
    public String toString() {
        return "OnsiteCourse{" + "courseID=" + courseID + ", location=" + location + ", days=" + days + ", time=" + time + '}';
    }

}
