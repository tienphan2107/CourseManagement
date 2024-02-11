/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import com.mycompany.coursemanagement.BUS.StudentGradeBUS;
import com.mycompany.coursemanagement.DAO.StudentGradeDAO;
import com.mycompany.coursemanagement.Models.StudentGrade;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author DELL
 */
public class GradeDAO {
    public static void main(String[] args) throws Exception {
        StudentGrade studentGrade1 = new StudentGrade(0, 0, 33, 0);
        StudentGradeDAO stDAO = new StudentGradeDAO();
        StudentGradeBUS studentGradeBUS = new StudentGradeBUS();
        try {
            List<StudentGrade> studentGrades = studentGradeBUS.Get();
            for (StudentGrade studentGrade : studentGrades) {
                System.out.println("Student ID: " + studentGrade.getStudentID());
                System.out.println("Course ID: " + studentGrade.getCourseID());
                System.out.println("Grade: " + studentGrade.getGrade());
                System.out.println("Enrollment: " + studentGrade.getEnrollmentID());
                System.out.println("-----------------------");
            }    
        } catch (SQLException ex) {
            System.out.println("Error");
             ex.printStackTrace();
        }
    }
}
