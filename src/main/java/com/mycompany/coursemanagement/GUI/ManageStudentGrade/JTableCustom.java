/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursemanagement.GUI.ManageStudentGrade;

/**
 *
 * @author DELL
 */
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.mycompany.coursemanagement.Models.StudentGrade;
import java.util.ArrayList;

public class JTableCustom extends AbstractTableModel {

    private List<StudentGrade> studentGradeList;

    private final String[] columnNames = new String[]{
        "EnrollmentID", "CourseID", "StudentID", "Grade"
    };
    private final Class[] columnClass = new Class[]{
        Integer.class, Integer.class, Integer.class, Double.class
    };

    public JTableCustom(List<StudentGrade> studentGradeList) {
        this.studentGradeList = studentGradeList;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return studentGradeList.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StudentGrade row = studentGradeList.get(rowIndex);
        if (0 == columnIndex) {
            return row.getEnrollmentID();
        } else if (1 == columnIndex) {
            return row.getCourseID();
        } else if (2 == columnIndex) {
            return row.getStudentID();
        } else if (3 == columnIndex) {
            return row.getGrade();
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 3;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        StudentGrade row = studentGradeList.get(rowIndex);
        if (0 == columnIndex) {
            row.setEnrollmentID((Integer) aValue);
        } else if (1 == columnIndex) {
            row.setCourseID((Integer) aValue);
        } else if (2 == columnIndex) {
            row.setStudentID((Integer) aValue);
        } else if (3 == columnIndex) {
            row.setGrade((Double) aValue);
        }
    }
    
     public void setRowCount(int rowCount) {
        int currentRowCount = getRowCount();
        
        if (rowCount < currentRowCount) {
            studentGradeList = studentGradeList.subList(0, rowCount);
        } else if (rowCount > currentRowCount) {
            int rowsToAdd = rowCount - currentRowCount;
            List<StudentGrade> newRows = new ArrayList<>(rowsToAdd);
            for (int i = 0; i < rowsToAdd; i++) {
                newRows.add(new StudentGrade());
            }
            studentGradeList.addAll(newRows);
        }
        
        fireTableDataChanged();
    }
}
