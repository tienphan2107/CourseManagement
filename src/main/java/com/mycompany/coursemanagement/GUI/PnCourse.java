package com.mycompany.coursemanagement.GUI;

import com.mycompany.coursemanagement.BUS.CourseBUS;
import com.mycompany.coursemanagement.BUS.OnlineCourseBUS;
import com.mycompany.coursemanagement.BUS.OnsiteCourseBUS;
import com.mycompany.coursemanagement.Models.Course;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PnCourse extends javax.swing.JPanel {

    private final CourseBUS courseBUS = new CourseBUS();
    private final OnlineCourseBUS onlineCourseBUS = new OnlineCourseBUS();
    private final OnsiteCourseBUS onsiteCourseBUS = new OnsiteCourseBUS();
    private final DefaultTableCellRenderer cellRightRenderer = new DefaultTableCellRenderer();
    private ArrayList<Course> data;
    private DefaultTableModel tableModel;
    private String query;

    public PnCourse() {
        initComponents();
        this.setSize(885, 515);
        initTable();
        this.data = new ArrayList<>();
        this.query = "";
        getCourseList(query);
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    private void initTable() {
        this.tableModel = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "Name", "Credits", "Type", "Department"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        tbCourse.setModel(tableModel);
        tbCourse.getColumnModel().getColumn(0).setPreferredWidth(80);
        tbCourse.getColumnModel().getColumn(1).setPreferredWidth(200);
        tbCourse.getColumnModel().getColumn(2).setPreferredWidth(50);
        tbCourse.getColumnModel().getColumn(4).setPreferredWidth(180);
        cellRightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
    }

    private void handleViewCourseDetail(int row) {
        Course course = data.get(row);
        if (course.getOnsiteCourse().getDays() == null) {
            DialogCourse dialog = new DialogCourse(this, (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this), true, "view", course, "online");
            dialog.setVisible(true);
        } else {
            DialogCourse dialog = new DialogCourse(this, (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this), true, "view", course, "onsite");
            dialog.setVisible(true);
        }
    }

    private void handleEditCourse(int row) {
        Course course = data.get(row);
        if (course.getOnsiteCourse().getDays() == null) {
            DialogCourse dialog = new DialogCourse(this, (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this), true, "edit", course, "online");
            dialog.setVisible(true);
        } else {
            DialogCourse dialog = new DialogCourse(this, (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this), true, "edit", course, "onsite");
            dialog.setVisible(true);
        }
    }

    private void handleDeleteCourse(int row) {
        Course course = data.get(row);
        int courseId = course.getCourseID();
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Delete '" + course.getTitle() + "' (" + course.getCourseID() + ")?",
                "Course",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int result = 0;
            try {
                if (course.getOnsiteCourse().getDays() == null) {
                    result = onlineCourseBUS.deleteOnlineCourse(courseId);
                } else {
                    result = onsiteCourseBUS.deleteOnsiteCourse(courseId);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Connection error", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
                return;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "An error occured, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
                return;
            }

            if (result <= 0) {
                JOptionPane.showMessageDialog(this, "An error occured while deleting this course.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            getCourseList(query);
        }
    }

    protected void getCourseList(String query) {
        ArrayList<Course> result = new ArrayList<>();
        try {
            result = courseBUS.getCourseList(query);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Connection error", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occured, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }
        this.data = result;
        tableModel.setRowCount(0);
        for (int i = 0; i < data.size(); ++i) {
            Course course = data.get(i);
            int courseId = course.getCourseID();
            String courseTitle = course.getTitle();
            int credits = course.getCredits();
            String type = course.getOnsiteCourse().getDays() == null ? "Online" : "Onsite";
            String departmentName = course.getDepartment().getName();
            Object[] row = {courseId, courseTitle, credits, type, departmentName};
            tableModel.addRow(row);
        }
        for (int i = 0; i < tbCourse.getColumnCount(); ++i) {
            switch (i) {
                case 0:
                case 2:
                    tbCourse.getColumnModel().getColumn(i).setCellRenderer(cellRightRenderer);
                    break;
                default:
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnReload = new javax.swing.JButton();
        btnDetail = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        tbCourse = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        txtSearch.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnReload.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnReload.setText("Reload");
        btnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadActionPerformed(evt);
            }
        });

        btnDetail.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnDetail.setText("View");
        btnDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailActionPerformed(evt);
            }
        });

        btnAdd.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        tbCourse.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        tbCourse.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Credits", "Type", "Department"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbCourse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCourseMouseClicked(evt);
            }
        });
        scrollPane.setViewportView(tbCourse);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReload)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDetail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete))
                    .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 873, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAdd)
                        .addComponent(btnDetail)
                        .addComponent(btnReload)
                        .addComponent(btnEdit)
                        .addComponent(btnDelete)))
                .addGap(6, 6, 6)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtSearch.getText().trim().toLowerCase().equals(query.toLowerCase())) {
                return;
            }
            setQuery(txtSearch.getText().trim());
            getCourseList(query);
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        if (txtSearch.getText().trim().toLowerCase().equals(query.toLowerCase())) {
            return;
        }
        setQuery(txtSearch.getText().trim());
        getCourseList(query);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void tbCourseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCourseMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2 && tbCourse.getSelectedRow() >= 0) {
            int row = tbCourse.getSelectedRow();
            handleViewCourseDetail(row);
        }
    }//GEN-LAST:event_tbCourseMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        DialogCourse dialog = new DialogCourse(this, (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this), true, "add", null, null);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        int row = tbCourse.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please choose a course");
            return;
        }
        handleEditCourse(row);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int row = tbCourse.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please choose a course");
            return;
        }
        handleDeleteCourse(row);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
        // TODO add your handling code here:
        txtSearch.setText("");
        setQuery("");
        getCourseList(query);
    }//GEN-LAST:event_btnReloadActionPerformed

    private void btnDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailActionPerformed
        // TODO add your handling code here:
        int row = tbCourse.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please choose a course");
            return;
        }
        handleViewCourseDetail(row);
    }//GEN-LAST:event_btnDetailActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDetail;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnReload;
    private javax.swing.JButton btnSearch;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable tbCourse;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
