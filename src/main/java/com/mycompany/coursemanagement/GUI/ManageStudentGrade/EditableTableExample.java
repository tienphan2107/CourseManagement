/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursemanagement.GUI.ManageStudentGrade;

/**
 *
 * @author DELL
 */
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import com.mycompany.coursemanagement.Models.StudentGrade;
 
public class EditableTableExample extends JFrame
{
    public EditableTableExample()
    {
        StudentGrade row1 = new StudentGrade(1, 2023, 3, 9);
        StudentGrade row2 = new StudentGrade(2, 2024, 4, 9);
        StudentGrade row3 = new StudentGrade(3, 2025, 5, 9);
         
        //build the list
        List<StudentGrade> employeeList = new ArrayList<StudentGrade>();
        employeeList.add(row1);
        employeeList.add(row2);
        employeeList.add(row3);
        
         
        //create the model
        JTableCustom model = new JTableCustom(employeeList);
        //create the table
        JTable table = new JTable(model);
         
        //add the table to the frame
        this.add(new JScrollPane(table));
         
        this.setTitle("Editable Table Example");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        this.pack();
        this.setVisible(true);
    }
     
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EditableTableExample();
            }
        });
    }   
}
