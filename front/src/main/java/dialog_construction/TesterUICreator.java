package dialog_construction;


import dialogs.DialogComponentСreator;
import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TesterUICreator extends UICreator {


    public TesterUICreator(UserModel currentUser) {
        super(currentUser);
    }

    @Override
    public void prepareFrameDialog(){
        prepareMenu();
        frameDialog.setSize(800, 470);
        frameDialog.setTitle(frameDialog.getTitle() + " Тестировщик!");
        frameDialog.addLabel(700, 400, 50, 5, "");
        DialogComponentСreator.setIcon(frameDialog.labels.get(0), "logo.png");
    }

    private void prepareMenu(){
        JMenuBar menuBar = new JMenuBar();
        createMenu(menuBar);

        JMenuItem item1 = new JMenuItem("Выход");
        setEventExit(item1);
        menuBar.add(item1);

        frameDialog.setJMenuBar(menuBar);
    }

    protected void createMenu(JMenuBar menuBar) {
        JMenu menuChange = new  JMenu("Управление данными");
        JMenuItem item1 = new JMenuItem("ВидыТестирования");
        setEventTesttype(item1);
        menuChange.add(item1);

        JMenuItem item2 = new JMenuItem("Тесткейсы");
        setEventTestcase(item2);
        menuChange.add(item2);

        JMenuItem item3 = new JMenuItem("Заявки");
        setEventRequest(item3);
        menuChange.add(item3);

        JMenuItem item4 = new JMenuItem("Тестировщики");
        setEventTester(item4);
        menuChange.add(item4);

        JMenuItem item5 = new JMenuItem("Устройства");
        setEventTestmachine(item5);
        menuChange.add(item5);

        JMenuItem item6 = new JMenuItem("Тестировщик На Тесткейсах");
        setEventTesterTestcase(item6);
        menuChange.add(item6);

        JMenuItem item7 = new JMenuItem("ВидыТестирования Пользователей");
        setEventTesttypeUser(item7);
        menuChange.add(item7);

        menuBar.add(menuChange);
    }


    protected void setEventTesttype(JMenuItem item){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OneTableDialog testtypeDialog = new OneTableDialog(frameDialog, TesttypeModel.class, "\"ВидыТестированияи\"");
                testtypeDialog.create();
            }
        };
        item.addActionListener(actionListener);
    }

    protected void setEventTestcase(JMenuItem item){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OneTableDialog testtypeDialog = new OneTableDialog(frameDialog, TestcaseModel.class, "\"Тесткейсы\"");
                testtypeDialog.create();
            }
        };
        item.addActionListener(actionListener);
    }

    protected void setEventRequest(JMenuItem item){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OneTableDialog testtypeDialog = new OneTableDialog(frameDialog, RequestModel.class, "\"Заявки\"");
                testtypeDialog.create();
            }
        };
        item.addActionListener(actionListener);
    }

    protected void setEventTester(JMenuItem item){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OneTableDialog testtypeDialog = new OneTableDialog(frameDialog, TesterModel.class, "\"Тестировщика\"");
                testtypeDialog.create();
            }
        };
        item.addActionListener(actionListener);
    }

    protected void setEventTestmachine(JMenuItem item){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OneTableDialog testtypeDialog = new OneTableDialog(frameDialog, TestmachineModel.class, "\"Устройства\"");
                testtypeDialog.create();
            }
        };
        item.addActionListener(actionListener);
    }

    protected void setEventTesterTestcase(JMenuItem item){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OneTableDialog testtypeDialog = new OneTableDialog(frameDialog, TesterTestcaseModel.class, "\"Тестировщики-Тесткейсы\"");
                testtypeDialog.create();
            }
        };
        item.addActionListener(actionListener);
    }

    protected void setEventTesttypeUser(JMenuItem item){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OneTableDialog testtypeDialog = new OneTableDialog(frameDialog, UserTesttypeModel.class, "\"ВидыТестирования-Пользователи\"");
                testtypeDialog.create();
            }
        };
        item.addActionListener(actionListener);
    }

    protected ActionListener setEventExit(JMenuItem item){
        ActionListener actionListener = super.setEventExit();
        item.addActionListener(actionListener);
        return actionListener;
    }
}
