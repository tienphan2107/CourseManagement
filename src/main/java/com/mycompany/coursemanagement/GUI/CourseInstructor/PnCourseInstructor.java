/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.coursemanagement.GUI.CourseInstructor;

import com.mycompany.coursemanagement.BUS.CourseInstructorBUS;
import com.mycompany.coursemanagement.Models.Course;
import com.mycompany.coursemanagement.Models.CourseInstructor;
import com.mycompany.coursemanagement.Models.Person;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author PC
 */
public class PnCourseInstructor extends javax.swing.JPanel {

    private CourseInstructorBUS courseInstructorBUS = new CourseInstructorBUS();
    private List<CourseInstructor> list;

    CourseInstructorDetail DetailFrame;
    AddCourseInstructor AddFrame;
    EditCourseInstructor EditFrame;
    
    public PnCourseInstructor() {
        initComponents();
        this.setSize(885, 515);
        SetUpTable();
        Resetpanel();
    }

    public void Resetpanel() {
        GetAllList();
        rdSortByTeacher.setSelected(true);
        txtFindContent.setText("");
        LoadCourseInstructor(rdSortByTeacher.isSelected(), list);
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
        tblInstructor = new javax.swing.JTable();
        txtFindContent = new javax.swing.JTextField();
        btnFind = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnView = new javax.swing.JButton();
        btnReload = new javax.swing.JButton();
        rdSortByTeacher = new javax.swing.JRadioButton();

        setPreferredSize(new java.awt.Dimension(880, 510));

        tblInstructor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Teacher ID", "Teacher Name", "Course ID", "Course Title"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
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
        jScrollPane1.setViewportView(tblInstructor);
        if (tblInstructor.getColumnModel().getColumnCount() > 0) {
            tblInstructor.getColumnModel().getColumn(0).setResizable(false);
            tblInstructor.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblInstructor.getColumnModel().getColumn(1).setResizable(false);
            tblInstructor.getColumnModel().getColumn(1).setPreferredWidth(60);
            tblInstructor.getColumnModel().getColumn(2).setResizable(false);
            tblInstructor.getColumnModel().getColumn(2).setPreferredWidth(20);
            tblInstructor.getColumnModel().getColumn(3).setResizable(false);
            tblInstructor.getColumnModel().getColumn(3).setPreferredWidth(60);
        }

        btnFind.setText("Find");
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

        rdSortByTeacher.setText("Sort By Teacher");
        rdSortByTeacher.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdSortByTeacherItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtFindContent, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFind)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdSortByTeacher, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnReload)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnView)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtFindContent)
                    .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReload, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdSortByTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
        Resetpanel();
    }//GEN-LAST:event_btnReloadActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        if (txtFindContent.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Please type something in the TextBox first.", "Message", JOptionPane.ERROR_MESSAGE);
            txtFindContent.setText("");
            txtFindContent.requestFocus();
            return;
        }
        try {
            List<CourseInstructor> resultList = courseInstructorBUS.Find(txtFindContent.getText().trim());
            LoadCourseInstructor(rdSortByTeacher.isSelected(), resultList);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occured when Finding data, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return;
        }

    }//GEN-LAST:event_btnFindActionPerformed

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
        if (tblInstructor.getSelectedRowCount() != 1) {
            JOptionPane.showMessageDialog(this, "Please choose ONE Course Instructor", "Message", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int courseID = Integer.parseInt(tblInstructor.getModel().getValueAt(tblInstructor.getSelectedRow(), 2).toString());
        int teacherID = Integer.parseInt(tblInstructor.getModel().getValueAt(tblInstructor.getSelectedRow(), 0).toString());
        if(DetailFrame != null){
            DetailFrame.dispose();
        }
        DetailFrame = new CourseInstructorDetail(courseID, teacherID);
        DetailFrame.setVisible(true);
    }//GEN-LAST:event_btnViewActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (tblInstructor.getSelectedRowCount() != 1) {
            JOptionPane.showMessageDialog(this, "Please choose ONE Course Instructor", "Message", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Xác nhận 
        int choose = JOptionPane.showConfirmDialog(this, "Delete This Course Instructor ?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (choose == JOptionPane.NO_OPTION) { // đổi ý không xóa nữa
            return;
        }

        //xóa
        int courseID = Integer.parseInt(tblInstructor.getModel().getValueAt(tblInstructor.getSelectedRow(), 2).toString());
        int teacherID = Integer.parseInt(tblInstructor.getModel().getValueAt(tblInstructor.getSelectedRow(), 0).toString());
        try {
            if (courseInstructorBUS.Delete(courseID, teacherID) > 0) {
                JOptionPane.showMessageDialog(this, "Delete Instructor Success !");
                btnReloadActionPerformed(evt);
            }
        }catch (IOException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occured when Delete Data, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return;
        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if(AddFrame != null){
            AddFrame.dispose();
        }
        AddFrame = new AddCourseInstructor();
        AddFrame.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void rdSortByTeacherItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdSortByTeacherItemStateChanged
        LoadCourseInstructor(rdSortByTeacher.isSelected(), list);
    }//GEN-LAST:event_rdSortByTeacherItemStateChanged

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if (tblInstructor.getSelectedRowCount() != 1) {
            JOptionPane.showMessageDialog(this, "Please choose ONE Course Instructor", "Message", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int courseID = Integer.parseInt(tblInstructor.getModel().getValueAt(tblInstructor.getSelectedRow(), 2).toString());
        int teacherID = Integer.parseInt(tblInstructor.getModel().getValueAt(tblInstructor.getSelectedRow(), 0).toString());
        if(EditFrame != null){
            EditFrame.dispose();
        }
        EditFrame = new EditCourseInstructor(courseID, teacherID);
        EditFrame.setVisible(true);
    }//GEN-LAST:event_btnEditActionPerformed
    public void SetUpTable() {
        for (int i = 0; i < tblInstructor.getColumnCount(); i++) {
            tblInstructor.getColumnModel().getColumn(i).setCellRenderer(new LeftAlignedCellRenderer());
        }
    }

    public void GetAllList() {
        try {
            this.list = courseInstructorBUS.Get();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occured when Get Data in Database, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return;
        }
    }

    public void LoadCourseInstructor(boolean sortByTeacher, List<CourseInstructor> list) {
        if (list == null || list.isEmpty()) {
            JOptionPane.showMessageDialog(this, "There are no CourseInstructor to be Show !");
            return;
        }
        if (sortByTeacher == true) {
            Collections.sort(list, Comparator.comparingInt(CourseInstructor::getPersonID));
        } else {
            Collections.sort(list, Comparator.comparingInt(CourseInstructor::getCourseID));
        }
        DefaultTableModel tableModel = (DefaultTableModel) tblInstructor.getModel();
        tableModel.setRowCount(0);
        try {
            for (CourseInstructor ci : list) {
                int TeacherID = ci.getPersonID();
                int CourseID = ci.getCourseID();
                Course course = courseInstructorBUS.GetCourseByID(CourseID);
                Person Teacher = courseInstructorBUS.GetTeacherByID(TeacherID);
                String TeacherName = Teacher.getFirstName() + " " + Teacher.getLastName();
                Object[] row = {TeacherID, TeacherName, CourseID, course.getTitle()};
                tableModel.addRow(row);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occured when Load Data to GUI, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            tableModel.setRowCount(0);
            return;
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
    private javax.swing.JRadioButton rdSortByTeacher;
    private javax.swing.JTable tblInstructor;
    private javax.swing.JTextField txtFindContent;
    // End of variables declaration//GEN-END:variables
}

class LeftAlignedCellRenderer extends DefaultTableCellRenderer {

    public LeftAlignedCellRenderer() {
        setHorizontalAlignment(SwingConstants.LEFT); // Đặt căn chỉnh sang trái
    }
}
