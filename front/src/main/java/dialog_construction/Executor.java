package dialog_construction;

import connection.DataPreparer;
import dialogs.CDialog;
import model.UserModel;
import validation.Validator;


import javax.swing.*;
import java.awt.event.*;

public class Executor {

    private CDialog frameDialog = null;
    private CDialog connectDialog = null;
    private CDialog authorizationjDialog = null;
    private UserModel currentUser = null;
    private boolean isSmallSize = true;

    public void getStarted(){
        connectDialog = new CDialog("Как подключиться к серверу?", 400, 300);
        prepareConnectionDialog();
        setEventsForConnectionDialog();
        connectDialog.setVisible(true);
    }


    private void prepareConnectionDialog(){
        connectDialog.addLabel(300, 30,50, 20, "Адрес:");
        connectDialog.addTextField(300, 30,50, 50);
        connectDialog.addLabel(300, 30,50, 100, "Порт:");
        connectDialog.addTextField(300, 30,50, 130);
        connectDialog.addButton(300, 30, 50, 180, "Войти");

        connectDialog.textFields.get(0).setText("127.0.0.1");
        connectDialog.textFields.get(1).setText("8999");
    }

    public void setEventsForConnectionDialog() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String host = connectDialog.textFields.get(0).getText();
                String port = connectDialog.textFields.get(1).getText();
                if( !Validator.isHost(host) || !Validator.isPort(port)) {
                    JOptionPane.showMessageDialog(connectDialog, "Некорректные данные", "Упс!", JOptionPane.PLAIN_MESSAGE);
                } else {
                    String answer = DataPreparer.startConnection(host, Integer.valueOf(port));
                    if (!answer.equals("")) {
                        JOptionPane.showMessageDialog(connectDialog, answer, "Упс!", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        connectDialog.dispose();
                        createAuthorizationDialog();
                    }
                }

            }
        };
        connectDialog.buttons.get(0).addActionListener(actionListener);
    }

    private void createAuthorizationDialog(){
        authorizationjDialog = new CDialog("Вход в Систему!", 400, 310);
        Validator.setSize(authorizationjDialog, 50);
        prepareAuthorizationDialog();
        setEventsForAuthorizationDialog();
        setEventsForCloseAuthorizationDialog();
        authorizationjDialog.setVisible(true);
    }

    private void prepareAuthorizationDialog(){
        authorizationjDialog.addLabel(300, 30,50, 0, "АВТОРИЗАЦИЯ!");
        authorizationjDialog.addLabel(300, 30,50, 20, "Логин:");
        authorizationjDialog.addTextField(300, 30,50, 50);
        authorizationjDialog.addLabel(300, 30,50, 100, "Пароль:");
        authorizationjDialog.addPasswordField(300, 30,50, 130);
        authorizationjDialog.addButton(300, 30, 50, 180, "Войти");

        authorizationjDialog.addLine(300, 30, 50, 270);

        authorizationjDialog.addLabel(300, 30,50, 290, "РЕГИСТРАЦИЯ!");
        authorizationjDialog.addLabel(300, 30,50, 310, "Логин:");
        authorizationjDialog.addTextField(300, 30,50, 340);
        authorizationjDialog.addLabel(300, 30,50, 390, "Пароль:");
        authorizationjDialog.addPasswordField(300, 30,50, 420);
        authorizationjDialog.addLabel(300, 30,50, 470, "Подтвердите пароль::");
        authorizationjDialog.addPasswordField(300, 30,50, 500);
        authorizationjDialog.addButton(300, 30, 50, 550, "Зарегестрироваться");


        authorizationjDialog.addButton(300, 30,50, 230, "Нет аккаунта? Регистрация");

    }

    public void setEventsForAuthorizationDialog() {
        setEventAuth();
        setEventRegistry();
        setEventShowRegistry();
    }

    public void setEventAuth(){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(isCorrectLogAndPass()) {
                    if((currentUser = getAuthorisationDataFromServer()) != null){
                        createFrameDialog();
                        clearDialogData();
                    } else {
                        JOptionPane.showMessageDialog(authorizationjDialog, "Неверный логин и\\или пароль", "Упс!", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        };
        authorizationjDialog.buttons.get(0).addActionListener(actionListener);
    }

    public void setEventRegistry(){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(isCorrectRegistry()) {
                    if((currentUser = getRegistryDataFromServer()) != null){
                        createFrameDialog();
                        clearDialogData();
                    } else {
                        JOptionPane.showMessageDialog(authorizationjDialog, "Логин занят!", "Упс!", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        };
        authorizationjDialog.buttons.get(1).addActionListener(actionListener);
    }

    public void setEventShowRegistry(){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int height;
                String buttonMessage;
                if(isSmallSize) {
                    height = 640;
                    buttonMessage = "Скрыть регистрацию!";
                } else {
                    height = 310;
                    buttonMessage = "Нет аккаунта? Регистрация";
                }
                authorizationjDialog.setSize(400, height);
                authorizationjDialog.buttons.get(2).setText(buttonMessage);
                isSmallSize = !isSmallSize;
            }
        };
        authorizationjDialog.buttons.get(2).addActionListener(actionListener);
    }

    private void clearDialogData(){
        authorizationjDialog.textFields.get(0).setText("");
        authorizationjDialog.passwordFields.get(0).setText("");
        authorizationjDialog.textFields.get(1).setText("");
        authorizationjDialog.passwordFields.get(1).setText("");
        authorizationjDialog.passwordFields.get(2).setText("");
        authorizationjDialog.dispose();
    }

    private void createFrameDialog() {
        DirectorUI directorUI = new DirectorUI(authorizationjDialog);
        System.out.println(currentUser.getRole());
        if(currentUser.getRole().equals("0")) {
            directorUI.setDialogCreator(new BossUICreator(currentUser));
        } else if(currentUser.getRole().equals("1")){
            directorUI.setDialogCreator(new TesterUICreator(currentUser));
        } else {
            directorUI.setDialogCreator(new UserUICreator(currentUser));
        }
        directorUI.createDialog();
        frameDialog = directorUI.getDialog();
    }

    public void setEventsForCloseAuthorizationDialog() {
        WindowListener windowListener = new WindowListener() {
            public void windowActivated(WindowEvent event) {}
            public void windowClosed(WindowEvent event) {}
            public void windowDeactivated(WindowEvent event) {}
            public void windowDeiconified(WindowEvent event) {}
            public void windowIconified(WindowEvent event) {}
            public void windowOpened(WindowEvent event) {}

            public void windowClosing(WindowEvent event) {
                String answer = DataPreparer.exit("witchout");
                if(!answer.equals("")) {
                    JOptionPane.showMessageDialog(authorizationjDialog, answer, "Упс!", JOptionPane.PLAIN_MESSAGE);
                }
            }
        };
        authorizationjDialog.addWindowListener(windowListener);
    }

    private boolean isCorrectLogAndPass(){
        boolean isOk = true;
        String login, password, errors = "";
        login = authorizationjDialog.textFields.get(0).getText().trim();
        password = authorizationjDialog.passwordFields.get(0).getText().trim();
        if("".equals(login) || login.length() < 3){
            errors += "Логин пуст, либо слишком мал!\n";
            isOk = false;
        }
        if("".equals(login) || password.length() < 3){
            errors += "Пароль пуст, либо слишком мал!\n";
            isOk = false;
        }
        if(!Validator.isHaveOnlyGoodChars(login) ||  !Validator.isHaveOnlyGoodChars(password)){
            errors += "Содержатся запрещённые символы\n";
            isOk = false;
        }
        if (!isOk) JOptionPane.showMessageDialog(authorizationjDialog, errors, "Error ввода данных!", JOptionPane.PLAIN_MESSAGE);
        return isOk;
    }

    private boolean isCorrectRegistry(){
        boolean isOk = true;
        String login, firstPassword, confirmPassword, errors = "";
        login = authorizationjDialog.textFields.get(1).getText().trim();
        firstPassword = authorizationjDialog.passwordFields.get(1).getText().trim();
        confirmPassword = authorizationjDialog.passwordFields.get(2).getText().trim();
        if("".equals(login) || login.length() < 3){
            errors += "Логин пуст, либо слишком мал для регистрации!\n";
            isOk = false;
        }
        if("".equals(firstPassword) || firstPassword.length() < 3){
            errors += "Пароль пуст, либо слишком мал для регистрации!\n";
            isOk = false;
        }
        if(!firstPassword.equals(confirmPassword)){
            errors += "Пароли не совпадают!\n";
            isOk = false;
        }
        if(!Validator.isHaveOnlyGoodChars(login) || !Validator.isHaveOnlyGoodChars(firstPassword)){
            errors += "Содержатся запрещённые символы в логине\\пароле!\n";
            isOk = false;
        }
        if (!isOk) JOptionPane.showMessageDialog(authorizationjDialog, errors, "Error ввода данных!", JOptionPane.PLAIN_MESSAGE);
        return isOk;
    }

    private UserModel getAuthorisationDataFromServer(){
        UserModel user = new UserModel (
            0,
            authorizationjDialog.textFields.get(0).getText().trim(),
            authorizationjDialog.passwordFields.get(0).getText().trim(),
            "Пользователь"
        );

        return DataPreparer.checkAuthorization(user);
    }

    private UserModel getRegistryDataFromServer(){
        UserModel user = new UserModel (
            0,
            authorizationjDialog.textFields.get(1).getText().trim(),
            authorizationjDialog.passwordFields.get(1).getText().trim(),
            "Пользователь"
        );

        return DataPreparer.checkRegistry(user);
    }

}
