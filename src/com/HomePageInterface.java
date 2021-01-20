package com;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public interface HomePageInterface {
    void searchRecordActionPerformed(ActionEvent e);

    default void logoutActionPerformed(ActionEvent e) {
        mainApp.main(new String[]{});
    }

    void listOfRecordsTableMouseClicked(MouseEvent e) throws SQLException;
}
