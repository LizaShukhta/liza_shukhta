package dialog_construction;

import connection.DataPreparer;
import dialogs.CDialog;
import listeners.*;
import model.*;
import table_models.*;
import validation.Validator;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class OneTableDialog {
    private CDialog frameDialog;
    public CDialog currentDialog;
    private String title;
    private Class<? extends Model> currentClass;
    private ArrayList<Model> models = new ArrayList<>();

    public OneTableDialog(CDialog frameDialog, Class<? extends Model> currentClass, String title){
        this.frameDialog = frameDialog;
        this.currentClass = currentClass;
        this.title = "Таблица: " + title;
    }

    public void create(){
            currentDialog = new CDialog(title, 800, 350);
        Validator.setSize(currentDialog, 40);
        addWindowListener();
        prepareDialog();
        setEventsForButtons();
        addMouseListener();
        currentDialog.setVisible(true);
    }

    private void addMouseListener(){
        MouseAdapter mouseAdapter = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int rowId = currentDialog.tables.get(0).getSelectedRow();
                int id = (int)(currentDialog.tables.get(0).getModel()).getValueAt(rowId, 0);
                currentDialog.textFields.get(0).setText(id + "");

                if (e.getClickCount() == 2) {
                    currentDialog.buttons.get(1).doClick();
                }
            }
        };
        currentDialog.tables.get(0).addMouseListener(mouseAdapter);
    }

    private void addWindowListener(){
        WindowListener windowListener = new WindowListener() {
            public void windowActivated(WindowEvent event) {}
            public void windowClosed(WindowEvent event) {}
            public void windowDeactivated(WindowEvent event) {}
            public void windowDeiconified(WindowEvent event) {}
            public void windowIconified(WindowEvent event) {}
            public void windowOpened(WindowEvent event) {
                frameDialog.setVisible(false);
            }
            public void windowClosing(WindowEvent event) {
                frameDialog.setVisible(true);
            }
        };
        currentDialog.addWindowListener(windowListener);
        currentDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void createMenu(){
        JMenuBar menuBar = new JMenuBar();

        JMenu menuChange = new  JMenu("Одработка");
        JMenuItem create = new JMenuItem("Создать");
        setEventAddRed(create);
        menuChange.add(create);
        JMenuItem delete = new JMenuItem("Удалить");
        setEventDel(delete);
        menuChange.add(delete);
        JMenuItem red = new JMenuItem("Редактировать");
        setEventAddRed(red);
        menuChange.add(red);
        menuBar.add(menuChange);

        if(!currentClass.getName().equals("model.TesterTestcaseModel") && !currentClass.getName().equals("model.UserTesttypeModel")) {
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
        }

        JMenu menuStat = new  JMenu("Аналитика");
        JMenuItem report = new JMenuItem("Отчёт");
        setEventSave(report);
        menuStat.add(report);
        if(!currentClass.getName().equals("model.TestmachineModel") && !currentClass.getName().equals("model.TesterTestcaseModel")
                && !currentClass.getName().equals("model.UserTesttypeModel")) {
            JMenuItem graph = new JMenuItem("Аналитика");
            setEventGetInfo(graph);
            menuStat.add(graph);
        }
        if(currentClass.getName().equals("model.TesterModel")) {
            JMenuItem graph2 = new JMenuItem("Востребованность Тестировщиков");
            setEventGetInfoTester(graph2);
            menuStat.add(graph2);
        }
        if(currentClass.getName().equals("model.TestcaseModel")) {
            JMenuItem graph2 = new JMenuItem("Экпорт в XML");
            setEventExport(graph2);
            menuStat.add(graph2);
        }
        menuBar.add(menuStat);
        currentDialog.setJMenuBar(menuBar);
    }

    private void prepareDialog(){
        if((models = OneTableDialog.getData(currentClass)) == null) {
            JOptionPane.showMessageDialog(currentDialog, "Error получения данных!", "Error!",JOptionPane.PLAIN_MESSAGE);
            currentDialog.dispose();
            return;
        }
        createMenu();
        currentDialog.addTable(750, 200, 25, 20, OneTableDialog.getModel(models, currentClass));

        currentDialog.addButton(150, 30,25, 225, "+");
        currentDialog.addLabel(300, 30,250, 290, "Номер покупаемого товара:");
        currentDialog.addTextField(300, 30,250, 320);
        currentDialog.addButton(300, 30,250, 370, "Редактировать");
        currentDialog.addButton(300, 30,250, 420, "Удалить");
        currentDialog.addButton(300, 30,250, 470, "Отчёт");
        if(!currentClass.getName().equals("model.TesterTestcaseModel") && !currentClass.getName().equals("model.UserTesttypeModel")) {
            currentDialog.addButton(300, 30, 250, 520, "Поиск");
            currentDialog.addButton(300, 30, 250, 570, "Сортировка");
            currentDialog.addButton(300, 30, 250, 620, "Сброс");
        }
        if(!currentClass.getName().equals("model.TestmachineModel") && !currentClass.getName().equals("model.TesterTestcaseModel")
                && !currentClass.getName().equals("model.UserTesttypeModel"))
            currentDialog.addButton(300, 30, 250, 670, "Статистика");
        if(currentClass.getName().equals("model.TesterModel")) {
            currentDialog.addButton(200, 30, 560, 240, "Статистика Тестировщиков").setVisible(false);
        }
    }

    private static ArrayList<Model> getData(Class<? extends Model> currentClass){
        switch (currentClass.getName()) {
            default:
            case "model.TesttypeModel":     return DataPreparer.getTesttypes("", "");
            case "model.TestcaseModel":      return DataPreparer.getTestcases("", "");
            case "model.RequestModel":     return DataPreparer.getRequests("", "");
            case "model.TesterModel":     return DataPreparer.getTesters("", "");
            case "model.UserModel":         return DataPreparer.getUsers("", "");
            case "model.TestmachineModel":    return DataPreparer.getTestmachines("", "");
            case "model.TesterTestcaseModel":     return DataPreparer.getTestersTestcases("", "");
            case "model.UserTesttypeModel":         return DataPreparer.getUserTesttypes("", "");
        }
    }

    private static AbstractTableModel getModel(ArrayList<Model> model, Class<? extends Model> currentClass){
        switch (currentClass.getName()) {
            default:
            case "model.TesttypeModel":     return new TesttypeTableModel(model, true);
            case "model.TestcaseModel":      return new TestcaseTableModel(model, true);
            case "model.RequestModel":     return new RequestTableModel(model, true);
            case "model.TesterModel":     return new TesterTableModel(model, true);
            case "model.UserModel":         return new UserTableModel(model, true);
            case "model.TestmachineModel":    return new TestmachineTableModel(model, true);
            case "model.TesterTestcaseModel":      return new TesterTestcaseTableModel(model, true);
            case "model.UserTesttypeModel":         return new UserTesttypeTableModel(model, true);
        }
    }

    protected void setEventsForButtons(){
        setEventAddRed();
        setEventDel();
        setEventSave();
        if(!currentClass.getName().equals("model.TesterTestcaseModel") && !currentClass.getName().equals("model.UserTesttypeModel")) {
            setEventSearch();
            setEventSort();
            setEventReset();
        }
        if(!currentClass.getName().equals("model.TestmachineModel") && !currentClass.getName().equals("model.TesterTestcaseModel")
            && !currentClass.getName().equals("model.UserTesttypeModel"))
            setEventGetInfo();
        if(currentClass.getName().equals("model.TesterModel"))
            setEventGetInfoTester();
    }

    private void setEventAddRed(JMenuItem item){
        ActionListener actionListener = new BAddRedListener(currentDialog, currentClass, models, this);
        item.addActionListener(actionListener);
    }

    private void setEventDel(JMenuItem item){
        ActionListener actionListener = new BDelListener(currentDialog, currentClass, models, this);
        item.addActionListener(actionListener);
    }

    private void setEventSearch(JMenuItem item){
        ActionListener actionListener = new BSearchListener(currentDialog, currentClass);
        item.addActionListener(actionListener);
    }

    private void setEventSort(JMenuItem item){
        BSortListener actionListener = new BSortListener(currentDialog, currentClass);
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

    private void setEventSave(JMenuItem item){
        BSaveListener actionListener = new BSaveListener(models, currentClass, frameDialog);
        item.addActionListener(actionListener);
    }

    private void setEventExport(JMenuItem item){
        BXMLListener actionListener = new BXMLListener(models, currentClass, frameDialog);
        item.addActionListener(actionListener);
    }

    private void setEventGetInfo(JMenuItem item){
        BGetInfoListener actionListener = new BGetInfoListener(currentClass, this);
        item.addActionListener(actionListener);
    }

    private void setEventGetInfoTester(JMenuItem item){
        BGetInfoTesterListener actionListener = new BGetInfoTesterListener(this);
        item.addActionListener(actionListener);
    }

    private void setEventAddRed(){
        ActionListener actionListener = new BAddRedListener(currentDialog, currentClass, models, this);
        currentDialog.buttons.get(0).addActionListener(actionListener);
        currentDialog.buttons.get(1).addActionListener(actionListener);
    }

    private void setEventDel(){
        ActionListener actionListener = new BDelListener(currentDialog, currentClass, models, this);
        currentDialog.buttons.get(2).addActionListener(actionListener);
    }


    private void setEventSave(){
        BSaveListener actionListener = new BSaveListener(models, currentClass, frameDialog);
        currentDialog.buttons.get(3).addActionListener(actionListener);
    }

    private void setEventSearch(){
        ActionListener actionListener = new BSearchListener(currentDialog, currentClass);
        currentDialog.buttons.get(4).addActionListener(actionListener);
    }

    private void setEventSort(){
        BSortListener actionListener = new BSortListener(currentDialog, currentClass);
        currentDialog.buttons.get(5).addActionListener(actionListener);
    }

    private void setEventReset(){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateData();
            }
        };
        currentDialog.buttons.get(6).addActionListener(actionListener);
    }

    public ArrayList<Model> updateData(){
        if((models = OneTableDialog.getData(currentClass)) == null) {
            JOptionPane.showMessageDialog(currentDialog, "Error получения данных!", "Error!",JOptionPane.PLAIN_MESSAGE);
            currentDialog.dispose();
        }
        currentDialog.tables.get(0).setModel(getModel(models, currentClass));
        return models;
    }

    private void setEventGetInfo(){
        BGetInfoListener actionListener = new BGetInfoListener(currentClass, this);
        currentDialog.buttons.get(7).addActionListener(actionListener);
    }

    private void setEventGetInfoTester(){
        BGetInfoTesterListener actionListener = new BGetInfoTesterListener(this);
        currentDialog.buttons.get(8).addActionListener(actionListener);
    }

}
