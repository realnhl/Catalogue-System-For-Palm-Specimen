package com;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.border.*;

/**
 * @author Woo
 */
public class editRecord extends JFrame {
    String id;
    static editRecord editRecord;
    private JPanel addRecordPane;
    private JPanel contentPanel;
    private JLabel commonName;
    private JLabel genus;
    private JLabel species;
    private JPanel photoPanel;
    private JLabel photoTitle;
    private JLabel photoLabel;
    private JTextField commonNameTF;
    private JTextField genusTF;
    private JTextField speciesTF;
    private JLabel stem;
    private JTextField stemTF;
    private JLabel leaf;
    private JTextField leafTF;
    private JLabel dateSampled;
    private JTextField dateSampledTF;
    private JLabel timeSampled;
    private JTextField timeSampledTF;
    private JLabel locationSampled;
    private JTextField locationSampledTF;
    private JPanel bottomButtonPanel;
    private JButton okButton;
    private JButton backButton;
    private JLabel contentPaneTitle;
    String l;

    //panel init
    public editRecord() {
        paneInit();
    }

    public editRecord(String id) throws SQLException {
        this.id = id;
        paneInit();
        displayRecord();
    }

    //init obj
    public static void main(String[] args){
        try {
            editRecord = new editRecord();
            editRecord.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayRecord() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_project?serverTimezone=UTC","root","");
        String sql = "select * from view where id = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1,id);
        ResultSet rs = preparedStatement.executeQuery();
        System.out.println(sql);
        System.out.println(preparedStatement);

        rs.first();

        byte[] img = rs.getBytes("Photo");

        ImageIcon img1 = new ImageIcon(img);
        Image img2 = img1.getImage();
        Image img3 = img2.getScaledInstance(photoLabel.getWidth(), photoLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon nImage = new ImageIcon(img3);
        photoLabel.setIcon(nImage);

        commonNameTF.setText(rs.getString("Common_Name"));
        genusTF.setText(rs.getString("Genus"));
        speciesTF.setText(rs.getString("Species"));
        stemTF.setText(rs.getString("Stem"));
        leafTF.setText(rs.getString("Leaf"));
        dateSampledTF.setText(rs.getString("Date_Sampled"));
        timeSampledTF.setText(rs.getString("Time_Sampled"));
        locationSampledTF.setText(rs.getString("Location_Sampled"));

    }


    //button to update data into database
    private void okButtonActionPerformed(ActionEvent e) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_project?serverTimezone=UTC","root","");
            PreparedStatement ps = con.prepareStatement("UPDATE view SET Common_Name=?, Genus=?, Species=?, Stem=?, Leaf=?, Date_Sampled=?, Time_Sampled=?, Location_Sampled=? WHERE id =?");
            ps.setString(1, commonNameTF.getText());
            ps.setString(2, genusTF.getText());
            ps.setString(3, speciesTF.getText());
            ps.setString(4, stemTF.getText());
            ps.setString(5, leafTF.getText());
            ps.setString(6, dateSampledTF.getText());
            ps.setString(7, timeSampledTF.getText());
            ps.setString(8, locationSampledTF.getText());
            ps.setString(9, id);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Update Success!");
            this.dispose();
            adminHomePage.main(new String[]{});
        }
        catch (Exception a)
        {
            a.printStackTrace();
        }
    }

    //back button to main menu
    private void backButtonActionPerformed(ActionEvent e) {
        adminHomePage.main(new String[]{});
        this.dispose();
    }

