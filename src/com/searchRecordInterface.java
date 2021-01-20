package com;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public interface searchRecordInterface {
    void displaySearchResult() throws SQLException;

    ArrayList<recordData> getRecords() throws SQLException;

    default void searchButtonActionPerformed(ActionEvent e) throws SQLException {
    }

    default void backButtonActionPerformed(ActionEvent e) {
        adminHomePage.main(new String[]{});
    }

    default void resultsTableMouseClicked(MouseEvent e) throws SQLException {
        int index = searchRecord.resultsTable.getSelectedRow();
        TableModel model = searchRecord.resultsTable.getModel();
        String id = model.getValueAt(index,1).toString();
        viewRecord viewRecord = new viewRecord(id);
        viewRecord.setVisible(true);
    }
}

