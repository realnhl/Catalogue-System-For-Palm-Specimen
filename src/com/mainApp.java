package com;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;

/**
 * @author Dude Dean
 */
public class mainApp extends JFrame {
    static JFrame frame = new JFrame("Welcome");
    public static void main(String[] args) {

        frame.setContentPane(new mainApp().dialogPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public mainApp() {
        initComponents();
    }

    private void loginAdminActionPerformed(ActionEvent e) {
        loginAdmin loginAdmin = new loginAdmin();
        loginAdmin.main(new String[]{});
        frame.dispose();
    }

    private void loginStudentActionPerformed(ActionEvent e) {
        loginStudent loginStudent = new loginStudent();
        loginStudent.main(new String[]{});
        frame.dispose();
    }

    private void registerActionPerformed(ActionEvent e) {
        register register = new register();
        register.main(new String[]{});
        frame.dispose();
    }

    private void initComponents() {
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        register = new JButton();
        loginAdmin = new JButton();
        loginStudent = new JButton();

        //======== this ========
        setTitle("Welcome");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {

                //---- label1 ----
                label1.setText("Welcome to UNIMAS Palm Specimen Database");

                //---- label2 ----
                label2.setText("To use this software, you need to login first");

                //---- register ----
                register.setText("Register");
                register.addActionListener(this::registerActionPerformed);

                //---- loginAdmin ----
                loginAdmin.setText("Login as Admin");
                loginAdmin.addActionListener(this::loginAdminActionPerformed);

                //---- loginStudent ----
                loginStudent.setText("Login as Student");
                loginStudent.addActionListener(this::loginStudentActionPerformed);

                GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
                contentPanel.setLayout(contentPanelLayout);
                contentPanelLayout.setHorizontalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                            .addContainerGap(67, Short.MAX_VALUE)
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                                    .addGroup(contentPanelLayout.createParallelGroup()
                                        .addComponent(label1)
                                        .addGroup(contentPanelLayout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(label2)
                                                .addComponent(loginAdmin, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(loginStudent, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                    .addGap(56, 56, 56))
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                                    .addComponent(register)
                                    .addGap(152, 152, 152))))
                );
                contentPanelLayout.setVerticalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addComponent(label1)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(label2)
                            .addGap(30, 30, 30)
                            .addComponent(loginAdmin, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(loginStudent)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                            .addComponent(register)
                            .addContainerGap())
                );
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }

    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JLabel label2;
    private JButton register;
    private JButton loginAdmin;
    private JButton loginStudent;
}
