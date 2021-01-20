package com;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
/*
 * Created by JFormDesigner on Mon Jun 29 22:28:48 SGT 2020
 */



/**
 * @author Dude Dean
 */
public class register extends JFrame {
    public register() {
        initComponents();
    }

    public static void main(String[] args) {
        try {
            register register = new register();
            register.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registerButtonActionPerformed(ActionEvent e) {
        String firstName = firstNameTF.getText();
        String lastName = lastNameTF.getText();
        String email = emailTF.getText();
        String password = new String(passwordTF.getPassword());
        String phoneNo = phoneNoTF.getText();
        String faculty = facultyTF.getText();
        String course = courseTF.getText();
        String yearOfStudy = yearOfStudyTF.getText();
        int len = phoneNo.length();

        String msg = "" + firstName;
        msg += " \n";
        if (len < 10) {
            JOptionPane.showMessageDialog(null, "Enter a valid mobile number");
        }

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_project?serverTimezone=UTC","root","");

            PreparedStatement ps = connection.prepareStatement("insert into user(firstName,lastName,email,password,phoneNumber,faculty,course,yearOfStudy) values(?,?,?,?,?,?,?,?)");

            ps.setString(1, firstNameTF.getText());
            ps.setString(2, lastNameTF.getText());
            ps.setString(3, emailTF.getText());
            ps.setString(4, new String(passwordTF.getPassword()));
            ps.setString(5, phoneNoTF.getText());
            ps.setString(6, facultyTF.getText());
            ps.setString(7, courseTF.getText());
            ps.setString(8, yearOfStudyTF.getText());

            int x = ps.executeUpdate();
            if (x == 0) {
                JOptionPane.showMessageDialog(null, "This is already exist");
            } else {
                JOptionPane.showMessageDialog(null,
                        "Welcome, " + msg + "Your account is sucessfully created");
                this.dispose();
                loginStudent.main(new String[]{});
            }
            connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void backButtonActionPerformed(ActionEvent e) {
        mainApp.main(new String[]{});
        this.dispose();
    }

    private void initComponents() {
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        firstNameTF = new JTextField();
        lastNameTF = new JTextField();
        emailTF = new JTextField();
        phoneNoTF = new JTextField();
        facultyTF = new JTextField();
        courseTF = new JTextField();
        yearOfStudyTF = new JTextField();
        passwordTF = new JPasswordField();
        buttonBar = new JPanel();
        registerButton = new JButton();
        backButton = new JButton();

        //======== this ========
        setTitle("Register");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {

                //---- label1 ----
                label1.setText("Register as new user");

                //---- label2 ----
                label2.setText("First Name : ");

                //---- label3 ----
                label3.setText("Last Name : ");

                //---- label4 ----
                label4.setText("Email : ");

                //---- label5 ----
                label5.setText("Password : ");

                //---- label6 ----
                label6.setText("Phone No : ");

                //---- label7 ----
                label7.setText("Faculty : ");

                //---- label8 ----
                label8.setText("Course : ");

                //---- label9 ----
                label9.setText("Year of Study : ");

                GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
                contentPanel.setLayout(contentPanelLayout);
                contentPanelLayout.setHorizontalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addGap(32, 32, 32)
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addComponent(label9)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(yearOfStudyTF, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE))
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addComponent(label2)
                                    .addGap(18, 18, 18)
                                    .addComponent(firstNameTF, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE))
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addGroup(contentPanelLayout.createParallelGroup()
                                        .addComponent(label3)
                                        .addComponent(label4)
                                        .addComponent(label5)
                                        .addComponent(label6)
                                        .addComponent(label7)
                                        .addComponent(label8))
                                    .addGap(18, 18, 18)
                                    .addGroup(contentPanelLayout.createParallelGroup()
                                        .addComponent(lastNameTF)
                                        .addComponent(emailTF)
                                        .addComponent(phoneNoTF)
                                        .addComponent(facultyTF)
                                        .addComponent(passwordTF, GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                                        .addGroup(GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                                            .addGap(2, 2, 2)
                                            .addComponent(courseTF, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE))))
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addGap(115, 115, 115)
                                    .addComponent(label1)
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addContainerGap())
                );
                contentPanelLayout.setVerticalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addComponent(label1)
                            .addGap(27, 27, 27)
                            .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label2)
                                .addComponent(firstNameTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addComponent(label3)
                                .addComponent(lastNameTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addComponent(label4)
                                .addComponent(emailTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label5)
                                .addComponent(passwordTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label6)
                                .addComponent(phoneNoTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label7)
                                .addComponent(facultyTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addComponent(label8)
                                .addComponent(courseTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label9)
                                .addComponent(yearOfStudyTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(16, Short.MAX_VALUE))
                );
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- registerButton ----
                registerButton.setText("Register");
                registerButton.addActionListener(this::registerButtonActionPerformed);
                buttonBar.add(registerButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- backButton ----
                backButton.setText("Back");
                backButton.addActionListener(this::backButtonActionPerformed);
                buttonBar.add(backButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
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
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JTextField firstNameTF;
    private JTextField lastNameTF;
    private JTextField emailTF;
    private JTextField phoneNoTF;
    private JTextField facultyTF;
    private JTextField courseTF;
    private JTextField yearOfStudyTF;
    private JPasswordField passwordTF;
    private JPanel buttonBar;
    private JButton registerButton;
    private JButton backButton;
}
