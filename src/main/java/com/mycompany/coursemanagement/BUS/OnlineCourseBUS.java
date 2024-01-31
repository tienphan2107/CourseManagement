/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursemanagement.BUS;

import com.mycompany.coursemanagement.DAO.CourseDAO;
import com.mycompany.coursemanagement.DAO.OnlineCourseDAO;
import com.mycompany.coursemanagement.Models.Course;
import com.mycompany.coursemanagement.Models.Department;
import com.mycompany.coursemanagement.Models.OnlineCourse;
import helper.EmptyFieldException;
import java.sql.SQLException;
import java.util.regex.Pattern;

/**
 *
 * @author PC
 */
public class OnlineCourseBUS {

    private final OnlineCourseDAO ocDAO = new OnlineCourseDAO();
    private final CourseDAO courseDAO = new CourseDAO();

    public OnlineCourseBUS() {
    }

    public Course getOnlineCourseDetail(int id) throws SQLException {
        return ocDAO.getOnlineCourseDetail(id);
    }

    public int addCourse(String courseId, String title, String credits, Department department, String url) throws Exception {
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
        if (url.isEmpty()) {
            throw new EmptyFieldException("url", "You must provide course URL");
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
        if (!Pattern.matches("^(https?|ftp)://[^\\s/$.?#].[^\\s]*$", url)) {
            throw new IllegalArgumentException("Invalid URL");
        }

        OnlineCourse onlineCourse = new OnlineCourse(id, url);
        Course c = new Course(id, title, Integer.parseInt(credits), department.getDepartmentID(), onlineCourse, null, department);

        int result;
        try {
            result = ocDAO.addOnlineCourse(c);
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }
}
