package listeners;

import connection.DataPreparer;
import dialogs.CDialog;
import dialogs.CDialogNotFrame;
import dialog_construction.OneTableDialog;
import model.*;
import validation.Validator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class BAddRedListener extends BAbstractEditListener {
    private CDialogNotFrame dialogAdd;

    public BAddRedListener(CDialog frameDialog, Class<? extends Model> currentClass, ArrayList<Model> models, OneTableDialog parental){
        super(frameDialog, currentClass, models, parental);
    }

    public void actionPerformed(ActionEvent e) {
        boolean isRed;
        isRed = e.getActionCommand() != "Создать" && e.getActionCommand() != "+";
        if(isRed && !isIdCorrect()) return;
        prepareAddData(isRed);
        dialogAdd.setVisible(true);
    }

    protected void prepareAddData(final boolean isRed){
        dialogAdd = new CDialogNotFrame("Создание", 300, 420, true);
        int i;
        String columns[] = getColumns();

        for (i = 1; i < columns.length; i++) {
            dialogAdd.addLabel(200, 30, 50, 10 + (i - 1) * 80, columns[i]);
            prepareValueField(i);
        }

        JButton button = dialogAdd.addButton(200, 30,50, 90+(i-2)*80, "Применить");
        addEventAdd(isRed, button);
        if (isRed) {
            JButton button2 = dialogAdd.addButton(200, 30, 50, 140 + (i - 2) * 80, "Удалить");
            addEventRemove(button2);
        }
        dialogAdd.setSize(300, (isRed ? 230 : 180)+(i-2)*80);
        Validator.setSize(dialogAdd, 40);

        if (isRed) {
            addDataLast();
            addBlockData();
        }
    }

    private void addBlockData(){
        switch (currentClass.getName()) {
            case "model.TestcaseModel":
                dialogAdd.textFields.get(0).setEnabled(false);  break;
            case "model.RequestModel":
                dialogAdd.textFields.get(0).setEnabled(false);
                dialogAdd.textFields.get(1).setEnabled(false); break;
            case "model.TesterModel":
                dialogAdd.textFields.get(2).setEnabled(false); break;
        }
    }

    private void prepareValueField(int i){
        switch (currentClass.getName()) {
            default:
            case "model.TesttypeModel":     createTesttypeField(i);  break;
            case "model.TestcaseModel":     createTraningField(i);  break;
            case "model.RequestModel":  createRequestField(i);  break;
            case "model.TesterModel":      createSimpleField(i); break;
            case "model.UserModel":         createUserField(i); break;
            case "model.TestmachineModel":          createSimpleField(i); break;
            case "model.TesterTestcaseModel":  createTesterTestcaseField(i);  break;
            case "model.UserTesttypeModel":     createUserTesttypeField(i);  break;
        }
    }

    private void addComboBox(int i, ArrayList<String> values){
        dialogAdd.addComboBox(false, 150, 30, 50, 40 + (i - 1) * 80);
        JButton button = dialogAdd.addButton(30, 30, 215, 40 + (i - 1) * 80, "...");
        addBoxListener(i, button);
        for (int j = 0; j < values.size(); j++) {
            dialogAdd.comboBoxes.get(dialogAdd.comboBoxes.size()-1).addItem(values.get(j));
        }
    }

    private void addBoxListener(int i, JButton label){
        switch (currentClass.getName()) {
            case "model.TestcaseModel":     createTraningButton(i, label);  break;
            case "model.RequestModel":  createRequestButton(i, label);  break;
            case "model.TesterTestcaseModel":  createTesterTestcaseButton(i, label);  break;
            case "model.UserTesttypeModel":     createUserTesttypeButton(i, label);  break;
        }
    }

    private void createUserTesttypeButton(int i, JButton label){
        if (i == 1) {
            ActionListener actionListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    OneTableDialog testtypeDialog = new OneTableDialog(frameDialog, UserModel.class, "Пользователи");
                    testtypeDialog.create();
                    dialogAdd.dispose();
                }
            };
            label.addActionListener(actionListener);
        } else {

            ActionListener actionListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    OneTableDialog testtypeDialog = new OneTableDialog(frameDialog, TesttypeModel.class, "ВидыТестирования");
                testtypeDialog.create();
                    dialogAdd.dispose();
                }
            };
            label.addActionListener(actionListener);
        }
    }

    private void createTesterTestcaseButton(int i, JButton label){
        if (i == 1) {
            ActionListener actionListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    OneTableDialog testtypeDialog = new OneTableDialog(frameDialog, TesterModel.class, "Тестировщика");
                    testtypeDialog.create();
                    dialogAdd.dispose();
                }
            };
            label.addActionListener(actionListener);
        } else {

            ActionListener actionListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    OneTableDialog testtypeDialog = new OneTableDialog(frameDialog, TestcaseModel.class, "Тесткейсы");
                testtypeDialog.create();
                    dialogAdd.dispose();
                }
            };
            label.addActionListener(actionListener);
        }
    }

    private void createTraningButton(int i, JButton label){
        if (i == 6) {
            ActionListener actionListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    OneTableDialog testtypeDialog = new OneTableDialog(frameDialog, TesttypeModel.class, "ВидыТестированияи");
                    testtypeDialog.create();
                    dialogAdd.dispose();
                }
            };
            label.addActionListener(actionListener);
        } else {

            ActionListener actionListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    OneTableDialog testtypeDialog = new OneTableDialog(frameDialog, TestmachineModel.class, "Устройства");
                testtypeDialog.create();
                    dialogAdd.dispose();
                }
            };
            label.addActionListener(actionListener);
        }
    }

    private void createRequestButton(int i, JButton label){
        if (i == 5) {
            ActionListener actionListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    OneTableDialog testtypeDialog = new OneTableDialog(frameDialog, TestcaseModel.class, "Тесткейсы");
                    testtypeDialog.create();
                    dialogAdd.dispose();
                }
            };
            label.addActionListener(actionListener);
        } else {

            ActionListener actionListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    OneTableDialog testtypeDialog = new OneTableDialog(frameDialog, UserModel.class, "Пользователи");
                    testtypeDialog.create();
                    dialogAdd.dispose();
                }
            };
            label.addActionListener(actionListener);
        }
    }

    private void createUserField(int i){
        ArrayList<String> values = new ArrayList<>();
        if(i == 3) {
            values.add("Пользователь");
            values.add("Тестер");
            values.add("Руководитель");
        }else {
            createSimpleField(i);
            return;
        }
        addComboBox(i, values);
    }

    private void createTesttypeField(int i){
        ArrayList<String> values = new ArrayList<>();
        if(i == 2) {
            values = getTesttypeValues(DataPreparer.getTesttypes("", ""));
        }else {
            createSimpleField(i);
            return;
        }
        addComboBox(i, values);
    }

    private void createTraningField(int i){
        ArrayList<String> values = new ArrayList<>();
        if(i == 6){
            values = getTesttypeValues(DataPreparer.getTesttypes("", ""));
        } else if(i == 7){
            values = getTestmachineValues(DataPreparer.getTestmachines("", ""));
        }else {
            createSimpleField(i);
            return;
        }
        addComboBox(i, values);
    }

    private void createRequestField(int i){
        ArrayList<String> values = new ArrayList<>();
        if(i == 5){
            values = getTestcaseValues(DataPreparer.getTestcases("", ""));
        } else if(i == 6) {
            values = getUserValues(DataPreparer.getUsers("", ""));
        }else {
            createSimpleField(i);
            return;
        }
        addComboBox(i, values);
    }

    private void createTesterTestcaseField(int i){
        ArrayList<String> values = new ArrayList<>();
        if(i == 1){
            values = getTesterValues(DataPreparer.getTesters("", ""));
        } else if(i == 2){
            values = getTestcaseValues(DataPreparer.getTestcases("", ""));
        }else {
            createSimpleField(i);
            return;
        }
        addComboBox(i, values);
    }

    private void createUserTesttypeField(int i){
        ArrayList<String> values = new ArrayList<>();
        if(i == 1){
            values = getUserValues(DataPreparer.getUsers("", ""));
        } else if(i == 2){
            values = getTesttypeValues(DataPreparer.getTesttypes("", ""));
        } else {
            createSimpleField(i);
            return;
        }
        addComboBox(i, values);
    }

    private ArrayList<String> getTesttypeValues(ArrayList<Model> models){
        ArrayList<String> values = new ArrayList<>();
        for (int i = 0; i < models.size(); i++) {
            values.add(models.get(i).getId() + ") " + ((TesttypeModel)models.get(i)).getName());
        }
        return values;
    }

    private ArrayList<String> getUserValues(ArrayList<Model> models){
        ArrayList<String> values = new ArrayList<>();
        for (int i = 0; i < models.size(); i++) {
            values.add(models.get(i).getId() + ") " + ((UserModel)models.get(i)).getLogin());
        }
        return values;
    }

    private ArrayList<String> getTestcaseValues(ArrayList<Model> models){
        ArrayList<String> values = new ArrayList<>();
        for (int i = 0; i < models.size(); i++) {
            values.add(models.get(i).getId() + ") " + ((TestcaseModel)models.get(i)).getName());
        }
        return values;
    }

    private ArrayList<String> getTesterValues(ArrayList<Model> models){
        ArrayList<String> values = new ArrayList<>();
        for (int i = 0; i < models.size(); i++) {
            values.add(models.get(i).getId() + ") " + ((TesterModel)models.get(i)).getName());
        }
        return values;
    }

    private ArrayList<String> getTestmachineValues(ArrayList<Model> models){
        ArrayList<String> values = new ArrayList<>();
        for (int i = 0; i < models.size(); i++) {
            values.add(models.get(i).getId() + ") " + ((TestmachineModel)models.get(i)).getName());
        }
        return values;
    }

    private void createSimpleField(int i){
        dialogAdd.addTextField(200, 30, 50, 40 + (i - 1) * 80);
    }

    private String[] getColumns(){
        switch (currentClass.getName()) {
            default:
            case "model.TesttypeModel":     return TesttypeModel.COLUMNS_BY_RUS;
            case "model.TestcaseModel":      return TestcaseModel.COLUMNS_BY_RUS;
            case "model.RequestModel":     return RequestModel.COLUMNS_BY_RUS;
            case "model.TesterModel":     return TesterModel.COLUMNS_BY_RUS;
            case "model.UserModel":         return UserModel.COLUMNS_BY_RUS;
            case "model.TestmachineModel":    return TestmachineModel.COLUMNS_BY_RUS;
            case "model.TesterTestcaseModel":  return TesterTestcaseModel.COLUMNS_BY_RUS;
            case "model.UserTesttypeModel":     return UserTesttypeModel.COLUMNS_BY_RUS;
        }
    }

    protected void addDataLast(){
        String where = " `" + Validator.getCurrentTable(currentClass.getName()) + "`.id = ? @@@" + frameDialog.textFields.get(0).getText() + "@@@" + " ";

        ArrayList<Model> result = getData(where);
        if(result == null){
            JOptionPane.showMessageDialog(dialogAdd, "Error поиска!", "Error!", JOptionPane.PLAIN_MESSAGE);
            dialogAdd.dispose();
        }
        Model model = result.get(0);
        addOneLastData(model);
    }

    private ArrayList<Model> getData(String where){
        switch (currentClass.getName()) {
            default:
            case "model.TesttypeModel":     return DataPreparer.getTesttypes("", where);
            case "model.TestcaseModel":      return DataPreparer.getTestcases("", where);
            case "model.RequestModel":     return DataPreparer.getRequests("", where);
            case "model.TesterModel":     return DataPreparer.getTesters("", where);
            case "model.UserModel":         return DataPreparer.getUsers("", where);
            case "model.TestmachineModel":    return DataPreparer.getTestmachines("", where);
            case "model.TesterTestcaseModel":      return DataPreparer.getTestersTestcases("", where);
            case "model.UserTesttypeModel":         return DataPreparer.getUserTesttypes("", where);
        }
    }

    private void addOneLastData(Model model){
        switch (currentClass.getName()) {
            default:
            case "model.TesttypeModel":     addOneLastDataTesttype((TesttypeModel) model); break;
            case "model.TestcaseModel":      addOneLastDataTestcase((TestcaseModel) model); break;
            case "model.RequestModel":     addOneLastDataRequest((RequestModel) model); break;
            case "model.TesterModel":     addOneLastDataTester((TesterModel) model); break;
            case "model.UserModel":         addOneLastDataUser((UserModel) model); break;
            case "model.TestmachineModel":    addOneLastDataTestmachine((TestmachineModel) model); break;
            case "model.TesterTestcaseModel":      addOneLastDataTesterTestcase((TesterTestcaseModel) model); break;
            case "model.UserTesttypeModel":         addOneLastDataUserTesttype((UserTesttypeModel) model); break;
        }
    }

    private void addOneLastDataTesttype(TesttypeModel model) {
        dialogAdd.textFields.get(0).setText(model.getName());
        for (int i = 0; i < dialogAdd.comboBoxes.get(0).getItemCount(); i++) {
            if(dialogAdd.comboBoxes.get(0).getItemAt(i).equals(model.getId() + ") " + model.getName())){
                dialogAdd.comboBoxes.get(0).removeItemAt(i);
                dialogAdd.comboBoxes.get(0).insertItemAt( "0) Верхоуровневая", i);

            }
        }
        dialogAdd.comboBoxes.get(0).setSelectedItem(model.getParent_testtype_id());
    }

    private void addOneLastDataTestcase(TestcaseModel model) {
        dialogAdd.textFields.get(0).setText(model.getName());
        dialogAdd.textFields.get(1).setText(model.getDescription());
        dialogAdd.textFields.get(2).setText(String.valueOf(model.getPrice()));
        dialogAdd.textFields.get(3).setText(String.valueOf(model.getDuration()));
        dialogAdd.textFields.get(4).setText(model.getAurimatization());

        dialogAdd.comboBoxes.get(0).setSelectedItem(model.getTesttype_id());
        dialogAdd.comboBoxes.get(1).setSelectedItem(model.getTestmachine_id());
    }

    private void addOneLastDataRequest(RequestModel model) {
        dialogAdd.textFields.get(0).setText(model.getDate());
        dialogAdd.textFields.get(1).setText(model.getPhone());
        dialogAdd.textFields.get(2).setText(model.getMail());
        dialogAdd.textFields.get(3).setText(String.valueOf(model.getImportance()));

        dialogAdd.comboBoxes.get(0).setSelectedItem(model.getTestcase_id());
        dialogAdd.comboBoxes.get(1).setSelectedItem(model.getUser_id());
    }

    private void addOneLastDataTester(TesterModel model) {
        dialogAdd.textFields.get(0).setText(model.getName());
        dialogAdd.textFields.get(1).setText(model.getSpeciality());
        dialogAdd.textFields.get(2).setText(model.getDate_of_birth());
    }

    private void addOneLastDataUser(UserModel model) {
        dialogAdd.textFields.get(0).setText(model.getLogin());
        dialogAdd.textFields.get(1).setText(model.getPassword());
        dialogAdd.comboBoxes.get(0).setSelectedItem(model.getRole());
    }

    private void addOneLastDataTestmachine(TestmachineModel model) {
        dialogAdd.textFields.get(0).setText(model.getName());
        dialogAdd.textFields.get(1).setText(model.getIpipaddress());

    }

    private void addOneLastDataTesterTestcase(TesterTestcaseModel model) {
        dialogAdd.comboBoxes.get(0).setSelectedItem(model.getTester_id());
        dialogAdd.comboBoxes.get(1).setSelectedItem(model.getTestcase_id());
    }

    private void addOneLastDataUserTesttype(UserTesttypeModel model) {
        dialogAdd.comboBoxes.get(0).setSelectedItem(model.getUser_id());
        dialogAdd.comboBoxes.get(1).setSelectedItem(model.getTesttype_id());
    }

    protected void addEventAdd(final boolean isRed, JButton button){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(changeAndCheckOk()){
                    addData(isRed, generateNewModel());
                    parental.updateData();
                    dialogAdd.dispose();
                }
            }
        };
        button.addActionListener(actionListener);
    }

    protected void addEventRemove(JButton button){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(changeAndCheckOk()){
                    parental.currentDialog.buttons.get(2).doClick();
                    dialogAdd.dispose();
                }
            }
        };
        button.addActionListener(actionListener);
    }

    private void addData(boolean isRed, Model model){
        String error;
        if(isRed){
            if (!"".equals(error = getQueryUpdate(model))) {
                JOptionPane.showMessageDialog(dialogAdd, "Error редактирования: " + error, "Error!", JOptionPane.PLAIN_MESSAGE);
            }
        } else {
            if (!"".equals(error = getQueryInsert(model))) {
                JOptionPane.showMessageDialog(dialogAdd, "Error добавления: " + error, "Error!", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }


    private Model generateNewModel(){
        switch (currentClass.getName()) {
            default:
            case "model.TesttypeModel":     return generateNewTesttype();
            case "model.TestcaseModel":      return generateNewTestcase();
            case "model.RequestModel":     return generateNewRequest();
            case "model.TesterModel":     return generateNewTester();
            case "model.UserModel":         return generateNewUser();
            case "model.TestmachineModel":    return generateNewTestmachine();
            case "model.TesterTestcaseModel":      return generateNewTesterTestcase();
            case "model.UserTesttypeModel":         return generateNewUserTesttype();
        }
    }

    private Model generateNewTesttype() {
        TesttypeModel model = new TesttypeModel(
                0,
                dialogAdd.textFields.get(0).getText(),
                dialogAdd.comboBoxes.get(0).getSelectedItem().toString()
        );
        return model;
    }

    private Model generateNewTestcase() {
        TestcaseModel model = new TestcaseModel(
                0,
                dialogAdd.textFields.get(0).getText(),
                dialogAdd.textFields.get(1).getText(),
                Double.valueOf(dialogAdd.textFields.get(2).getText()),
                Integer.valueOf(dialogAdd.textFields.get(3).getText()),
                dialogAdd.textFields.get(4).getText(),
                dialogAdd.comboBoxes.get(0).getSelectedItem().toString(),
                dialogAdd.comboBoxes.get(1).getSelectedItem().toString()
        );
        return model;
    }

    private Model generateNewRequest() {
        RequestModel model = new RequestModel(
                0,
                dialogAdd.textFields.get(0).getText(),
                dialogAdd.textFields.get(1).getText(),
                dialogAdd.textFields.get(2).getText(),
                Integer.valueOf(dialogAdd.textFields.get(3).getText()),
                dialogAdd.comboBoxes.get(0).getSelectedItem().toString(),
                dialogAdd.comboBoxes.get(1).getSelectedItem().toString()
        );
        return model;
    }

    private Model generateNewTester() {
        TesterModel model = new TesterModel(
                0,
                dialogAdd.textFields.get(0).getText(),
                dialogAdd.textFields.get(1).getText(),
                dialogAdd.textFields.get(2).getText()
        );
        return model;
    }

    private Model generateNewUser() {
        UserModel model = new UserModel(
                0,
                dialogAdd.textFields.get(0).getText(),
                dialogAdd.textFields.get(1).getText(),
                dialogAdd.comboBoxes.get(0).getSelectedItem().toString()
        );
        return model;
    }

    private Model generateNewTestmachine() {
        TestmachineModel model = new TestmachineModel(
                0,
                dialogAdd.textFields.get(0).getText(),
                dialogAdd.textFields.get(1).getText()
        );
        return model;
    }

    private Model generateNewTesterTestcase() {
        TesterTestcaseModel model = new TesterTestcaseModel(
                0,
                dialogAdd.comboBoxes.get(0).getSelectedItem().toString(),
                dialogAdd.comboBoxes.get(1).getSelectedItem().toString()
        );
        return model;
    }

    private Model generateNewUserTesttype() {
        UserTesttypeModel model = new UserTesttypeModel(
                0,
                dialogAdd.comboBoxes.get(0).getSelectedItem().toString(),
                dialogAdd.comboBoxes.get(1).getSelectedItem().toString()
        );
        return model;
    }

    private boolean changeAndCheckOk(){
        switch (currentClass.getName()) {
            default:
            case "model.TesttypeModel":     return checkAddOk(TesttypeModel.COLUMNS_BY_RUS, TesttypeModel.IS_STR);
            case "model.TestcaseModel":      return checkAddOk(TestcaseModel.COLUMNS_BY_RUS, TestcaseModel.IS_STR);
            case "model.RequestModel":     return checkAddOk(RequestModel.COLUMNS_BY_RUS, RequestModel.IS_STR);
            case "model.TesterModel":     return checkAddOk(TesterModel.COLUMNS_BY_RUS, TesterModel.IS_STR);
            case "model.UserModel":         return checkAddOk(UserModel.COLUMNS_BY_RUS, UserModel.IS_STR);
            case "model.TestmachineModel":    return checkAddOk(TestmachineModel.COLUMNS_BY_RUS, TestmachineModel.IS_STR);
            case "model.TesterTestcaseModel":      return checkAddOk(TesterTestcaseModel.COLUMNS_BY_RUS, TesterTestcaseModel.IS_STR);
            case "model.UserTesttypeModel":         return checkAddOk(UserTesttypeModel.COLUMNS_BY_RUS, UserTesttypeModel.IS_STR);
        }
    }


    private boolean checkAddOk(String columns[], boolean isStrColumns[]){
        String message = "";
        for (int i = 0; i < isStrColumns.length; i++) {
            if(!isSpecialColumn(i)) {
                if(i < dialogAdd.textFields.size()) {
                    if (isStrColumns[i]) {
                        if ("".equals(dialogAdd.textFields.get(i).getText().trim())) {
                            message += "  " + columns[i + 1] + " пусто;\r\n";
                        }
                    } else {
                        if (!Validator.isInt(dialogAdd.textFields.get(i).getText())) {
                            message += "  " + columns[i + 1] + " не целое число;\r\n";
                        }
                    }
                }
            }
        }
        message += checkAddOkSpecial();
        if(!message.equals("")) JOptionPane.showMessageDialog(dialogAdd, "Errors:\r\n" + message, "Error!", JOptionPane.PLAIN_MESSAGE);
        return message.equals("") ? true : false;
    }

    private boolean isSpecialColumn(int i){
        boolean answer = false;
        if(currentClass.getName().equals("model.RequestModel"))
            if (i == 0 || i == 2)
                answer = true;
        if(currentClass.getName().equals("model.TesterModel"))
            if(i == 2)
                answer = true;
        if(currentClass.getName().equals("model.TestcaseModel"))
            if(i == 2)
                answer = true;
        if(currentClass.getName().equals("model.UserModel"))
            if(i == 2)
                answer = true;

        return answer;
    }

    private String checkAddOkSpecial(){
        String message = "";
        if(currentClass.getName().equals("model.RequestModel")) {
            if (!Validator.isDateNow(dialogAdd.textFields.get(0).getText()))
                message += "  Некторректная дата;\r\n";
            if (!Validator.isEMail(dialogAdd.textFields.get(2).getText()))
                message += "  Некторректный email;\r\n";
        }
        if(currentClass.getName().equals("model.TesterModel"))
            if(!Validator.isDate(dialogAdd.textFields.get(2).getText()))
                message += "  Некторректная дата;\r\n";
        if(currentClass.getName().equals("model.TestcaseModel"))
            if(!Validator.isDouble(dialogAdd.textFields.get(2).getText()))
                message += "  Цена не число;\r\n";
        return message;
    }

}
