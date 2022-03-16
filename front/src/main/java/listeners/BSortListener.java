package listeners;


import dialogs.*;
import model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BSortListener extends BAbstractSelectListener {
    private CDialogNotFrame dialodSort;

    public BSortListener(CDialog parentalDialog, Class<? extends Model> currentClass){
        super(parentalDialog, currentClass);
    }

    public void actionPerformed(ActionEvent e) {
            prepareSortData();
            addEventSort();
            dialodSort.setVisible(true);
    }

    private void prepareSortData(){
        dialodSort = new CDialogNotFrame("Сортировка", 300, 200, true);

        dialodSort.addLabel(200, 30,50, 20, "Поле:");
        dialodSort.addComboBox(false,200, 30,50, 50);
        choseDefCols(dialodSort);
        dialodSort.addCheckBox(200, 30,50, 80,"DESC", false);

        dialodSort.addButton(200, 30,50, 110, "Сортировать");
    }



    private void addEventSort() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                choseOrderByString();
                getData();
                dialodSort.dispose();
            }
        };
        dialodSort.buttons.get(0).addActionListener(actionListener);
    }

    private void choseOrderByString(){
        switch (currentClass.getName()) {
            case "model.TesttypeModel":     prepareOrderByString(TesttypeModel.COLUMNS_BY_RUS, TesttypeModel.COLUMNS); break;
            case "model.TestcaseModel":      prepareOrderByString(TestcaseModel.COLUMNS_BY_RUS, TestcaseModel.COLUMNS); break;
            case "model.RequestModel":     prepareOrderByString(RequestModel.COLUMNS_BY_RUS, RequestModel.COLUMNS); break;
            case "model.TesterModel":     prepareOrderByString(TesterModel.COLUMNS_BY_RUS, TesterModel.COLUMNS); break;
            case "model.UserModel":         prepareOrderByString(UserModel.COLUMNS_BY_RUS, UserModel.COLUMNS); break;
            case "model.TestmachineModel":    prepareOrderByString(TestmachineModel.COLUMNS_BY_RUS, TestmachineModel.COLUMNS); break;
        }
        if(dialodSort.checkBoxes.get(0).isSelected()) orderBy += " desc";
    }

    private void prepareOrderByString(String colsRus[], String cols[]){
        String selected = dialodSort.comboBoxes.get(0).getSelectedItem().toString();
        if(selected.equals(colsRus[0])) orderBy = "id";
        for (int i = 1; i < colsRus.length; i++) {
            if(selected.equals(colsRus[i])) orderBy = cols[i-1];
        }
    }

}
