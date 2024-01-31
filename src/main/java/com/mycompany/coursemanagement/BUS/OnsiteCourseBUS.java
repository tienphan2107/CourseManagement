/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursemanagement.BUS;

import com.mycompany.coursemanagement.DAO.CourseDAO;
import com.mycompany.coursemanagement.DAO.OnsiteCourseDAO;
import com.mycompany.coursemanagement.Models.Course;
import com.mycompany.coursemanagement.Models.Department;
import com.mycompany.coursemanagement.Models.OnsiteCourse;
import helper.DateTimeHelper;
import helper.EmptyFieldException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

/**
 *
 * @author PC
 */
public class OnsiteCourseBUS {

    private final OnsiteCourseDAO ocDAO = new OnsiteCourseDAO();
    private final CourseDAO courseDAO = new CourseDAO();

    public OnsiteCourseBUS() {
    }

    public Course getOnsiteCourseDetail(int id) throws SQLException {
        return ocDAO.getOnsiteCourseDetail(id);
    }

    public int addCourse(String courseId, String title, String credits, Department department, String location, boolean[] days, String time) throws Exception {
        if (courseId.isEmpty()) {
            throw new EmptyFieldException("courseId", "You must provide course ID");
        }
        if (title.isEmpty()) {
            throw new EmptyFieldException("title", "You must provide course title");
        }
        if (credits.isEmpty()) {
            throw new EmptyFieldException("credits", "You must provide credits");
        }
        if (department == null) {
            throw new EmptyFieldException("department", "You must choose a department");
        }
        if (location.isEmpty()) {
            throw new EmptyFieldException("location", "You must provide location");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(days[0] ? "M" : "");
        sb.append(days[1] ? "T" : "");
        sb.append(days[2] ? "W" : "");
        sb.append(days[3] ? "H" : "");
        sb.append(days[4] ? "F" : "");
        sb.append(days[5] ? "S" : "");
        if (sb.toString().isEmpty()) {
            throw new EmptyFieldException("days", "You must select days");
        }

        if (time.isEmpty()) {
            throw new EmptyFieldException("time", "You must provide time");
        }

        if (!Pattern.matches("^\\d{4}$", courseId)) {
            throw new IllegalArgumentException("Course ID must be an integer, 4 digits");
        }

        int id = Integer.parseInt(courseId);

        boolean existed = false;
        try {
            existed = courseDAO.checkCourseExisted(id);
        } catch (SQLException e) {
            throw e;
        }
        if (existed) {
            throw new IllegalArgumentException("Course with ID " + id + " already existed");
        }

        if (!Pattern.matches("^\\d{1,2}$", credits)) {
            throw new IllegalArgumentException("Credits must be an integer");
        }

        LocalTime localTime;
        try {
            localTime = DateTimeHelper.parseTime(time);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid time (HH:mm)");
        }

        OnsiteCourse onsiteCourse = new OnsiteCourse(id, location, sb.toString(), localTime);
        Course c = new Course(id, title, Integer.parseInt(credits), department.getDepartmentID(), null, onsiteCourse, department);

        int result;
        try {
            result = ocDAO.addOnsiteCourse(c);
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }

}
