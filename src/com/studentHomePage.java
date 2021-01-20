package com;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.GroupLayout;
/*
 * Created by JFormDesigner on Tue Jun 30 12:29:03 SGT 2020
 */
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;



/**
 * @author Dude Dean
 */
public class studentHomePage extends JFrame implements HomePageInterface {
    public studentHomePage() {
        initComponents();
    }
    
    public static void main(String[] args) {
        try {
            studentHomePage studentHomePage = new studentHomePage();
            studentHomePage.setVisible(true);

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

    @Override
    public void searchRecordActionPerformed(ActionEvent e) {
        this.dispose();
        searchRecordS.main(new String[]{});
    }

    @Override
    public void logoutActionPerformed(ActionEvent e) {
        this.dispose();
        mainApp.main(new String[]{});
    }

    @Override
    public void listOfRecordsTableMouseClicked(MouseEvent e) throws SQLException {

        int index = listOfRecordsTable.getSelectedRow();
        TableModel model = listOfRecordsTable.getModel();
        String id = model.getValueAt(index,1).toString();
        viewRecordS viewRecordS = new viewRecordS(id);
//        viewRecordResult.initComponents();
//        viewRecordResult.displayResults();
        this.dispose();
        viewRecordS.setVisible(true);

    }

    private void initComponents() {
        searchRecordB = new JButton();
        logoutB = new JButton();
        listofRecordsLabel = new JLabel();
        scrollPane1 = new JScrollPane();
        listOfRecordsTable = new JTable();

        //======== this ========
        setTitle("Homepage");
        var contentPane = getContentPane();

        //---- searchRecordB ----
        searchRecordB.setText("Search Record");
        searchRecordB.addActionListener(this::searchRecordActionPerformed);

        //---- logoutB ----
        logoutB.setText("Logout");
        logoutB.addActionListener(this::logoutActionPerformed);

        //---- listofRecordsLabel ----
        listofRecordsLabel.setText("List of Records");
        listofRecordsLabel.setFont(listofRecordsLabel.getFont().deriveFont(listofRecordsLabel.getFont().getSize() + 14f));

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

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap(216, Short.MAX_VALUE)
                    .addComponent(searchRecordB)
                    .addGap(146, 146, 146)
                    .addComponent(logoutB)
                    .addGap(238, 238, 238))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 720, GroupLayout.PREFERRED_SIZE)
                        .addComponent(listofRecordsLabel))
                    .addContainerGap(44, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(46, 46, 46)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(searchRecordB)
                        .addComponent(logoutB))
                    .addGap(18, 18, 18)
                    .addComponent(listofRecordsLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(24, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
    }

    private JButton searchRecordB;
    private JButton logoutB;
    private JLabel listofRecordsLabel;
    private JScrollPane scrollPane1;
    public static JTable listOfRecordsTable;
}
