package com.mycompany.coursemanagement.GUI.Course;

import com.mycompany.coursemanagement.BUS.DepartmentBUS;
import com.mycompany.coursemanagement.Models.Department;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DialogChooseDepartment extends javax.swing.JDialog {

    private final DepartmentBUS departmentBUS = new DepartmentBUS();
    private ArrayList<Department> data;
    private DefaultTableModel tableModel;
    private String query;
    private DepartmentDialogListener listener;

    public DialogChooseDepartment(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public DialogChooseDepartment(JDialog parent, boolean modal) {
        super(getOwnerFrame(parent), modal);
        initComponents();
        initTable();
        setLocationRelativeTo(null);
        this.data = new ArrayList<>();
        this.query = "";
        getDepartmentList(query);
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setListener(DepartmentDialogListener listener) {
        this.listener = listener;
    }

    public interface DepartmentDialogListener {

        void onDepartmentChosen(Department department);
    }

    public static Frame getOwnerFrame(JDialog dialog) {
        if (dialog != null) {
            Window owner = dialog.getOwner();
            if (owner instanceof Frame) {
                return (Frame) owner;
            }
        }
        return null;
    }

    private void initTable() {
        tableModel = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "Name"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        tbDepartment.setModel(tableModel);
        tbDepartment.getColumnModel().getColumn(0).setMinWidth(0);
        tbDepartment.getColumnModel().getColumn(0).setMaxWidth(0);
        tbDepartment.getColumnModel().getColumn(0).setWidth(0);
        tbDepartment.getColumnModel().getColumn(0).setPreferredWidth(0);
    }

    private void getDepartmentList(String query) {
        ArrayList<Department> result = new ArrayList<>();
        try {
            result = departmentBUS.getDepartmentList(query);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Connection error", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }
        this.data = result;
        tableModel.setRowCount(0);
        for (int i = 0; i < data.size(); ++i) {
            Department department = data.get(i);
            int departmentId = department.getDepartmentID();
            String name = department.getName();
            Object[] row = {departmentId, name};
            tableModel.addRow(row);
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

        pChooseDepartment = new javax.swing.JPanel();
        lblChooseDepartment = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        tbDepartment = new javax.swing.JTable();
        btnConfirm = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Choose department");
        setResizable(false);

        pChooseDepartment.setBackground(new java.awt.Color(255, 255, 255));

        lblChooseDepartment.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblChooseDepartment.setText("Choose department:");

        txtSearch.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        tbDepartment.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tbDepartment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPane.setViewportView(tbDepartment);

        btnConfirm.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btnConfirm.setText("OK");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pChooseDepartmentLayout = new javax.swing.GroupLayout(pChooseDepartment);
        pChooseDepartment.setLayout(pChooseDepartmentLayout);
        pChooseDepartmentLayout.setHorizontalGroup(
            pChooseDepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pChooseDepartmentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pChooseDepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pChooseDepartmentLayout.createSequentialGroup()
                        .addComponent(txtSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch))
                    .addGroup(pChooseDepartmentLayout.createSequentialGroup()
                        .addGroup(pChooseDepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblChooseDepartment))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pChooseDepartmentLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnConfirm)))
                .addContainerGap())
        );
        pChooseDepartmentLayout.setVerticalGroup(
            pChooseDepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pChooseDepartmentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblChooseDepartment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pChooseDepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConfirm)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pChooseDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pChooseDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        if (txtSearch.getText().trim().toLowerCase().equals(query.toLowerCase())) {
            return;
        }
        setQuery(txtSearch.getText().trim());
        getDepartmentList(query);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtSearch.getText().trim().toLowerCase().equals(query.toLowerCase())) {
                return;
            }
            setQuery(txtSearch.getText().trim());
            getDepartmentList(query);
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        // TODO add your handling code here:
        int row = tbDepartment.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please choose a department");
            return;
        }
        Department department = data.get(row);
        listener.onDepartmentChosen(department);
        this.dispose();
    }//GEN-LAST:event_btnConfirmActionPerformed

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
            java.util.logging.Logger.getLogger(DialogChooseDepartment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogChooseDepartment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogChooseDepartment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogChooseDepartment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogChooseDepartment dialog = new DialogChooseDepartment(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel lblChooseDepartment;
    private javax.swing.JPanel pChooseDepartment;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable tbDepartment;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}