package listeners;

import connection.DataPreparer;
import dialogs.CDialog;
import dialogs.CDialogNotFrame;
import model.Model;
import model.TestcaseModel;
import model.RequestModel;
import model.UserModel;
import table_models.TestcaseTableModel;
import validation.Validator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BBuyListener implements ActionListener {
    private ArrayList<Model> allTestcases;
    private CDialogNotFrame dialogBuy;
    private CDialog frameDialog;
    private UserModel user;

    public BBuyListener(CDialog frameDialog, ArrayList<Model> allTestcases, UserModel user){
        super();
        this.frameDialog = frameDialog;
        this.allTestcases = allTestcases;
        this.user = user;
    }

    public void actionPerformed(ActionEvent e) {
        TestcaseModel selectedTestcase ;
        if((selectedTestcase = messageOk()) == null) return;

        prepareBuyData(selectedTestcase);
        setEventBuy(Integer.valueOf(selectedTestcase.getId()));
        dialogBuy.setVisible(true);
    }

    private TestcaseModel messageOk(){
        if(Validator.isInt(frameDialog.textFields.get(0).getText())){
            int myId = Integer.valueOf(frameDialog.textFields.get(0).getText());

            for (int i = 0; i < allTestcases.size(); i++){
                if(allTestcases.get(i).getId() == myId){
                    return (TestcaseModel)allTestcases.get(i);
                }
            }
        }

        JOptionPane.showMessageDialog(dialogBuy, "Введите правильный номер!", "Error!", JOptionPane.PLAIN_MESSAGE);
        return null;
    }

    private void prepareBuyData(TestcaseModel selectedTestcase){
        dialogBuy = new CDialogNotFrame("Заявка на тестирование", 300, 430, true);

        dialogBuy.addLabel(100, 30,50, 20, "Ваш Товар:");
        dialogBuy.addTextField(200, 30,50, 50);
        addTestcase(selectedTestcase);
        dialogBuy.addLabel(100, 30,50, 100, "Ваш email:");
        dialogBuy.addTextField(200, 30,50, 130);
        dialogBuy.addLabel(100, 30,50, 180, "Ваш Телефон:");
        dialogBuy.addTextField(200, 30,50, 210);
        dialogBuy.addLabel(100, 30,50, 260, "Приоритет:");
        dialogBuy.addTextField(200, 30,50, 290);

        dialogBuy.addButton(200, 30,50, 340, "Заявка на тестирование");

    }

    private void addTestcase(TestcaseModel testcase){
        String stringTestcase = testcase.getAurimatization() + " \"" + testcase.getName() + "\" Цена: " + testcase.getPrice();
        dialogBuy.textFields.get(0).setText(stringTestcase);
        dialogBuy.textFields.get(0).setEnabled(false);

    }

    private void setEventBuy( int id){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int importance = Integer.valueOf(dialogBuy.textFields.get(3).getText());
                if(chectAddOk(importance)){
                    String messageToUser;
                    if(addNewRequest(importance, id)) {
                        frameDialog.textFields.get(0).setText("");
                        messageToUser = "Заявка отправлена, спасибо!";
                    } else {
                        messageToUser = "Error добавления покупки в Базу Данных!";
                    }
                    JOptionPane.showMessageDialog(dialogBuy, messageToUser, "Операция завершена!", JOptionPane.PLAIN_MESSAGE);
                    dialogBuy.dispose();
                }
            }
        };
        dialogBuy.buttons.get(0).addActionListener(actionListener);
    }


    private boolean addNewRequest(int importance, int id){
        int user_id = user.getId();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        RequestModel request = new RequestModel(0,sdf.format(new Date()), dialogBuy.textFields.get(2).getText().trim(),
                dialogBuy.textFields.get(1).getText().trim(), importance, id + ") ", user_id + ") ");

       if("@ok@".equals(DataPreparer.insert(request, "requests"))) return true;
       else return false;

    }

    private boolean chectAddOk(int importance){
        String message = "";

        if(!Validator.isEMail(dialogBuy.textFields.get(1).getText().trim()))
            message += "  E-mail не корректен;\r\n";
        if("".equals(dialogBuy.textFields.get(2).getText().trim()))
            message += "  Адрес пуст пусто;\r\n";
        if(!Validator.isInt(dialogBuy.textFields.get(3).getText())){
            message += "  Приоритет - не число!;\r\n";
        } else if(Integer.valueOf(dialogBuy.textFields.get(3).getText()) <= 0 || Integer.valueOf(dialogBuy.textFields.get(3).getText()) > importance) {
            message += "  Приоритет - в недопустимых пределах!;\r\n";
        }

        if(!message.equals("")) JOptionPane.showMessageDialog(dialogBuy, "Errors: \r\n" + message, "Error!", JOptionPane.PLAIN_MESSAGE);
        return message.equals("") ? true : false;
    }


}
