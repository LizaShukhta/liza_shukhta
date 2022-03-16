package listeners;

import connection.DataPreparer;
import dialogs.*;
import model.*;
import table_models.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

abstract class BAbstractSelectListener implements ActionListener {
    protected CDialog parentalDialog;
    protected String orderBy = "";
    protected String where = "";
    protected Class<? extends Model> currentClass;

    public BAbstractSelectListener(CDialog parentalDialog, Class<? extends Model> currentClass){
        super();
        this.parentalDialog = parentalDialog;
        this.currentClass = currentClass;
    }


    protected void choseDefCols(CDialogNotFrame dialog){
        switch (currentClass.getName()) {
            case "model.TesttypeModel":     addDefCols(dialog, TesttypeModel.COLUMNS_BY_RUS); break;
            case "model.TestcaseModel":      addDefCols(dialog, TestcaseModel.COLUMNS_BY_RUS); break;
            case "model.RequestModel":     addDefCols(dialog, RequestModel.COLUMNS_BY_RUS); break;
            case "model.TesterModel":     addDefCols(dialog, TesterModel.COLUMNS_BY_RUS); break;
            case "model.UserModel":         addDefCols(dialog, UserModel.COLUMNS_BY_RUS); break;
            case "model.TestmachineModel":    addDefCols(dialog, TestmachineModel.COLUMNS_BY_RUS); break;
        }
    }

    private void addDefCols(CDialogNotFrame dialog, String items[]){
        for (int i = 0; i < items.length; i++) {
            dialog.comboBoxes.get(0).addItem(items[i]);
        }
    }

    protected void getData(){
        switch (currentClass.getName()) {
            case "model.TesttypeModel":     getDataTesttype(); break;
            case "model.TestcaseModel":      getDataTestcase(); break;
            case "model.RequestModel":     getDataRequest(); break;
            case "model.TesterModel":     getDataTester(); break;
            case "model.UserModel":         getDataUser(); break;
            case "model.TestmachineModel":    getDataTestmachine(); break;
        }
    }

    private void getDataTesttype(){
        ArrayList<Model> result;
        if((result = DataPreparer.getTesttypes(orderBy, where)) == null){
            JOptionPane.showMessageDialog(parentalDialog, "Error сортировки!", "Error!", JOptionPane.PLAIN_MESSAGE);
        } else {
            parentalDialog.tables.get(0).setModel(new TesttypeTableModel(result, true));
        }
    }

    private void getDataTestcase(){
        ArrayList<Model> result;
        if((result = DataPreparer.getTestcases(orderBy, where)) == null){
            JOptionPane.showMessageDialog(parentalDialog, "Error сортировки!", "Error!", JOptionPane.PLAIN_MESSAGE);
        } else {
            parentalDialog.tables.get(0).setModel(new TestcaseTableModel(result, true));
        }
    }


    private void getDataRequest(){
        ArrayList<Model> result;
        if((result = DataPreparer.getRequests(orderBy, where)) == null){
            JOptionPane.showMessageDialog(parentalDialog, "Error сортировки!", "Error!", JOptionPane.PLAIN_MESSAGE);
        } else {
            parentalDialog.tables.get(0).setModel(new RequestTableModel(result, true));
        }
    }


    private void getDataTester(){
        ArrayList<Model> result;
        if((result = DataPreparer.getTesters(orderBy, where)) == null){
            JOptionPane.showMessageDialog(parentalDialog, "Error сортировки!", "Error!", JOptionPane.PLAIN_MESSAGE);
        } else {
            parentalDialog.tables.get(0).setModel(new TesterTableModel(result, true));
        }
    }

    private void getDataUser(){
        ArrayList<Model> result;
        if((result = DataPreparer.getUsers(orderBy, where)) == null){
            JOptionPane.showMessageDialog(parentalDialog, "Error сортировки!", "Error!", JOptionPane.PLAIN_MESSAGE);
        } else {
            parentalDialog.tables.get(0).setModel(new UserTableModel(result, true));
        }
    }

    private void getDataTestmachine(){
        ArrayList<Model> result;
        if((result = DataPreparer.getTestmachines(orderBy, where)) == null){
            JOptionPane.showMessageDialog(parentalDialog, "Error сортировки!", "Error!", JOptionPane.PLAIN_MESSAGE);
        } else {
            parentalDialog.tables.get(0).setModel(new TestmachineTableModel(result, true));
        }
    }

}
