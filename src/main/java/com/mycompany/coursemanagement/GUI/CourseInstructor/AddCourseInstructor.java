/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.coursemanagement.GUI.CourseInstructor;

import com.mycompany.coursemanagement.BUS.CourseBUS;
import com.mycompany.coursemanagement.BUS.CourseInstructorBUS;
import com.mycompany.coursemanagement.BUS.PersonBUS;
import com.mycompany.coursemanagement.Models.Course;
import com.mycompany.coursemanagement.Models.CourseInstructor;
import com.mycompany.coursemanagement.Models.Person;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer
 */
public class AddCourseInstructor extends javax.swing.JFrame {

    /**
     * Creates new form AddCourseInstructor
     */
    private List<CourseInstructor> list;
    private CourseInstructorBUS courseInstructorBUS = new CourseInstructorBUS();
    private PersonBUS personBUS = new PersonBUS();
    private List<Course> courseList;
    private List<Person> teacherList;

    public AddCourseInstructor() {
        initComponents();
        setLocationRelativeTo(null);
        LoadValueComponent();
    }

    public void LoadValueComponent() {
        try {
            teacherList = personBUS.GetAllTeacher();
            courseList = new CourseBUS().getCourseList("");

            for (Course course : courseList) {
                cbbCourseID.addItem(course.getCourseID() + "");
                cbbTitle.addItem(course.getTitle());
            }
            LoadValueTextField();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occured , please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return;
        }
    }

