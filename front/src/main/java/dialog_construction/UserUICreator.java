package dialog_construction;

import connection.DataPreparer;
import listeners.*;
import model.*;
import table_models.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UserUICreator extends UICreator {
    private ArrayList<Model> allTestcases;

    public UserUICreator(UserModel currentUser) {
        super(currentUser);
    }

    @Override
    public void prepareFrameDialog(){
        prepareMenu();
        if((allTestcases = DataPreparer.getTestcases("", "")) == null) {
            JOptionPane.showMessageDialog(frameDialog, "Error получения данных!", "Error!",JOptionPane.PLAIN_MESSAGE);
            frameDialog.close();
        }
        frameDialog.setTitle(frameDialog.getTitle() + " Пользователь!");
        frameDialog.setSize(800, 300);

        frameDialog.addTable(750, 200, 25, 20, new TestcaseTableModel(allTestcases, true));
        frameDialog.addLabel(300, 30,250, 240, "Номер тесткейса для вас:");
        frameDialog.addTextField(300, 30,250, 270);
        frameDialog.addButton(300, 30,250, 320, "Сделать запрос");
        frameDialog.addButton(300, 30,250, 370, "Поиск");
        frameDialog.addButton(300, 30,250, 420, "Сортировка");
        frameDialog.addButton(300, 30,250, 470, "Сброс");
        frameDialog.addButton(300, 30,250, 520, "Наши Преимущества");
        frameDialog.addButton(300, 30,250, 570, "ВЫЙТИ");
    }

    private void prepareMenu(){
        JMenuBar menuBar = new JMenuBar();

        JMenu menuChange = new  JMenu("Главное меню");
        JMenuItem item1 = new JMenuItem("Заявка на тестирование");
        addEventBuy(item1);
        menuChange.add(item1);

        JMenuItem item2 = new JMenuItem("Презентация");
        setEventShow(item2);
        menuChange.add(item2);

        menuChange.addSeparator();

        JMenuItem item3 = new JMenuItem("Выйти");
        setEventExit(item3);
        menuChange.add(item3);
        menuBar.add(menuChange);

        JMenu menuShow = new JMenu("Фильтрация");
        JMenuItem search = new JMenuItem("Поиск");
        setEventSearch(search);
        menuShow.add(search);
        JMenuItem sort = new JMenuItem("Сортировка");
        setEventSort(sort);
        menuShow.add(sort);
        JMenuItem reset = new JMenuItem("Сброс");
        setEventReset(reset);
        menuShow.add(reset);
        menuBar.add(menuShow);

        menuBar.add(menuShow);


        frameDialog.setJMenuBar(menuBar);
    }

    private void addMouseListener(){
        MouseAdapter mouseAdapter = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int rowId = frameDialog.tables.get(0).getSelectedRow();
                int id = (int)(frameDialog.tables.get(0).getModel()).getValueAt(rowId, 0);
                frameDialog.textFields.get(0).setText(id + "");

                if (e.getClickCount() == 2) {
                    frameDialog.buttons.get(0).doClick();
                }
            }
        };
        frameDialog.tables.get(0).addMouseListener(mouseAdapter);
    }

    public ArrayList<Model> updateData(){
        if((allTestcases = DataPreparer.getTestcases("", "")) == null) {
            JOptionPane.showMessageDialog(frameDialog, "Error получения данных!", "Error!",JOptionPane.PLAIN_MESSAGE);
            frameDialog.dispose();
        }
        frameDialog.tables.get(0).setModel(new TestcaseTableModel(allTestcases, true));
        return allTestcases;
    }

    private void addEventBuy(JMenuItem item){
        ActionListener actionListener2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameDialog.buttons.get(0).doClick();
            }
        };
        item.addActionListener(actionListener2);
    }


    private void setEventSearch(JMenuItem item){
        ActionListener actionListener = new BSearchListener(frameDialog, TestcaseModel.class);
        item.addActionListener(actionListener);
    }

    private void setEventSort(JMenuItem item){
        BSortListener actionListener = new BSortListener(frameDialog, TestcaseModel.class);
        item.addActionListener(actionListener);
    }


    private void setEventReset(JMenuItem item){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateData();
            }
        };
        item.addActionListener(actionListener);
    }

    private void setEventShow(JMenuItem item){
        BShowListener actionListener = new BShowListener(frameDialog);
        item.addActionListener(actionListener);
    }


    protected ActionListener setEventExit(JMenuItem item){
        ActionListener actionListener = super.setEventExit();
        item.addActionListener(actionListener);
        return actionListener;
    }


    private void addEventBuy(){
        ActionListener actionListener = new BBuyListener(frameDialog, allTestcases, currentUser);
        frameDialog.buttons.get(0).addActionListener(actionListener);

        ActionListener actionListener2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateData();
            }
        };
        frameDialog.buttons.get(0).addActionListener(actionListener2);
    }


    private void setEventSearch( ){
        ActionListener actionListener = new BSearchListener(frameDialog, TestcaseModel.class);
        frameDialog.buttons.get(1).addActionListener(actionListener);
    }

    private void setEventSort( ){
        BSortListener actionListener = new BSortListener(frameDialog, TestcaseModel.class);
        frameDialog.buttons.get(2).addActionListener(actionListener);
    }


    private void setEventReset( ){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateData();
            }
        };
        frameDialog.buttons.get(3).addActionListener(actionListener);
    }

    private void setEventShow(){
        BShowListener actionListener = new BShowListener(frameDialog);
        frameDialog.buttons.get(4).addActionListener(actionListener);
    }


    protected ActionListener setEventExit(){
        ActionListener actionListener = super.setEventExit();
        frameDialog.buttons.get(5).addActionListener(actionListener);
        return actionListener;
    }

}
