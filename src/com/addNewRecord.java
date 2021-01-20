package com;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Woo
 */
public class addNewRecord extends JFrame {
    static addNewRecord addNewRecord;
    private JPanel addRecordPane;
    private JPanel contentPanel;
    private JLabel commonName;
    private JLabel genus;
    private JLabel species;
    private JPanel photoPanel;
    private JButton browsePhoto;
    private JLabel photoTitle;
    private JLabel photoLabel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JLabel stem;
    private JTextField textField4;
    private JLabel leaf;
    private JTextField textField5;
    private JLabel dateSampled;
    private JTextField textField6;
    private JLabel timeSampled;
    private JTextField textField7;
    private JLabel locationSampled;
    private JTextField textField8;
    private JPanel bottomButtonPanel;
    private JButton okButton;
    private JButton backButton;
    private JLabel contentPaneTitle;
    String l;

    //panel init
    public addNewRecord() {
        paneInit();
    }

    //init obj
    public static void main(String[] args){
        try {
            addNewRecord = new addNewRecord();
            addNewRecord.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ImageIcon ResizeImage(String imgPath){
        ImageIcon img = new ImageIcon(imgPath);
        Image img2 = img.getImage();
        Image nImage = img2.getScaledInstance(photoLabel.getWidth(), photoLabel.getHeight(),Image.SCALE_SMOOTH);
        return new ImageIcon(nImage);
    }

    //button to browse image and display at photoLabel
    private void button1ActionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter f = new FileNameExtensionFilter("*.IMAGE","jpg","gif","png");
        fc.addChoosableFileFilter(f);
        int r = fc.showSaveDialog(null);
        if (r == JFileChooser.APPROVE_OPTION)
        {
            File cf = fc.getSelectedFile();
            String path = cf.getAbsolutePath();
            photoLabel.setIcon(ResizeImage(path));
            l = path;
        }
        else if (r == JFileChooser.CANCEL_OPTION)
        {
            System.out.println("Invalid Data");
        }
    }

    //button to insert image and data into database
    private void okButtonActionPerformed(ActionEvent e) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_project?serverTimezone=UTC","root","");
            PreparedStatement ps = con.prepareStatement("insert into view(Common_Name,Genus,Species,Photo,Stem,Leaf,Date_Sampled,Time_Sampled,Location_Sampled) values(?,?,?,?,?,?,?,?,?)");
            InputStream i = new FileInputStream(new File(l));
            ps.setString(1, textField1.getText());
            ps.setString(2, textField2.getText());
            ps.setString(3, textField3.getText());
            ps.setBlob(4, i);
            ps.setString(5, textField4.getText());
            ps.setString(6, textField5.getText());
            ps.setString(7, textField6.getText());
            ps.setString(8, textField7.getText());
            ps.setString(9, textField8.getText());


            ps.executeUpdate();

            new recordData(textField1.toString(), textField2.toString(), textField3.toString(), textField4.toString(), textField5.toString(), textField6.toString(), textField7.toString(), textField8.toString());
            System.out.println("test");
            JOptionPane.showMessageDialog(null, "Data is successfully saved");
        }
        catch (Exception a)
        {
            a.printStackTrace();
        }
    }

    //back button to main menu
    private void backButtonActionPerformed(ActionEvent e) {
        adminHomePage.main(new String[]{});
        addNewRecord.dispose();
    }

    private void paneInit() {
        addRecordPane = new JPanel();
        contentPanel = new JPanel();
        commonName = new JLabel();
        genus = new JLabel();
        species = new JLabel();
        photoPanel = new JPanel();
        browsePhoto = new JButton();
        photoTitle = new JLabel();
        photoLabel = new JLabel();
        textField1 = new JTextField();
        textField2 = new JTextField();
        textField3 = new JTextField();
        stem = new JLabel();
        textField4 = new JTextField();
        leaf = new JLabel();
        textField5 = new JTextField();
        dateSampled = new JLabel();
        textField6 = new JTextField();
        timeSampled = new JLabel();
        textField7 = new JTextField();
        locationSampled = new JLabel();
        textField8 = new JTextField();
        bottomButtonPanel = new JPanel();
        okButton = new JButton();
        backButton = new JButton();
        contentPaneTitle = new JLabel();

        setTitle("Add New Record");
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

                    //---- browsePhoto ----
                    browsePhoto.setText("Browse");
                    browsePhoto.addActionListener(this::button1ActionPerformed);

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
                                                            .addComponent(browsePhoto, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
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
                                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(browsePhoto, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
                                            .addContainerGap())
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
                                                                                .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(textField2, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)))
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
                                                                                        .addComponent(textField3, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(textField4, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(textField5, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(textField6, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(textField7, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE))))
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(photoPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(contentPanelLayout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addComponent(locationSampled, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(textField8, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)))
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
                                                                .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(commonName))
                                                        .addGap(8, 8, 8)
                                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(textField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(genus))
                                                        .addGap(8, 8, 8)
                                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(textField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(species))
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(stem)
                                                                .addComponent(textField4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(leaf)
                                                                .addComponent(textField5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(dateSampled)
                                                                .addComponent(textField6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(timeSampled)
                                                                .addComponent(textField7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(locationSampled, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(textField8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                                        .addGap(0, 6, Short.MAX_VALUE))
                );
            }
            addRecordPane.add(contentPanel, BorderLayout.CENTER);

            {
                bottomButtonPanel.setBorder(new EmptyBorder(12, 0, 0, 0));
                bottomButtonPanel.setLayout(new GridBagLayout());
                ((GridBagLayout) bottomButtonPanel.getLayout()).columnWidths = new int[] {425, 90, 80};
                ((GridBagLayout) bottomButtonPanel.getLayout()).columnWeights = new double[] {1.0, 1.0, 1.0};

                okButton.setText("Add");
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

            contentPaneTitle.setText("Add New Record");
            contentPaneTitle.setHorizontalAlignment(SwingConstants.CENTER);
            contentPaneTitle.setFont(new Font("Segoe UI", Font.PLAIN, 17));
            addRecordPane.add(contentPaneTitle, BorderLayout.NORTH);
        }
        contentPane.add(addRecordPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }


}
