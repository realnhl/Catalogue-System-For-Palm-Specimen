package com;

import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class searchRecordS extends JFrame implements searchRecordInterface{
	 public searchRecordS() {
	        initComponents();
	    }

	public static void main(String[] args){
        try {
            searchRecordS searchRecordS = new searchRecordS();
            searchRecordS.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displaySearchResult() throws SQLException {
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

        resultsTable.setModel(model);
    }

    @Override
    public ArrayList<recordData> getRecords() throws SQLException {
        ArrayList<recordData> records = new ArrayList<recordData>();

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_project?serverTimezone=UTC","root","");
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("select * from view where Common_Name = '" + commonNameTF.getText() + "' OR Genus = '" + genusTF.getText() + "' OR Species = '" + speciesTF.getText() + "'");
        recordData r;

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

    public void searchButtonActionPerformed(ActionEvent e) throws SQLException {
        displaySearchResult();
    }

    public void backButtonActionPerformed(ActionEvent e) {
        studentHomePage.main(new String[]{});
        this.dispose();
    }

    public void resultsTableMouseClicked(MouseEvent e) throws SQLException {
        int index = resultsTable.getSelectedRow();
        TableModel model = resultsTable.getModel();
        String id = model.getValueAt(index,1).toString();
        viewRecordS viewRecordS = new viewRecordS(id);
        this.dispose();
        viewRecordS.setVisible(true);
    }

    private void initComponents() {
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        commonNameTF = new JTextField();
        genusTF = new JTextField();
        speciesTF = new JTextField();
        searchButton = new JButton();
        scrollPane1 = new JScrollPane();
        resultsTable = new JTable();
        backButton = new JButton();

        //======== this ========
        setTitle("Search Record");
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Common Name : ");

        //---- label2 ----
        label2.setText("Genus :");

        //---- label3 ----
        label3.setText("Species :");

        //---- searchButton ----
        searchButton.setText("Search");
        searchButton.addActionListener(e -> {
            try {
                searchButtonActionPerformed(e);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        //======== scrollPane1 ========
        {
            //---- resultsTable ----
            resultsTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        resultsTableMouseClicked(e);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            });
            scrollPane1.setViewportView(resultsTable);
        }

        //---- backButton ----
        backButton.setText("Back");
        backButton.addActionListener(this::backButtonActionPerformed);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(33, 33, 33)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 754, GroupLayout.PREFERRED_SIZE)
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                                        .addGap(47, 47, 47)
                                        .addComponent(label3)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(speciesTF))
                                    .addGroup(contentPaneLayout.createSequentialGroup()
                                        .addComponent(label1)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(commonNameTF, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(genusTF, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)))
                        .addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
                        .addComponent(backButton))
                    .addContainerGap(41, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(commonNameTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(genusTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label2))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3)
                        .addComponent(speciesTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(3, 3, 3)
                    .addComponent(searchButton)
                    .addGap(18, 18, 18)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(backButton)
                    .addContainerGap(13, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
    }

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField commonNameTF;
    private JTextField genusTF;
    private JTextField speciesTF;
    private JButton searchButton;
    private JScrollPane scrollPane1;
    public static JTable resultsTable;
    private JButton backButton;
}