    public void LoadValueTextField() {
        try {
            Course course = courseInstructorBUS.GetCourseByID(Integer.parseInt(cbbCourseID.getSelectedItem().toString()));
            Person teacher = courseInstructorBUS.GetTeacherByID(Integer.parseInt(cbbTeacherID.getSelectedItem().toString()));
            txtCredits.setText(course.getCredits() + "");
            txtDepartmentID.setText(course.getDepartmentID() + "");
            txtLastName.setText(teacher.getLastName());
            txtHireDate.setText(teacher.getHireDate().toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occured , please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return;
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbbCourseID = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbbTitle = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cbbTeacherID = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cbbFirstName = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtCredits = new javax.swing.JTextField();
        txtDepartmentID = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        txtHireDate = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add Course Instructor");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Add Course Instructor");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Course");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Teacher");

        jLabel4.setText("Course ID:");

        cbbCourseID.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbCourseIDItemStateChanged(evt);
            }
        });

        jLabel6.setText("Credits:");

        cbbTitle.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbTitleItemStateChanged(evt);
            }
        });

        jLabel7.setText("Teacher ID:");

        cbbTeacherID.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbTeacherIDItemStateChanged(evt);
            }
        });

        jLabel8.setText("First Name:");

        cbbFirstName.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbFirstNameItemStateChanged(evt);
            }
        });

        jLabel9.setText("Title:");

        jLabel10.setText("Department ID:");

        jLabel11.setText("Last Name:");

        jLabel12.setText("Hire Date:");

        txtCredits.setEditable(false);
        txtCredits.setPreferredSize(new java.awt.Dimension(156, 36));

        txtDepartmentID.setEditable(false);
        txtDepartmentID.setPreferredSize(new java.awt.Dimension(156, 36));

        txtLastName.setEditable(false);
        txtLastName.setPreferredSize(new java.awt.Dimension(156, 36));

        txtHireDate.setEditable(false);
        txtHireDate.setPreferredSize(new java.awt.Dimension(156, 36));

        btnSave.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnExit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                                .addComponent(cbbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbCourseID, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtDepartmentID, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                                        .addComponent(txtCredits, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtLastName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbTeacherID, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHireDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(10, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(139, 139, 139))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbCourseID, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbTeacherID, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCredits, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtLastName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDepartmentID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtHireDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void loadCbbTeacherForEachCourse(int courseID) {
        cbbTeacherID.removeAllItems();
        cbbFirstName.removeAllItems();
        try {
            if (courseInstructorBUS.GetTeachersDidNotTeachThisCourse(courseID).isEmpty()) {
                for (Person p : teacherList) {
                    cbbTeacherID.addItem(p.getPersonID() + "");
                    cbbFirstName.addItem(p.getFirstName());
                }
            } else {
                for (Person p : courseInstructorBUS.GetTeachersDidNotTeachThisCourse(courseID)) {
                    cbbTeacherID.addItem(p.getPersonID() + "");
                    cbbFirstName.addItem(p.getFirstName());
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occured , please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return;
        }

    }
    private void cbbCourseIDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbCourseIDItemStateChanged
        if (cbbTitle.getItemCount() <= 0) {
            return;
        }
        try {
            Course course = courseInstructorBUS.GetCourseByID(Integer.parseInt(cbbCourseID.getSelectedItem().toString()));
            txtCredits.setText(course.getCredits() + "");
            txtDepartmentID.setText(course.getDepartmentID() + "");
            if (cbbTitle.getSelectedIndex() != cbbCourseID.getSelectedIndex()) {
                cbbTitle.setSelectedIndex(cbbCourseID.getSelectedIndex());
            }
            loadCbbTeacherForEachCourse(course.getCourseID()); // hàm này load lại ds gvien dạy được môn đó
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occured , please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return;
        }
    }//GEN-LAST:event_cbbCourseIDItemStateChanged

    private void cbbTitleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbTitleItemStateChanged
        if (cbbCourseID.getItemCount() <= 0) {
            return;
        }
        //Course course = new Course();
        try {

            if (cbbTitle.getSelectedIndex() != cbbCourseID.getSelectedIndex()) {
                cbbCourseID.setSelectedIndex(cbbTitle.getSelectedIndex());
            }// nếu như title trùng khớp ròi thì không cần thay đổi courseID lại
            Course course = courseInstructorBUS.GetCourseByID(Integer.parseInt(cbbCourseID.getSelectedItem().toString()));
            loadCbbTeacherForEachCourse(course.getCourseID()); // hàm này load lại ds gvien dạy được môn đó
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occured , please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return;
        }
    }//GEN-LAST:event_cbbTitleItemStateChanged

    private void cbbTeacherIDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbTeacherIDItemStateChanged

        if(cbbFirstName.getItemCount()<1 || cbbTeacherID.getItemCount() < 1){
            return;
        }
        String firstName = cbbFirstName.getSelectedItem().toString();
        try {
            Person teacher = courseInstructorBUS.GetTeacherByID(Integer.parseInt(cbbTeacherID.getSelectedItem().toString()));
            txtLastName.setText(teacher.getLastName());
            txtHireDate.setText(teacher.getHireDate().toString());
            if (cbbFirstName.getSelectedIndex() != cbbTeacherID.getSelectedIndex()) {
                cbbFirstName.setSelectedIndex(cbbTeacherID.getSelectedIndex());

            }// nêú trùng rồi không cần load lại bên cbb 
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occured , please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return;
        }
    }//GEN-LAST:event_cbbTeacherIDItemStateChanged

    private void cbbFirstNameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbFirstNameItemStateChanged

        if(cbbTeacherID.getItemCount()<1 || cbbFirstName.getItemCount()<1){
            return;
        }
        int teacherID = Integer.parseInt(cbbTeacherID.getSelectedItem().toString());
        try {
            Person teacher = new Person();
            for (Person p : this.teacherList) {
                if (p.getFirstName().equals(cbbFirstName.getSelectedItem().toString())) {
                    teacher = p;
                    break;
                }
            }
            txtLastName.setText(teacher.getLastName());
            txtHireDate.setText(teacher.getHireDate().toString());
            if (cbbTeacherID.getSelectedIndex() != cbbFirstName.getSelectedIndex()) {
                cbbTeacherID.setSelectedIndex(cbbFirstName.getSelectedIndex());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occured , please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return;
        }
    }//GEN-LAST:event_cbbFirstNameItemStateChanged

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

        //Xác nhận 
        int choose = JOptionPane.NO_OPTION;
        choose = JOptionPane.showConfirmDialog(this, "Add This Course Instructor ?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (choose == JOptionPane.NO_OPTION) { // đổi ý không xóa nữa
            return;
        }

        //Thêm
        int courseID = Integer.parseInt(cbbCourseID.getSelectedItem().toString());
        int teacherID = Integer.parseInt(cbbTeacherID.getSelectedItem().toString());
        try {
            if (courseInstructorBUS.Add(new CourseInstructor(courseID, teacherID)) > 0) {
                JOptionPane.showMessageDialog(this, "Add Instructor Success !");
            }
            this.dispose();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occured when Add Data, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return;
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddCourseInstructor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddCourseInstructor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddCourseInstructor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddCourseInstructor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddCourseInstructor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cbbCourseID;
    private javax.swing.JComboBox<String> cbbFirstName;
    private javax.swing.JComboBox<String> cbbTeacherID;
    private javax.swing.JComboBox<String> cbbTitle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtCredits;
    private javax.swing.JTextField txtDepartmentID;
    private javax.swing.JTextField txtHireDate;
    private javax.swing.JTextField txtLastName;
    // End of variables declaration//GEN-END:variables
}
