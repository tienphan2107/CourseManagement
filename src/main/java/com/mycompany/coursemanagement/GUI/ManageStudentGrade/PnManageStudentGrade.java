/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.coursemanagement.GUI.ManageStudentGrade;


import com.mycompany.coursemanagement.BUS.StudentGradeBUS;
import com.mycompany.coursemanagement.Models.StudentGrade;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class PnManageStudentGrade extends javax.swing.JPanel {
    
    private StudentGradeBUS studentGradeBUS = new StudentGradeBUS();
    private List<StudentGrade> list;
    private final DefaultTableCellRenderer cellRightRenderer = new DefaultTableCellRenderer();
    private DefaultTableModel tableModel;
    
    EditManageStudentGrade EditFrame;
    DetailManageStudentGrade DetailFrame;

    /**
     * Creates new form ManageStudentGrade
     */
    public PnManageStudentGrade() {
        initComponents();
        this.setSize(885, 515);
        SetUpTable();
        Resetpanel();
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblGrade = new javax.swing.JTable();
        txtFindContent = new javax.swing.JTextField();
        btnFind = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnView = new javax.swing.JButton();
        btnReload = new javax.swing.JButton();

        tblGrade.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "EnrollmentID", "CourseID", "StudentID", "Grade"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblGrade);
        if (tblGrade.getColumnModel().getColumnCount() > 0) {
            tblGrade.getColumnModel().getColumn(0).setResizable(false);
        }

        btnFind.setText("Search");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnView.setText("View");
        btnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewActionPerformed(evt);
            }
        });

        btnReload.setText("Reload");
        btnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 880, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtFindContent, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnFind)
                            .addGap(160, 160, 160)
                            .addComponent(btnReload)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnView)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnAdd)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnEdit)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnDelete)))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtFindContent)
                        .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnReload, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        if (txtFindContent.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Please type something in the TextBox first.", "Message", JOptionPane.ERROR_MESSAGE);
            txtFindContent.setText("");
            txtFindContent.requestFocus();
            return;
        }
//        try {
//            List<CourseInstructor> resultList = courseInstructorBUS.Find(txtFindContent.getText().trim());
//            LoadCourseInstructor(rdSortByTeacher.isSelected(), resultList);
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(this, "An error occured when Finding data, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
//            ex.printStackTrace();
//            return;
//        }
    }//GEN-LAST:event_btnFindActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (tblGrade.getSelectedRowCount() != 1) {
            JOptionPane.showMessageDialog(this, "Please choose ONE Student Grade", "Message", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Xác nhận
        int choose = JOptionPane.showConfirmDialog(this, "Delete This Student Grade ?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (choose == JOptionPane.NO_OPTION) { // đổi ý không xóa nữa
            return;
        }

        //xóa
        int courseID = Integer.parseInt(tblGrade.getModel().getValueAt(tblGrade.getSelectedRow(), 1).toString());
        int studentID = Integer.parseInt(tblGrade.getModel().getValueAt(tblGrade.getSelectedRow(), 2).toString());
        try {
            if (studentGradeBUS.Delete(courseID, studentID) > 0) {
                JOptionPane.showMessageDialog(this, "Delete Grade Success !");
                btnReloadActionPerformed(evt);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occured when Delete Data, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return;
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if (tblGrade.getSelectedRowCount() != 1) {
            JOptionPane.showMessageDialog(this, "Please choose ONE Student Grade", "Message", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int enrollmentID = Integer.parseInt(tblGrade.getModel().getValueAt(tblGrade.getSelectedRow(), 0).toString());

        EditFrame = new EditManageStudentGrade(enrollmentID);
        EditFrame.setVisible(true);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
//        AddFrame = new AddCourseInstructor();
//        AddFrame.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
        if (tblGrade.getSelectedRowCount() != 1) {
            JOptionPane.showMessageDialog(this, "Please choose ONE Student Grade", "Message", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int enrollmentID = Integer.parseInt(tblGrade.getModel().getValueAt(tblGrade.getSelectedRow(), 0).toString());
        
        DetailFrame = new DetailManageStudentGrade(enrollmentID);
        DetailFrame.setVisible(true);
    }//GEN-LAST:event_btnViewActionPerformed

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
        Resetpanel();
    }//GEN-LAST:event_btnReloadActionPerformed

    private void Resetpanel() {
        GetAllList();
        txtFindContent.setText("");
        LoadCourseInstructor(list);
    }
    
    private void SetUpTable() {
        for (int i = 0; i < tblGrade.getColumnCount(); i++) {
            tblGrade.getColumnModel().getColumn(i).setCellRenderer(new LeftAlignedCellRenderer());
        }
    }
    
    public void GetAllList() {
        try {
            this.list = studentGradeBUS.Get();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occured when Get Data in Database, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return;
        }
    }
    
    private void LoadStudentGrades() {
        GetAllList(); // Get the list of student grades from the database
        tableModel.setRowCount(0); // Clear the existing rows in the table
        for (StudentGrade studentGrade : list) {
            Object[] rowData = {studentGrade.getEnrollmentID(), studentGrade.getCourseID(), studentGrade.getStudentID(), studentGrade.getGrade()};
            tableModel.addRow(rowData); // Add each student grade as a row in the table
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnReload;
    private javax.swing.JButton btnView;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblGrade;
    private javax.swing.JTextField txtFindContent;
    // End of variables declaration//GEN-END:variables

    private void LoadCourseInstructor(List<StudentGrade> list) {
        DefaultTableModel tableModel = (DefaultTableModel) tblGrade.getModel();
        tableModel.setRowCount(0);
        try {
            for (StudentGrade ci : list) {
                int EnrollmentID = ci.getEnrollmentID();
                int CourseID = ci.getCourseID();
//                Course course = courseInstructorBUS.GetCourseByID(CourseID);
//                Person Teacher = courseInstructorBUS.GetTeacherByID(TeacherID);
                int StudentID = ci.getStudentID();
                double GradeID = ci.getGrade();
                Object[] row = {EnrollmentID, CourseID, StudentID, GradeID};
                tableModel.addRow(row);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occured when Load Data to GUI, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            tableModel.setRowCount(0);
            return;
        }
    }
}

class LeftAlignedCellRenderer extends DefaultTableCellRenderer {

    public LeftAlignedCellRenderer() {
        setHorizontalAlignment(SwingConstants.LEFT); // Đặt căn chỉnh sang trái
    }
}