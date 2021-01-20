package com;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public interface viewRecordInterface {
    void displayResults() throws SQLException;

    void cancelButtonActionPerformed(ActionEvent actionEvent);
}
