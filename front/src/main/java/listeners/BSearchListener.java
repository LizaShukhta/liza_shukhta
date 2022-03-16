package listeners;


import dialogs.*;
import model.*;
import validation.Validator;

import javax.swing.*;
import java.awt.event.*;


public class BSearchListener extends BAbstractSelectListener {
    private CDialogNotFrame dialogSearch;

    public BSearchListener(CDialog parentalDialog, Class<? extends Model> currentClass){
        super(parentalDialog, currentClass);
    }

    public void actionPerformed(ActionEvent e) {
        prepareSearchData();
        addEventSearch();
        dialogSearch.setVisible(true);
    }

    private void prepareSearchData(){
        dialogSearch = new CDialogNotFrame("Поиск", 300, 450, true);

        dialogSearch.addLabel(200, 30,50, 20, "Поле:");
        dialogSearch.addComboBox(false,200, 30,50, 50);
        choseDefCols(dialogSearch);
        dialogSearch.addLabel(200, 30,50, 100, "Значение:");
        dialogSearch.addTextField(200, 30,50, 130);
        dialogSearch.addLabel(200, 30,50, 180, "Знак:");
        dialogSearch.addRadioButtonGroupe(200, 20,50, 210, new String[]{"LIKE", "=", ">", "<", "<>"});

        dialogSearch.addButton(200, 30,50, 360, "Искать");

    }


    private void addEventSearch() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                choseWhereStr();
                if(!choseMessageOk()) return;
                getData();
                dialogSearch.dispose();
            }
        };
        dialogSearch.buttons.get(0).addActionListener(actionListener);
    }

    private void choseWhereStr(){
        String apos = "";

        switch (currentClass.getName()) {
            case "model.TesttypeModel":
                apos = prepareWhereString(TesttypeModel.COLUMNS_BY_RUS, TesttypeModel.COLUMNS, TesttypeModel.IS_STR); break;
            case "model.TestcaseModel":
                apos = prepareWhereString(TestcaseModel.COLUMNS_BY_RUS, TestcaseModel.COLUMNS, TestcaseModel.IS_STR); break;
            case "model.RequestModel":
                apos = prepareWhereString(RequestModel.COLUMNS_BY_RUS, RequestModel.COLUMNS, RequestModel.IS_STR); break;
            case "model.TesterModel":
                apos = prepareWhereString(TesterModel.COLUMNS_BY_RUS, TesterModel.COLUMNS, TesterModel.IS_STR); break;
            case "model.UserModel":
                apos = prepareWhereString(UserModel.COLUMNS_BY_RUS, UserModel.COLUMNS, UserModel.IS_STR); break;
            case "model.TestmachineModel":
                apos = prepareWhereString(TestmachineModel.COLUMNS_BY_RUS, TestmachineModel.COLUMNS, TestmachineModel.IS_STR); break;
        }
        String simbol = getSimbolSearch();
        String firstSimbol = simbol.equals("LIKE") ?  "%" : "";
        String lastSimbol = simbol.equals("LIKE") ? "%" : "";
        where += " " + getSimbolSearch() + " ? @@@" + firstSimbol + dialogSearch.textFields.get(0).getText()
                + lastSimbol +"@@@" + apos;
    }

    private String prepareWhereString(String colsRus[], String cols[], boolean isStr[]){
        String selected = dialogSearch.comboBoxes.get(0).getSelectedItem().toString();
        if(selected.equals(colsRus[0])) where = "`" + Validator.getCurrentTable(currentClass.getName()) + "`.id";
        for (int i = 1; i < colsRus.length; i++) {
            if(selected.equals(colsRus[i])) {
                where = "`" +  Validator.getCurrentTable(currentClass.getName()) + "`." + cols[i-1];
                if(isStr[i-1]) return "false";
            }
        }
        return "true";
    }

    private String getSimbolSearch() {
        for (int i = 0; i < dialogSearch.radioBoxGroups.get(0).length; i++)
            if(dialogSearch.radioBoxGroups.get(0)[i].isSelected()){
                return dialogSearch.radioBoxGroups.get(0)[i].getText();
            }
        return "=";
    }

    private boolean choseMessageOk(){
        switch (currentClass.getName()) {
            default:
            case "model.TesttypeModel":     return messageIntOk(TesttypeModel.IS_STR, false);
            case "model.TestcaseModel":      return messageIntOk(TestcaseModel.IS_STR, true);
            case "model.RequestModel":     return messageIntOk(RequestModel.IS_STR, false);
            case "model.TesterModel":     return messageIntOk(TesterModel.IS_STR, false);
            case "model.UserModel":         return messageIntOk(UserModel.IS_STR, false);
            case "model.TestmachineModel":    return messageIntOk(TestmachineModel.IS_STR, false);
        }
    }

    private boolean messageIntOk(boolean isStr[], boolean isTestcase){
        int selecterIndex = dialogSearch.comboBoxes.get(0).getSelectedIndex();
        boolean isIntColumns = selecterIndex == 0;
        for (int i = 0; i < isStr.length; i++) {
            if(!isStr[i]){
                isIntColumns = isIntColumns || selecterIndex == i+1;
            }
        }

        return checkCulumns(isIntColumns, isTestcase, selecterIndex);
    }

    private boolean checkCulumns(boolean isIntColumns, boolean isTestcase, int selecterIndex){
        if(isIntColumns) {
            if (!Validator.isInt(dialogSearch.textFields.get(0).getText())) {
                String mes = "Введено не целое число для числового поля!";
                JOptionPane.showMessageDialog(dialogSearch, mes, "Error!", JOptionPane.PLAIN_MESSAGE);
                return false;
            }
        } else if(isTestcase && selecterIndex == 3){
            if (!Validator.isDouble(dialogSearch.textFields.get(0).getText())) {
                String mes = "Введено не число для числового поля!";
                JOptionPane.showMessageDialog(dialogSearch, mes, "Error!", JOptionPane.PLAIN_MESSAGE);
                return false;
            }
        }
        return true;
    }

}
