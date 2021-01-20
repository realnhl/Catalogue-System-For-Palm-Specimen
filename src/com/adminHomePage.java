package com;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*;
import java.util.ArrayList;
/*
 * Created by JFormDesigner on Sun Jul 05 16:33:48 SGT 2020
 */



/**
 * @author Dude Dean
 */
public class adminHomePage extends JFrame implements HomePageInterface{
    public adminHomePage() {
        initComponents();
    }

    public static void main(String[] args) {
        try {
            adminHomePage adminHomePage = new adminHomePage();
            adminHomePage.setVisible(true);

            DefaultTableModel model = new DefaultTableModel();
            Object[] columnsName = new Object[7];
            columnsName[0] = "No";
            columnsName[1] = "Id";
            columnsName[2] = "Common Name";
            columnsName[3] = "Genus";
            columnsName[4] = "Species";
            columnsName[5] = "Date Sampled";
            columnsName[6] = "Location Sampled";

            model.setColumnIdentifiers(columnsName);


            Object[] rowData = new Object[8];

            for(int i = 0 ; i < getRecords().size() ; i++){
                String id = getRecords().get(i).getRecordID();
                rowData[0] = i+1;
                rowData[1] = getRecords().get(i).getRecordID();
                rowData[2] = getRecords().get(i).getCommonName();
                rowData[3] = getRecords().get(i).getGenus();
                rowData[4] = getRecords().get(i).getSpecies();
                rowData[5] = getRecords().get(i).getDateSampled();
                rowData[6] = getRecords().get(i).getLocationSampled();
                model.addRow(rowData);
            }

            listOfRecordsTable.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static ArrayList<recordData> getRecords() throws SQLException {
        ArrayList<recordData> records = new ArrayList<recordData>();

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_project?serverTimezone=UTC","root","");
        Statement st;
        ResultSet rs;
        recordData r;

        st = con.createStatement();
        rs = st.executeQuery("SELECT * FROM view");

        while(rs.next()){
            r = new recordData(
                    rs.getString("id"),
                    rs.getString("Common_Name"),
                    rs.getString("Genus"),
                    rs.getString("Species"),
                    rs.getString("Stem"),
                    rs.getString("Leaf"),
                    rs.getString("Time_Sampled"),
                    rs.getString("Date_Sampled"),
                    rs.getString("Location_Sampled")
            );

            records.add(r);
        }

        return records;
    }

    private void addNewRecordActionPerformed(ActionEvent e) {
        addNewRecord.main(new String[]{});
        this.dispose();
    }

    public void searchRecordActionPerformed(ActionEvent e) {
        searchRecord.main(new String[]{});
        this.dispose();
    }

    private void generateReportActionPerformed(ActionEvent e) {
        generateReport.main(new String[]{});
    }

    @Override
    public void logoutActionPerformed(ActionEvent e) {
        this.dispose();
        mainApp.main(new String[]{});
    }

    public void listOfRecordsTableMouseClicked(MouseEvent e) throws SQLException {

        int index = listOfRecordsTable.getSelectedRow();
        TableModel model = listOfRecordsTable.getModel();
        String id = model.getValueAt(index,1).toString();
        viewRecord viewRecord = new viewRecord(id);
//        viewRecordResult.initComponents();
//        viewRecordResult.displayResults();
        this.dispose();
        viewRecord.setVisible(true);

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Dude Dean
        addNewRecordB = new JButton();
        searchRecordB = new JButton();
        generateReportB = new JButton();
        scrollPane1 = new JScrollPane();
        listOfRecordsTable = new JTable();
        listofRecordsLabel = new JLabel();
        logoutB = new JButton();

        //======== this ========
        setTitle("Homepage");
        Container contentPane = getContentPane();

        //---- addNewRecordB ----
        addNewRecordB.setText("Add New Record");
        addNewRecordB.addActionListener(e -> addNewRecordActionPerformed(e));

        //---- searchRecordB ----
        searchRecordB.setText("Search Record");
        searchRecordB.addActionListener(e -> searchRecordActionPerformed(e));

        //---- generateReportB ----
        generateReportB.setText("Generate Report");
        generateReportB.addActionListener(e -> generateReportActionPerformed(e));

        //======== scrollPane1 ========
        {

            //---- listOfRecordsTable ----
            listOfRecordsTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        listOfRecordsTableMouseClicked(e);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            });
            scrollPane1.setViewportView(listOfRecordsTable);
        }

        //---- listofRecordsLabel ----
        listofRecordsLabel.setText("List of Records");
        listofRecordsLabel.setFont(listofRecordsLabel.getFont().deriveFont(listofRecordsLabel.getFont().getSize() + 14f));

        //---- logoutB ----
        logoutB.setText("Logout");
        logoutB.addActionListener(e -> logoutActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(62, 62, 62)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(listofRecordsLabel)
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 731, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(addNewRecordB)
                            .addGap(93, 93, 93)
                            .addComponent(searchRecordB)
                            .addGap(105, 105, 105)
                            .addComponent(generateReportB)
                            .addGap(100, 100, 100)
                            .addComponent(logoutB)))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(23, 23, 23)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(addNewRecordB)
                        .addComponent(logoutB)
                        .addComponent(searchRecordB)
                        .addComponent(generateReportB))
                    .addGap(18, 18, 18)
                    .addComponent(listofRecordsLabel)
                    .addGap(18, 18, 18)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(21, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Dude Dean
    private JButton addNewRecordB;
    private JButton searchRecordB;
    private JButton generateReportB;
    private JScrollPane scrollPane1;
    public static JTable listOfRecordsTable;
    private JLabel listofRecordsLabel;
    private JButton logoutB;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
