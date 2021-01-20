package com;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
/*
 * Created by JFormDesigner on Mon Jun 29 21:59:37 SGT 2020
 */

/**
 * @author Dude Dean
 */
public class loginAdmin extends JFrame implements loginInterface {

    public loginAdmin() {
        initComponents();
    }

    public static void main(String[] args) {
        try {
            loginAdmin loginAdmin = new loginAdmin();
            loginAdmin.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loginBActionPerformed(ActionEvent e) {
        String email = emailTF.getText();
        String password = new String(passwordTF.getPassword());
        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_project?serverTimezone=UTC","root","");

            PreparedStatement st = connection
                    .prepareStatement("Select * from user where email=? and password=? and type=1");

            st.setString(1, email);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                dispose();
                JOptionPane.showMessageDialog(null, "You have successfully logged in");

                adminHomePage.main(new String[]{});

            } else {
                JOptionPane.showMessageDialog(null, "Wrong Username & Password");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void backBActionPerformed(ActionEvent e) {
        mainApp.main(new String[]{});
        this.dispose();
    }

    private void initComponents() {
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        emailTF = new JTextField();
        passwordTF = new JPasswordField();
        buttonBar = new JPanel();
        loginB = new JButton();
        backB = new JButton();

        //======== this ========
        setTitle("Admin Login");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {

                //---- label1 ----
                label1.setText("Login as Admin");

                //---- label2 ----
                label2.setText("Email : ");

                //---- label3 ----
                label3.setText("Password");

                GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
                contentPanel.setLayout(contentPanelLayout);
                contentPanelLayout.setHorizontalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addGap(171, 171, 171)
                                    .addComponent(label1))
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addGap(46, 46, 46)
                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(label3)
                                        .addComponent(label2))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(emailTF, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                        .addComponent(passwordTF, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))))
                            .addContainerGap(108, Short.MAX_VALUE))
                );
                contentPanelLayout.setVerticalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addComponent(label1)
                            .addGap(38, 38, 38)
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addComponent(label2)
                                .addComponent(emailTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addComponent(label3)
                                .addComponent(passwordTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(28, Short.MAX_VALUE))
                );
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- loginB ----
                loginB.setText("Login");
                loginB.addActionListener(this::loginBActionPerformed);
                buttonBar.add(loginB, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- backB ----
                backB.setText("Back");
                backB.addActionListener(this::backBActionPerformed);
                buttonBar.add(backB, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }

    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField emailTF;
    private JPasswordField passwordTF;
    private JPanel buttonBar;
    private JButton loginB;
    private JButton backB;
}
