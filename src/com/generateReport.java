package com;

import java.awt.event.*;
import java.awt.*;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.GroupLayout;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 * @author Dude Dean
 */
public class generateReport extends JFrame {
    public generateReport() {
        initComponents();
    }
    
    public static void main(String[] args){
        try {
            generateReport generateReport = new generateReport();
            generateReport.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateReportLocationButtonActionPerformed(ActionEvent e) {
        java.sql.Connection con = null;
        try {
            String jdbcDriver = "com.mysql.jdbc.Driver";
            Class.forName(jdbcDriver);
            
            String url = "jdbc:mysql://localhost:3306/java_project";
            String user = "root";
            String pass = "";
            
            con = DriverManager.getConnection(url,user,pass);
            Statement stm = con.createStatement();
            
            try {
                 String currentDirectory = System.getProperty("user.dir");
                 String report = currentDirectory + "\\src\\" + "reportByLocation.jrxml";
                 System.out.println(report);
                 HashMap hash = new HashMap();
                 hash.put("location",locationTF.getText());
                 JasperReport JRpt = JasperCompileManager.compileReport(report);
                 JasperPrint JPrint = JasperFillManager.fillReport(JRpt,hash,con);
                 JasperViewer.viewReport(JPrint,false);
            } catch (Exception err) {
                System.out.println("report cannot be view because : " + err);
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void generateReportDateButtonActionPerformed(ActionEvent e) {
        java.sql.Connection con = null;
        try {
            String jdbcDriver = "com.mysql.jdbc.Driver";
            Class.forName(jdbcDriver);
            
            String url = "jdbc:mysql://localhost:3306/java_project";
            String user = "root";
            String pass = "";
            
            con = DriverManager.getConnection(url,user,pass);
            Statement stm = con.createStatement();
            
            try {
                 String currentDirectory = System.getProperty("user.dir");
                 String report = currentDirectory + "\\src\\" + "reportByDate.jrxml";
                 HashMap hash = new HashMap();
                 hash.put("startDate",startDateTF.getText());
                 hash.put("endDate",endDateTF.getText());
                 System.out.println(startDateTF.getText());
                 System.out.println(endDateTF.getText());
                 JasperReport JRpt = JasperCompileManager.compileReport(report);
                 JasperPrint JPrint = JasperFillManager.fillReport(JRpt,hash,con);
                 JasperViewer.viewReport(JPrint,false);
            } catch (Exception err) {
                System.out.println("report cannot be view because : " + err);
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void initComponents() {
        label1 = new JLabel();
        locationTF = new JTextField();
        generateReportLocationButton = new JButton();
        label2 = new JLabel();
        label3 = new JLabel();
        startDateTF = new JTextField();
        label4 = new JLabel();
        endDateTF = new JTextField();
        generateReportDateButton = new JButton();

        //======== this ========
        setTitle("Generate Report");
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Location : ");

        //---- generateReportLocationButton ----
        generateReportLocationButton.setText("Generate Report by Location");
        generateReportLocationButton.addActionListener(this::generateReportLocationButtonActionPerformed);

        //---- label2 ----
        label2.setText("OR");

        //---- label3 ----
        label3.setText("Start Date :");

        //---- label4 ----
        label4.setText("End Date : ");

        //---- generateReportDateButton ----
        generateReportDateButton.setText("Generate Report by Date Range");
        generateReportDateButton.addActionListener(this::generateReportDateButtonActionPerformed);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(288, 288, 288)
                            .addComponent(label2))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(33, 33, 33)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(label1)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(locationTF, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(label3)
                                        .addComponent(label4))
                                    .addGap(18, 18, 18)
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(endDateTF)
                                        .addComponent(startDateTF))))
                            .addGap(36, 36, 36)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(generateReportLocationButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(generateReportDateButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGap(64, 64, 64))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(generateReportLocationButton)
                        .addComponent(locationTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(label2)
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label3)
                                .addComponent(startDateTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(34, 34, 34))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(generateReportDateButton)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label4)
                        .addComponent(endDateTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(51, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
    }

    private JLabel label1;
    private JTextField locationTF;
    private JButton generateReportLocationButton;
    private JLabel label2;
    private JLabel label3;
    private JTextField startDateTF;
    private JLabel label4;
    private JTextField endDateTF;
    private JButton generateReportDateButton;
}
