package com.mycompany.coursemanagement.GUI.Course;

import com.mycompany.coursemanagement.BUS.OnlineCourseBUS;
import com.mycompany.coursemanagement.BUS.OnsiteCourseBUS;
import com.mycompany.coursemanagement.Models.Course;
import com.mycompany.coursemanagement.Models.Department;
import helper.EmptyFieldException;
import java.awt.CardLayout;
import java.sql.SQLException;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DialogCourse extends javax.swing.JDialog {

    private final OnlineCourseBUS onlineCourseBUS = new OnlineCourseBUS();
    private final OnsiteCourseBUS onsiteCourseBUS = new OnsiteCourseBUS();
    private final String CB_VALUE_ONLINE_COURSE = "Online course";
    private final String CB_VALUE_ONSITE_COURSE = "Onsite course";
    private JPanel parent;
    private CardLayout layout;
    private String dialogMode; // view, add, edit
    private String courseType; // online, onsite
    private Course course;

    private DialogCourse(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public DialogCourse(JPanel parent, java.awt.Frame owner, boolean modal, String dialogMode, Course course, String courseType) {
        super(owner, modal);
        initComponents();
        setLocationRelativeTo(null);
        setModelComboBox();
        this.parent = parent;
        this.dialogMode = dialogMode;
        this.courseType = courseType;
        this.layout = (CardLayout) pMain.getLayout();

        if (!dialogMode.equalsIgnoreCase("add")) {
            this.course = getCourseDetail(course.getCourseID());
            if (this.course.getTitle() == null) {
                throw new IllegalArgumentException("Course info not found");
            }
            displayInfo(this.course, courseType);
        }

        enableAllInputs(dialogMode);
        setFormVisibility(dialogMode, courseType);
    }

    private void displayInfo(Course course, String courseType) {
        if (courseType.equalsIgnoreCase("online")) {
            pOnlineCourse.getTxtCourseId().setText(String.valueOf(course.getCourseID()));
            pOnlineCourse.getTxtTitle().setText(course.getTitle());
            pOnlineCourse.getTxtCredits().setText(String.valueOf(course.getCredits()));
            pOnlineCourse.setDepartment(course.getDepartment());
            pOnlineCourse.getTxtDepartment().setText(course.getDepartment().getName());
            pOnlineCourse.getTxtUrl().setText(course.getOnlineCourse().getUrl());
        } else if (courseType.equalsIgnoreCase("onsite")) {
            pOnsiteCourse.getTxtCourseId().setText(String.valueOf(course.getCourseID()));
            pOnsiteCourse.getTxtTitle().setText(course.getTitle());
            pOnsiteCourse.getTxtCredits().setText(String.valueOf(course.getCredits()));
            pOnsiteCourse.setDepartment(course.getDepartment());
            pOnsiteCourse.getTxtDepartment().setText(course.getDepartment().getName());
            pOnsiteCourse.getTxtLocation().setText(course.getOnsiteCourse().getLocation());

            String days = course.getOnsiteCourse().getDays();
            pOnsiteCourse.getCbMonday().setSelected(days.contains("M"));
            pOnsiteCourse.getCbTuesday().setSelected(days.contains("T"));
            pOnsiteCourse.getCbWednesday().setSelected(days.contains("W"));
            pOnsiteCourse.getCbThursday().setSelected(days.contains("H"));
            pOnsiteCourse.getCbFriday().setSelected(days.contains("F"));
            pOnsiteCourse.getCbSaturday().setSelected(days.contains("S"));

            LocalTime time = course.getOnsiteCourse().getLocalTime();
            pOnsiteCourse.setTime(time.getHour(), time.getMinute());
        }
    }

    private void setFormVisibility(String dialogMode, String courseType) {
        if (dialogMode.equalsIgnoreCase("view")) {
            lblDialogTitle.setText("Course detail");
            pOnlineCourse.getBtnChooseDepartment().setVisible(false);
            pOnsiteCourse.getBtnChooseDepartment().setVisible(false);
            btnBack1.setVisible(false);
            btnBack2.setVisible(false);
            btnConfirmActionOnlineCourse.setText("Close");
            btnConfirmActionOnsiteCourse.setText("Close");
            if (courseType.equalsIgnoreCase("online")) {
                layout.show(pMain, "pOnlineCourseInfo");
                btnConfirmActionOnlineCourse.requestFocus();
            } else if (courseType.equalsIgnoreCase("onsite")) {
                layout.show(pMain, "pOnsiteCourseInfo");
                btnConfirmActionOnsiteCourse.requestFocus();
            }
        } else if (dialogMode.equalsIgnoreCase("add")) {
            lblDialogTitle.setText("Add course");
            layout.show(pMain, "pChooseType");
        } else if (dialogMode.equalsIgnoreCase("edit")) {
            lblDialogTitle.setText("Edit course");
            btnBack1.setVisible(false);
            btnBack2.setVisible(false);
            if (courseType.equalsIgnoreCase("online")) {
                layout.show(pMain, "pOnlineCourseInfo");
                pOnlineCourse.getTxtTitle().requestFocus();
            } else if (courseType.equalsIgnoreCase("onsite")) {
                layout.show(pMain, "pOnsiteCourseInfo");
                pOnsiteCourse.getTxtTitle().requestFocus();
            }
        }
    }

    private void setModelComboBox() {
        cbCourseType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{CB_VALUE_ONLINE_COURSE, CB_VALUE_ONSITE_COURSE}));
        cbCourseType.setSelectedIndex(0);
    }

    private void enableAllInputs(String dialogMode) {
        if (dialogMode.equalsIgnoreCase("view")) {
            return;
        } else if (dialogMode.equalsIgnoreCase("add")) {
            pOnlineCourse.getTxtCourseId().setEditable(true);
            pOnsiteCourse.getTxtCourseId().setEditable(true);
        }
        pOnlineCourse.getTxtTitle().setEditable(true);
        pOnlineCourse.getTxtCredits().setEditable(true);
        pOnlineCourse.getBtnChooseDepartment().setEnabled(true);
        pOnlineCourse.getTxtUrl().setEditable(true);

        pOnsiteCourse.getTxtTitle().setEditable(true);
        pOnsiteCourse.getTxtCredits().setEditable(true);
        pOnsiteCourse.getBtnChooseDepartment().setEnabled(true);
        pOnsiteCourse.getTxtLocation().setEditable(true);
        pOnsiteCourse.getCbMonday().setEnabled(true);
        pOnsiteCourse.getCbTuesday().setEnabled(true);
        pOnsiteCourse.getCbWednesday().setEnabled(true);
        pOnsiteCourse.getCbThursday().setEnabled(true);
        pOnsiteCourse.getCbFriday().setEnabled(true);
        pOnsiteCourse.getCbSaturday().setEnabled(true);
        pOnsiteCourse.getSpinnerTime().setEnabled(true);
    }

    private Course getCourseDetail(int id) {
        Course c = new Course();
        try {
            if (courseType.equalsIgnoreCase("online")) {
                c = onlineCourseBUS.getOnlineCourseDetail(id);
            } else if (courseType.equalsIgnoreCase("onsite")) {
                c = onsiteCourseBUS.getOnsiteCourseDetail(id);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Connection error", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return c;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return c;
        }
        return c;
    }

    private void handleAddOnlineCourse() {
        String courseId = pOnlineCourse.getTxtCourseId().getText().trim();
        String title = pOnlineCourse.getTxtTitle().getText().trim();
        String credits = pOnlineCourse.getTxtCredits().getText().trim();
        Department department = pOnlineCourse.getDepartment();
        String url = pOnlineCourse.getTxtUrl().getText().trim();
        int result;
        try {
            result = onlineCourseBUS.addCourse(courseId, title, credits, department, url);
        } catch (EmptyFieldException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            if (e.getFieldName().equalsIgnoreCase("courseId")) {
                pOnlineCourse.getTxtCourseId().requestFocus();
            } else if (e.getFieldName().equalsIgnoreCase("title")) {
                pOnlineCourse.getTxtTitle().requestFocus();
            } else if (e.getFieldName().equalsIgnoreCase("credits")) {
                pOnlineCourse.getTxtCredits().requestFocus();
            } else if (e.getFieldName().equalsIgnoreCase("department")) {
                pOnlineCourse.getBtnChooseDepartment().requestFocus();
            } else if (e.getFieldName().equalsIgnoreCase("url")) {
                pOnlineCourse.getTxtUrl().requestFocus();
            }
            return;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            return;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Connection error", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occured, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }

        if (result > 0) {
            pOnlineCourse.clearAllInput();
            if (parent instanceof PnCourse) {
                PnCourse p = (PnCourse) parent;
                p.getCourseList(p.getQuery());
            }
            JOptionPane.showMessageDialog(this, "Course '" + title + "' (" + courseId + ") successfully added");
        } else {
            JOptionPane.showMessageDialog(this, "An error occured, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleAddOnsiteCourse() {
        String courseId = pOnsiteCourse.getTxtCourseId().getText().trim();
        String title = pOnsiteCourse.getTxtTitle().getText().trim();
        String credits = pOnsiteCourse.getTxtCredits().getText().trim();
        Department department = pOnsiteCourse.getDepartment();
        String location = pOnsiteCourse.getTxtLocation().getText().trim();
        boolean[] days = {pOnsiteCourse.getCbMonday().isSelected(), pOnsiteCourse.getCbTuesday().isSelected(),
            pOnsiteCourse.getCbWednesday().isSelected(), pOnsiteCourse.getCbThursday().isSelected(),
            pOnsiteCourse.getCbFriday().isSelected(), pOnsiteCourse.getCbSaturday().isSelected()};
        int hour = pOnsiteCourse.getHour();
        int minute = pOnsiteCourse.getMinute();
        int result;
        try {
            result = onsiteCourseBUS.addCourse(courseId, title, credits, department, location, days, hour, minute);
        } catch (EmptyFieldException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            if (e.getFieldName().equalsIgnoreCase("courseId")) {
                pOnsiteCourse.getTxtCourseId().requestFocus();
            } else if (e.getFieldName().equalsIgnoreCase("title")) {
                pOnsiteCourse.getTxtTitle().requestFocus();
            } else if (e.getFieldName().equalsIgnoreCase("credits")) {
                pOnsiteCourse.getTxtCredits().requestFocus();
            } else if (e.getFieldName().equalsIgnoreCase("department")) {
                pOnsiteCourse.getBtnChooseDepartment().requestFocus();
            } else if (e.getFieldName().equalsIgnoreCase("location")) {
                pOnsiteCourse.getTxtLocation().requestFocus();
            } else if (e.getFieldName().equalsIgnoreCase("days")) {
                pOnsiteCourse.getCbMonday().requestFocus();
            } else if (e.getFieldName().equalsIgnoreCase("time")) {
                pOnsiteCourse.getSpinnerTime().requestFocus();
            }
            return;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            return;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Connection error", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occured, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }

        if (result > 0) {
            pOnsiteCourse.clearAllInput();
            if (parent instanceof PnCourse) {
                PnCourse p = (PnCourse) parent;
                p.getCourseList(p.getQuery());
            }
            JOptionPane.showMessageDialog(this, "Course '" + title + "' (" + courseId + ") successfully added");
        } else {
            JOptionPane.showMessageDialog(this, "An error occured, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleEditOnlineCourse() {
        String courseId = pOnlineCourse.getTxtCourseId().getText().trim();
        String title = pOnlineCourse.getTxtTitle().getText().trim();
        String credits = pOnlineCourse.getTxtCredits().getText().trim();
        Department department = pOnlineCourse.getDepartment();
        String url = pOnlineCourse.getTxtUrl().getText().trim();
        int result;
        try {
            result = onlineCourseBUS.editCourse(this.course, courseId, title, credits, department, url);
        } catch (EmptyFieldException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            if (e.getFieldName().equalsIgnoreCase("courseId")) {
                pOnlineCourse.getTxtCourseId().requestFocus();
            } else if (e.getFieldName().equalsIgnoreCase("title")) {
                pOnlineCourse.getTxtTitle().requestFocus();
            } else if (e.getFieldName().equalsIgnoreCase("credits")) {
                pOnlineCourse.getTxtCredits().requestFocus();
            } else if (e.getFieldName().equalsIgnoreCase("department")) {
                pOnlineCourse.getBtnChooseDepartment().requestFocus();
            } else if (e.getFieldName().equalsIgnoreCase("url")) {
                pOnlineCourse.getTxtUrl().requestFocus();
            }
            return;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            return;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Connection error", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        } catch (Exception e) {
            if (e.getMessage().equalsIgnoreCase("Form unchanged")) {
                return;
            }
            JOptionPane.showMessageDialog(this, "An error occured, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }

        if (result > 0) {
            this.course = getCourseDetail(Integer.parseInt(courseId));
            if (parent instanceof PnCourse) {
                PnCourse p = (PnCourse) parent;
                p.getCourseList(p.getQuery());
            }
            JOptionPane.showMessageDialog(this, "Course '" + title + "' (" + courseId + ") successfully modified");
        } else {
            JOptionPane.showMessageDialog(this, "An error occured, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleEditOnsiteCourse() {
        String courseId = pOnsiteCourse.getTxtCourseId().getText().trim();
        String title = pOnsiteCourse.getTxtTitle().getText().trim();
        String credits = pOnsiteCourse.getTxtCredits().getText().trim();
        Department department = pOnsiteCourse.getDepartment();
        String location = pOnsiteCourse.getTxtLocation().getText().trim();
        boolean[] days = {pOnsiteCourse.getCbMonday().isSelected(), pOnsiteCourse.getCbTuesday().isSelected(),
            pOnsiteCourse.getCbWednesday().isSelected(), pOnsiteCourse.getCbThursday().isSelected(),
            pOnsiteCourse.getCbFriday().isSelected(), pOnsiteCourse.getCbSaturday().isSelected()};
        int hour = pOnsiteCourse.getHour();
        int minute = pOnsiteCourse.getMinute();
        int result;
        try {
            result = onsiteCourseBUS.editCourse(this.course, courseId, title, credits, department, location, days, hour, minute);
        } catch (EmptyFieldException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            if (e.getFieldName().equalsIgnoreCase("courseId")) {
                pOnsiteCourse.getTxtCourseId().requestFocus();
            } else if (e.getFieldName().equalsIgnoreCase("title")) {
                pOnsiteCourse.getTxtTitle().requestFocus();
            } else if (e.getFieldName().equalsIgnoreCase("credits")) {
                pOnsiteCourse.getTxtCredits().requestFocus();
            } else if (e.getFieldName().equalsIgnoreCase("department")) {
                pOnsiteCourse.getBtnChooseDepartment().requestFocus();
            } else if (e.getFieldName().equalsIgnoreCase("location")) {
                pOnsiteCourse.getTxtLocation().requestFocus();
            } else if (e.getFieldName().equalsIgnoreCase("days")) {
                pOnsiteCourse.getCbMonday().requestFocus();
            } else if (e.getFieldName().equalsIgnoreCase("time")) {
                pOnsiteCourse.getSpinnerTime().requestFocus();
            }
            return;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            return;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Connection error", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        } catch (Exception e) {
            if (e.getMessage().equalsIgnoreCase("Form unchanged")) {
                return;
            }
            JOptionPane.showMessageDialog(this, "An error occured, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }

        if (result > 0) {
            this.course = getCourseDetail(Integer.parseInt(courseId));
            if (parent instanceof PnCourse) {
                PnCourse p = (PnCourse) parent;
                p.getCourseList(p.getQuery());
            }
            JOptionPane.showMessageDialog(this, "Course '" + title + "' (" + courseId + ") successfully modified");
        } else {
            JOptionPane.showMessageDialog(this, "An error occured, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
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

        pHeader = new javax.swing.JPanel();
        lblDialogTitle = new javax.swing.JLabel();
        pMain = new javax.swing.JPanel();
        pChooseType = new javax.swing.JPanel();
        lblChooseType = new javax.swing.JLabel();
        cbCourseType = new javax.swing.JComboBox<>();
        btnGoToCourseInfo = new javax.swing.JButton();
        pOnlineCourseInfo = new javax.swing.JPanel();
        pOnlineCourse = new com.mycompany.coursemanagement.GUI.Course.PnOnlineCourse();
        btnBack1 = new javax.swing.JButton();
        btnConfirmActionOnlineCourse = new javax.swing.JButton();
        pOnsiteCourseInfo = new javax.swing.JPanel();
        pOnsiteCourse = new com.mycompany.coursemanagement.GUI.Course.PnOnsiteCourse();
        btnBack2 = new javax.swing.JButton();
        btnConfirmActionOnsiteCourse = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Course");
        setResizable(false);

        pHeader.setBackground(new java.awt.Color(204, 255, 255));
        pHeader.setPreferredSize(new java.awt.Dimension(500, 60));
        pHeader.setLayout(new java.awt.BorderLayout());

        lblDialogTitle.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblDialogTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDialogTitle.setText("[title]");
        lblDialogTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pHeader.add(lblDialogTitle, java.awt.BorderLayout.CENTER);

        getContentPane().add(pHeader, java.awt.BorderLayout.NORTH);

        pMain.setBackground(new java.awt.Color(255, 255, 255));
        pMain.setPreferredSize(new java.awt.Dimension(500, 384));
        pMain.setLayout(new java.awt.CardLayout());

        pChooseType.setBackground(new java.awt.Color(255, 255, 255));
        pChooseType.setPreferredSize(new java.awt.Dimension(500, 510));
        pChooseType.setRequestFocusEnabled(false);

        lblChooseType.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblChooseType.setText("Choose course type:");

        cbCourseType.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        btnGoToCourseInfo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btnGoToCourseInfo.setText("Next");
        btnGoToCourseInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoToCourseInfoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pChooseTypeLayout = new javax.swing.GroupLayout(pChooseType);
        pChooseType.setLayout(pChooseTypeLayout);
        pChooseTypeLayout.setHorizontalGroup(
            pChooseTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pChooseTypeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGoToCourseInfo)
                .addContainerGap())
            .addGroup(pChooseTypeLayout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(pChooseTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbCourseType, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblChooseType))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        pChooseTypeLayout.setVerticalGroup(
            pChooseTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pChooseTypeLayout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addComponent(lblChooseType)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbCourseType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
                .addComponent(btnGoToCourseInfo)
                .addContainerGap())
        );

        pMain.add(pChooseType, "pChooseType");

        pOnlineCourseInfo.setBackground(new java.awt.Color(255, 255, 255));
        pOnlineCourseInfo.setPreferredSize(new java.awt.Dimension(500, 510));
        pOnlineCourseInfo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pOnlineCourseInfo.add(pOnlineCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 500, 250));

        btnBack1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btnBack1.setText("Back");
        btnBack1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack1ActionPerformed(evt);
            }
        });
        pOnlineCourseInfo.add(btnBack1, new org.netbeans.lib.awtextra.AbsoluteConstraints(344, 353, -1, -1));

        btnConfirmActionOnlineCourse.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btnConfirmActionOnlineCourse.setText("Save");
        btnConfirmActionOnlineCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionOnlineCourseActionPerformed(evt);
            }
        });
        pOnlineCourseInfo.add(btnConfirmActionOnlineCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(422, 353, -1, -1));

        pMain.add(pOnlineCourseInfo, "pOnlineCourseInfo");

        pOnsiteCourseInfo.setBackground(new java.awt.Color(255, 255, 255));
        pOnsiteCourseInfo.setPreferredSize(new java.awt.Dimension(500, 510));
        pOnsiteCourseInfo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pOnsiteCourseInfo.add(pOnsiteCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, -1));

        btnBack2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btnBack2.setText("Back");
        btnBack2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack2ActionPerformed(evt);
            }
        });
        pOnsiteCourseInfo.add(btnBack2, new org.netbeans.lib.awtextra.AbsoluteConstraints(344, 356, -1, -1));

        btnConfirmActionOnsiteCourse.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btnConfirmActionOnsiteCourse.setText("Save");
        btnConfirmActionOnsiteCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionOnsiteCourseActionPerformed(evt);
            }
        });
        pOnsiteCourseInfo.add(btnConfirmActionOnsiteCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(422, 356, -1, -1));

        pMain.add(pOnsiteCourseInfo, "pOnsiteCourseInfo");

        getContentPane().add(pMain, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGoToCourseInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoToCourseInfoActionPerformed
        // TODO add your handling code here:
        switch (String.valueOf(cbCourseType.getSelectedItem())) {
            case CB_VALUE_ONLINE_COURSE:
                layout.show(pMain, "pOnlineCourseInfo");
                pOnlineCourse.getTxtCourseId().requestFocus();
                break;
            case CB_VALUE_ONSITE_COURSE:
                layout.show(pMain, "pOnsiteCourseInfo");
                pOnsiteCourse.getTxtCourseId().requestFocus();
                break;
            default:
        }
    }//GEN-LAST:event_btnGoToCourseInfoActionPerformed

    private void btnBack1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack1ActionPerformed
        // TODO add your handling code here:
        layout.show(pMain, "pChooseType");
    }//GEN-LAST:event_btnBack1ActionPerformed

    private void btnBack2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack2ActionPerformed
        // TODO add your handling code here:
        layout.show(pMain, "pChooseType");
    }//GEN-LAST:event_btnBack2ActionPerformed

    private void btnConfirmActionOnlineCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionOnlineCourseActionPerformed
        // TODO add your handling code here:
        if (dialogMode.equalsIgnoreCase("view")) {
            this.dispose();
        } else if (dialogMode.equalsIgnoreCase("add")) {
            handleAddOnlineCourse();
        } else if (dialogMode.equalsIgnoreCase("edit")) {
            handleEditOnlineCourse();
        }
    }//GEN-LAST:event_btnConfirmActionOnlineCourseActionPerformed

    private void btnConfirmActionOnsiteCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionOnsiteCourseActionPerformed
        // TODO add your handling code here:
        if (dialogMode.equalsIgnoreCase("view")) {
            this.dispose();
        } else if (dialogMode.equalsIgnoreCase("add")) {
            handleAddOnsiteCourse();
        } else if (dialogMode.equalsIgnoreCase("edit")) {
            handleEditOnsiteCourse();
        }
    }//GEN-LAST:event_btnConfirmActionOnsiteCourseActionPerformed

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
            java.util.logging.Logger.getLogger(DialogCourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogCourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogCourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogCourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogCourse dialog = new DialogCourse(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack1;
    private javax.swing.JButton btnBack2;
    private javax.swing.JButton btnConfirmActionOnlineCourse;
    private javax.swing.JButton btnConfirmActionOnsiteCourse;
    private javax.swing.JButton btnGoToCourseInfo;
    private javax.swing.JComboBox<String> cbCourseType;
    private javax.swing.JLabel lblChooseType;
    private javax.swing.JLabel lblDialogTitle;
    private javax.swing.JPanel pChooseType;
    private javax.swing.JPanel pHeader;
    private javax.swing.JPanel pMain;
    private com.mycompany.coursemanagement.GUI.Course.PnOnlineCourse pOnlineCourse;
    private javax.swing.JPanel pOnlineCourseInfo;
    private com.mycompany.coursemanagement.GUI.Course.PnOnsiteCourse pOnsiteCourse;
    private javax.swing.JPanel pOnsiteCourseInfo;
    // End of variables declaration//GEN-END:variables
}
