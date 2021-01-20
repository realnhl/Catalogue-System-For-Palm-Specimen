package com;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

/**
 * @author Woo
 */
public class viewRecord extends JFrame implements viewRecordInterface {
    static viewRecord viewRecord;
    String id;

    public static void main(String[] args){
        try {
            viewRecord = new viewRecord();
            viewRecord.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public viewRecord(String id) throws SQLException {
        this.id = id;
        initComponents();
        displayResults();
    }

    @Override
    public void displayResults() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_project?serverTimezone=UTC","root","");
        String sql = "select * from view where id = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1,id);
        ResultSet rs = preparedStatement.executeQuery();

        rs.first();

            byte[] img = rs.getBytes("Photo");

            ImageIcon img1 = new ImageIcon(img);
            Image img2 = img1.getImage();
            Image img3 = img2.getScaledInstance(photoLabel.getWidth(), photoLabel.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon nImage = new ImageIcon(img3);
            photoLabel.setIcon(nImage);

            recordIDTF.setText(rs.getString("id"));
            commonNameTF.setText(rs.getString("Common_Name"));
            genusTF.setText(rs.getString("Genus"));
            speciesTF.setText(rs.getString("Species"));
            stemTF.setText(rs.getString("Stem"));
            leafTF.setText(rs.getString("Leaf"));
            dateSampledTF.setText(rs.getString("Date_Sampled"));
            timeSampledTF.setText(rs.getString("Time_Sampled"));
            locationSampledTF.setText(rs.getString("Location_Sampled"));

    }

    public viewRecord() throws SQLException {
        initComponents();
        displayResults();
    }

    private void initComponents() {
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        commonNameTF = new JTextField();
        genusTF = new JTextField();
        speciesTF = new JTextField();
        label6 = new JLabel();
        stemTF = new JTextField();
        label5 = new JLabel();
        leafTF = new JTextField();
        label8 = new JLabel();
        dateSampledTF = new JFormattedTextField();
        label9 = new JLabel();
        timeSampledTF = new JTextField();
        label7 = new JLabel();
        locationSampledTF = new JTextField();
        panel1 = new JPanel();
        label10 = new JLabel();
        photoLabel = new JLabel();
        label11 = new JLabel();
        recordIDTF = new JTextField();
        label1 = new JLabel();
        buttonBar2 = new JPanel();
        deleteButton = new JButton();
        editButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("View Record");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {

                //---- label2 ----
                label2.setText("Common Name :");
                label2.setHorizontalAlignment(SwingConstants.TRAILING);

                //---- label3 ----
                label3.setText("Genus :");
                label3.setHorizontalAlignment(SwingConstants.TRAILING);

                //---- label4 ----
                label4.setText("Species :");
                label4.setHorizontalAlignment(SwingConstants.TRAILING);

                //---- commonNameTF ----
                commonNameTF.setEditable(false);

                //---- genusTF ----
                genusTF.setEditable(false);

                //---- speciesTF ----
                speciesTF.setEditable(false);

                //---- label6 ----
                label6.setText("Stem :");
                label6.setHorizontalAlignment(SwingConstants.TRAILING);

                //---- textField4 ----
                stemTF.setEditable(false);

                //---- label5 ----
                label5.setText("Leaf :");
                label5.setHorizontalAlignment(SwingConstants.TRAILING);

                //---- leafTF ----
                leafTF.setEditable(false);

                //---- label8 ----
                label8.setText("Date Sampled :");
                label8.setHorizontalAlignment(SwingConstants.TRAILING);

                //---- textField6 ----
                dateSampledTF.setEditable(false);

                //---- label9 ----
                label9.setText("Time Sampled :");
                label9.setHorizontalAlignment(SwingConstants.TRAILING);

                //---- timeSampledTF ----
                timeSampledTF.setEditable(false);

                //---- label7 ----
                label7.setText("Location Sampled :");
                label7.setHorizontalAlignment(SwingConstants.TRAILING);

                //---- locationSampledTF ----
                locationSampledTF.setEditable(false);

                //======== panel1 ========
                {
                    panel1.setBorder(LineBorder.createBlackLineBorder());

                    //---- label10 ----
                    label10.setText("Photo");
                    label10.setHorizontalAlignment(SwingConstants.CENTER);
                    label10.setFont(label10.getFont().deriveFont(label10.getFont().getSize() + 2f));
                    label10.setBackground(Color.darkGray);

                    GroupLayout panel1Layout = new GroupLayout(panel1);
                    panel1.setLayout(panel1Layout);
                    panel1Layout.setHorizontalGroup(
                            panel1Layout.createParallelGroup()
                                    .addGroup(panel1Layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addGroup(panel1Layout.createParallelGroup()
                                                    .addComponent(label10, GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                                                    .addComponent(photoLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addContainerGap())
                    );
                    panel1Layout.setVerticalGroup(
                            panel1Layout.createParallelGroup()
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(label10)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(photoLabel, GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                                            .addContainerGap())
                    );
                }

                //---- label11 ----
                label11.setText("Record ID : ");

                //---- recordIDTF ----
                recordIDTF.setEditable(false);

                GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
                contentPanel.setLayout(contentPanelLayout);
                contentPanelLayout.setHorizontalGroup(
                        contentPanelLayout.createParallelGroup()
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(contentPanelLayout.createParallelGroup()
                                                .addGroup(contentPanelLayout.createSequentialGroup()
                                                        .addGap(22, 22, 22)
                                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                .addGroup(contentPanelLayout.createSequentialGroup()
                                                                        .addGroup(contentPanelLayout.createParallelGroup()
                                                                                .addComponent(label3, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(label2, GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(label11, GroupLayout.Alignment.TRAILING))
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(commonNameTF, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(genusTF, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(recordIDTF, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)))
                                                                .addGroup(contentPanelLayout.createSequentialGroup()
                                                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(label6, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(label4, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(label5, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(label8)
                                                                                .addComponent(label9, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                                .addGroup(contentPanelLayout.createParallelGroup()
                                                                                        .addComponent(speciesTF, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(stemTF, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(leafTF, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(dateSampledTF, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(timeSampledTF, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)))))
                                                .addGroup(contentPanelLayout.createSequentialGroup()
                                                        .addComponent(label7, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(locationSampledTF, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addContainerGap())
                );
                contentPanelLayout.setVerticalGroup(
                        contentPanelLayout.createParallelGroup()
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addContainerGap())
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                                        .addContainerGap(19, Short.MAX_VALUE)
                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(label11)
                                                .addComponent(recordIDTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(commonNameTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(label2))
                                        .addGap(8, 8, 8)
                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(genusTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(label3))
                                        .addGap(8, 8, 8)
                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(speciesTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(label4))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(label6)
                                                .addComponent(stemTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(label5)
                                                .addComponent(leafTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(label8)
                                                .addComponent(dateSampledTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(label9)
                                                .addComponent(timeSampledTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(label7, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(locationSampledTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGap(52, 52, 52))
                );
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //---- label1 ----
            label1.setText("View Record");
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            label1.setFont(new Font("Segoe UI", Font.PLAIN, 17));
            dialogPane.add(label1, BorderLayout.NORTH);

            //======== buttonBar2 ========
            {
                buttonBar2.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar2.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar2.getLayout()).columnWidths = new int[] {272, 0, 90, 80};
                ((GridBagLayout)buttonBar2.getLayout()).columnWeights = new double[] {1.0, 1.0, 1.0, 1.0};

                //---- deleteButton ----
                deleteButton.setText("Delete");
                deleteButton.addActionListener(this::deleteButtonActionPerformed);
                buttonBar2.add(deleteButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));

                //---- editButton ----
                editButton.setText("Edit");
                editButton.addActionListener(e -> {
                    try {
                        editButtonActionPerformed(e);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                });
                buttonBar2.add(editButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Back");
                cancelButton.addActionListener(this::cancelButtonActionPerformed);
                buttonBar2.add(cancelButton, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar2, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }

    private void deleteButtonActionPerformed(ActionEvent actionEvent) {
        try {
            Statement s = null;
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_project?serverTimezone=UTC","root","");
            s = con.createStatement();
            s.executeUpdate("DELETE FROM view WHERE id = '" + id + "'");
            JOptionPane.showMessageDialog(dialogPane,"Delete success");
            adminHomePage.main(new String[]{});
            this.dispose();
        }
        catch(Exception a) {
            a.printStackTrace();
        }
    }

    private void editButtonActionPerformed(ActionEvent actionEvent) throws SQLException {
        editRecord editRecord = new editRecord(id);
        editRecord.setVisible(true);
        this.dispose();
    }

    @Override
    public void cancelButtonActionPerformed(ActionEvent actionEvent) {
        adminHomePage.main(new String[]{});
        this.dispose();
    }

    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JTextField commonNameTF;
    private JTextField genusTF;
    private JTextField speciesTF;
    private JLabel label6;
    private JTextField stemTF;
    private JLabel label5;
    private JTextField leafTF;
    private JLabel label8;
    private JFormattedTextField dateSampledTF;
    private JLabel label9;
    private JTextField timeSampledTF;
    private JLabel label7;
    private JTextField locationSampledTF;
    private JPanel panel1;
    private JLabel label10;
    private JLabel photoLabel;
    private JLabel label11;
    private JTextField recordIDTF;
    private JLabel label1;
    private JPanel buttonBar2;
    private JButton deleteButton;
    private JButton editButton;
    private JButton cancelButton;
}
