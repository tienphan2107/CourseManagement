/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursemanagement.BUS;

import com.mycompany.coursemanagement.DAO.CourseDAO;
import com.mycompany.coursemanagement.DAO.CourseInstructorDAO;
import com.mycompany.coursemanagement.DAO.OnlineCourseDAO;
import com.mycompany.coursemanagement.DAO.StudentGradeDAO;
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
    private final CourseInstructorDAO courseInstructorDAO = new CourseInstructorDAO();
    private final StudentGradeDAO studentGradeDAO = new StudentGradeDAO();

    public OnlineCourseBUS() {
    }

    public Course getOnlineCourseDetail(int id) throws SQLException {
        return ocDAO.getOnlineCourseDetail(id);
    }

    public Course validateCourseInfo(String courseId, String title, String credits, Department department, String url, boolean checkExisted) throws Exception {
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

        if (checkExisted) {
            boolean existed = false;
            try {
                existed = courseDAO.checkCourseExisted(id);
            } catch (SQLException e) {
                throw e;
            }
            if (existed) {
                throw new IllegalArgumentException("Course with ID " + id + " already existed");
            }
        }

        if (!Pattern.matches("^\\d{1,2}$", credits)) {
            throw new IllegalArgumentException("Credits must be an integer");
        }
        if (!Pattern.matches("^(https?|ftp)://[^\\s/$.?#].[^\\s]*$", url)) {
            throw new IllegalArgumentException("Invalid URL");
        }

        OnlineCourse onlineCourse = new OnlineCourse(id, url);
        return new Course(id, title, Integer.parseInt(credits), department.getDepartmentID(), onlineCourse, null, department);
    }

    public int addCourse(String courseId, String title, String credits, Department department, String url) throws Exception {
        Course c = validateCourseInfo(courseId, title, credits, department, url, true);
        int result;
        try {
            result = ocDAO.addOnlineCourse(c);
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }

    public int editCourse(Course oldCourseInfo, String courseId, String title, String credits, Department department, String url) throws Exception {
        String oldCredits = String.valueOf(oldCourseInfo.getCredits());

        if (oldCourseInfo.getTitle().equals(title)
                && oldCredits.equals(credits)
                && oldCourseInfo.getDepartment().getDepartmentID() == department.getDepartmentID()
                && oldCourseInfo.getOnlineCourse().getUrl().equals(url)) {
            throw new Exception("Form unchanged");
        }
        Course c = validateCourseInfo(courseId, title, credits, department, url, false);
        int result;
        try {
            result = ocDAO.editOnlineCourse(c);
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }

    public int deleteOnlineCourse(int courseId, String courseTitle) throws SQLException, IllegalArgumentException {
        boolean canBeDeleted = true;
        try {
            canBeDeleted = !courseInstructorDAO.anyInstructorFound(courseId) && !studentGradeDAO.anyEnrollmentFound(courseId);
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }

        if (!canBeDeleted) {
            throw new IllegalArgumentException("Course '" + courseTitle + "' (" + courseId + ") cannot be deleted as it already has instructors assigned or students enrolled.");
        }

        return ocDAO.deleteOnlineCourse(courseId);
    }
}