    private void paneInit() {
        addRecordPane = new JPanel();
        contentPanel = new JPanel();
        commonName = new JLabel();
        genus = new JLabel();
        species = new JLabel();
        photoPanel = new JPanel();
        photoTitle = new JLabel();
        photoLabel = new JLabel();
        commonNameTF = new JTextField();
        genusTF = new JTextField();
        speciesTF = new JTextField();
        stem = new JLabel();
        stemTF = new JTextField();
        leaf = new JLabel();
        leafTF = new JTextField();
        dateSampled = new JLabel();
        dateSampledTF = new JTextField();
        timeSampled = new JLabel();
        timeSampledTF = new JTextField();
        locationSampled = new JLabel();
        locationSampledTF = new JTextField();
        bottomButtonPanel = new JPanel();
        okButton = new JButton();
        backButton = new JButton();
        contentPaneTitle = new JLabel();

        setTitle("Edit Record");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        {
            addRecordPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            addRecordPane.setLayout(new BorderLayout());

            {
                commonName.setText("Common Name :");
                commonName.setHorizontalAlignment(SwingConstants.TRAILING);

                genus.setText("Genus :");
                genus.setHorizontalAlignment(SwingConstants.TRAILING);

                species.setText("Species :");
                species.setHorizontalAlignment(SwingConstants.TRAILING);

                {

                    

                    //---- label10 ----
                    photoTitle.setText("Photo");
                    photoTitle.setHorizontalAlignment(SwingConstants.CENTER);
                    photoTitle.setFont(photoTitle.getFont().deriveFont(photoTitle.getFont().getSize() + 2f));

                    GroupLayout panel1Layout = new GroupLayout(photoPanel);
                    photoPanel.setLayout(panel1Layout);
                    panel1Layout.setHorizontalGroup(
                            panel1Layout.createParallelGroup()
                                    .addGroup(panel1Layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addGroup(panel1Layout.createParallelGroup()
                                                    .addComponent(photoLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(photoTitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGroup(panel1Layout.createSequentialGroup()
                                                            .addGap(0, 0, Short.MAX_VALUE)
                                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)))
                                            .addContainerGap())
                    );
                    panel1Layout.setVerticalGroup(
                            panel1Layout.createParallelGroup()
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(photoTitle)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(photoLabel, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false))
                                            )
                   );
                }

                stem.setText("Stem :");
                stem.setHorizontalAlignment(SwingConstants.TRAILING);

                leaf.setText("Leaf :");
                leaf.setHorizontalAlignment(SwingConstants.TRAILING);

                dateSampled.setText("Date Sampled :");
                dateSampled.setHorizontalAlignment(SwingConstants.TRAILING);

                timeSampled.setText("Time Sampled :");
                timeSampled.setHorizontalAlignment(SwingConstants.TRAILING);

                locationSampled.setText("Location Sampled :");
                locationSampled.setHorizontalAlignment(SwingConstants.TRAILING);

                GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
                contentPanel.setLayout(contentPanelLayout);
                contentPanelLayout.setHorizontalGroup(
                        contentPanelLayout.createParallelGroup()
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                        .addGroup(contentPanelLayout.createParallelGroup()
                                                .addGroup(contentPanelLayout.createSequentialGroup()
                                                        .addGap(28, 28, 28)
                                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                .addGroup(contentPanelLayout.createSequentialGroup()
                                                                        .addGroup(contentPanelLayout.createParallelGroup()
                                                                                .addComponent(genus, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(commonName, GroupLayout.Alignment.TRAILING))
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(commonNameTF, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(genusTF, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)))
                                                                .addGroup(contentPanelLayout.createSequentialGroup()
                                                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(stem, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(species, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(leaf, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(dateSampled)
                                                                                .addComponent(timeSampled, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                                .addGroup(contentPanelLayout.createParallelGroup()
                                                                                        .addComponent(speciesTF, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(stemTF, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(leafTF, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(dateSampledTF, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(timeSampledTF, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE))))
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(photoPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(contentPanelLayout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addComponent(locationSampled, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(locationSampledTF, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 6, Short.MAX_VALUE))
                );
                contentPanelLayout.setVerticalGroup(
                        contentPanelLayout.createParallelGroup()
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(photoPanel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(contentPanelLayout.createSequentialGroup()
                                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(commonNameTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(commonName))
                                                        .addGap(8, 8, 8)
                                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(genusTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(genus))
                                                        .addGap(8, 8, 8)
                                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(speciesTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(species))
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(stem)
                                                                .addComponent(stemTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(leaf)
                                                                .addComponent(leafTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(dateSampled)
                                                                .addComponent(dateSampledTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(timeSampled)
                                                                .addComponent(timeSampledTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(locationSampled, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(locationSampledTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                                        .addGap(0, 6, Short.MAX_VALUE))
                );
            }
            addRecordPane.add(contentPanel, BorderLayout.CENTER);

            {
                bottomButtonPanel.setBorder(new EmptyBorder(12, 0, 0, 0));
                bottomButtonPanel.setLayout(new GridBagLayout());
                ((GridBagLayout) bottomButtonPanel.getLayout()).columnWidths = new int[] {425, 90, 80};
                ((GridBagLayout) bottomButtonPanel.getLayout()).columnWeights = new double[] {1.0, 1.0, 1.0};

                okButton.setText("Update");
                okButton.addActionListener(this::okButtonActionPerformed);
                bottomButtonPanel.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));

                backButton.setText("Back");
                backButton.addActionListener(this::backButtonActionPerformed);
                bottomButtonPanel.add(backButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
            }
            addRecordPane.add(bottomButtonPanel, BorderLayout.SOUTH);

            contentPaneTitle.setText("Edit Record");
            contentPaneTitle.setHorizontalAlignment(SwingConstants.CENTER);
            contentPaneTitle.setFont(new Font("Segoe UI", Font.PLAIN, 17));
            addRecordPane.add(contentPaneTitle, BorderLayout.NORTH);
        }
        contentPane.add(addRecordPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }


}
