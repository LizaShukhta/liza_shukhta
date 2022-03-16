package listeners;

import connection.DataPreparer;
import dialogs.CDialog;
import model.*;
import validation.Validator;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BSaveListener implements ActionListener {
    private ArrayList<Model> models;
    private Class<? extends Model> currentClass;
    private CDialog dialog;

    public BSaveListener(ArrayList<Model> models, Class<? extends Model> currentClass, CDialog dialog){
        System.out.println(2222);
        System.out.println(models.size());
        this.models = models;
        this.currentClass = currentClass;
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new MyTXTFilter());
        fileChooser.setDialogTitle("Отчетность: '" + currentClass.getSimpleName() + "'");
        if (fileChooser.showSaveDialog(dialog) == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            saveDataInFile(models, getCorrectFileName(fileToSave.getAbsolutePath()));
        }
    }

    private String getCorrectFileName(String name){
        return name.substring(name.length()-4).equals(".txt") ? name : name + ".txt";
    }

    class MyTXTFilter extends FileFilter implements java.io.FileFilter {
        public boolean accept(File pathname) {
            if (pathname.isDirectory()) {
                return true;
            }
            if (pathname.getName().toLowerCase().endsWith(".txt")) {
                return true;
            }
            return false;
        }

        public String getDescription() {
            return "Текстовые файлы (*.txt)";
        }
    }

    private void saveDataInFile(ArrayList<Model> models, String file){
        try (
                FileWriter fout = new FileWriter(file, false);
        ){
            switch (currentClass.getName()) {
                default:
                case "model.TesttypeModel":      saveTesttype(models, fout); break;
                case "model.TestcaseModel":       saveTestcase(models, fout); break;
                case "model.RequestModel":      saveRequest(models, fout); break;
                case "model.TesterModel":      saveTester(models, fout); break;
                case "model.UserModel":          saveUser(models, fout); break;
                case "model.TestmachineModel":     saveTestmachine(models, fout); break;
                case "model.TesterTestcaseModel":      saveTesterTestcase(models, fout); break;
                case "model.UserTesttypeModel":         saveTesttypeUser(models, fout); break;
            }
            JOptionPane.showMessageDialog(dialog, "Отчёт создан!", "Всё ок!",JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e){
            JOptionPane.showMessageDialog(dialog, "Error создания отчёта!", "Error!",JOptionPane.PLAIN_MESSAGE);
        }
    }

    private String getSpaces(String line, int length){
        int size = line.length();
        if(size > length){
            char[] simbols = new char[length];
            line.getChars(0, length-2, simbols, 0);
            simbols[length-2] = '.';
            simbols[length-1] = '.';
            line = String.valueOf(simbols);

        } else {
            for (int i = 0; i < length - size; i++) {
                line += " ";
            }
        }
        return line;
    }

    private String getLine(int length){
        String answer = "";
        for (int i = 0; i < length+2; i++) {
            answer += "=";
        }
        return answer + "\r\n";
    }

    private void saveTesttype(ArrayList<Model> model, FileWriter fout) throws IOException {
        String columnHead = "|" + "ИД\t|" + getSpaces("Название", 35) + "|"
                 + getSpaces("К.ИД", 5) + "|\r\n";
        fout.write(getLine(columnHead.length()) + getSpaces("| Таблица: <<<ВидыТестирования>>>", columnHead.length()) + " |\r\n");
        fout.write(getLine(columnHead.length()) + columnHead + getLine(columnHead.length()));
        for (int i = 0; i < model.size(); i++) {
            TesttypeModel testtype = (TesttypeModel)model.get(i);
            fout.write("|" + testtype.getId() + "\t|"
                    + getSpaces(testtype.getName(), 35) + "|"
                    + getSpaces(String.valueOf(testtype.getParent_testtype_id()), 5) + "|\r\n");
        }
        fout.write(getLine(columnHead.length()));
    }

    private void saveTestcase(ArrayList<Model> model, FileWriter fout) throws IOException {
        fout.write("Сценарий выполнения тестирования: \r\n");
        fout.write("Начало Тестирования \r\n");
        for (int i = 0; i < model.size(); i++) {
            TestcaseModel testcase = (TestcaseModel)model.get(i);


            fout.write("=> \r\n");

            fout.write(
                    "Тест " + i +" \"" + testcase.getName()
                            + "\" (Автоматизация: " + testcase.getAurimatization()
                            + ") - Тетировщики:"
            );
            ArrayList<Model> testers = DataPreparer.getTestersTestcases("", "");
            for (Model tester :
                    testers) {

                if (((TesterTestcaseModel) tester).getTestcase_id().split("\\)")[0].equals("" + testcase.getId())) {
                    fout.write(((TesterTestcaseModel) tester).getTester_id());
                    fout.write(", ");
                }
            }

            fout.write(
                    "\r\n"
            );

        }
        fout.write("=> \r\n");
        fout.write("Конеч Тестирования \r\n");
    }

    private void saveRequest(ArrayList<Model> model, FileWriter fout) throws IOException {
        String columnHead = "|" + "ИД\t|" + getSpaces("Дата", 15) + "|"
                + getSpaces("Адрес", 25)  + "|" + getSpaces("Почта", 25) + "|"
                + getSpaces("Кл-во", 10) + "|" + getSpaces("П.ИД", 5) + getSpaces("Ю.ИД", 5) + "|\r\n";
        fout.write(getLine(columnHead.length()) + getSpaces("| Таблица: <<<ЗАЯВКИ>>>", columnHead.length()) + " |\r\n");
        fout.write(getLine(columnHead.length()) + columnHead + getLine(columnHead.length()));
        for (int i = 0; i < model.size(); i++) {
            RequestModel request = (RequestModel)model.get(i);
            fout.write("|" + request.getId() + "\t|" + getSpaces(request.getDate(), 15) + "|"
                    + getSpaces(request.getPhone(), 25)  + "|" + getSpaces(request.getMail(), 25) + "|"
                    + getSpaces(String.valueOf(request.getImportance()), 10) + "|"
                    + getSpaces(String.valueOf(request.getTestcase_id()), 5) + "|"
                    + getSpaces(String.valueOf(request.getUser_id()), 5) + "|\r\n");
        }
        fout.write(getLine(columnHead.length()));
    }

    private void saveTester(ArrayList<Model> model, FileWriter fout) throws IOException {
        String columnHead = "|" + "ИД\t|" + getSpaces("Название", 25) + "|"
                + getSpaces("Страна",25) + "|" + getSpaces("Сотрудничество с", 25) + "|\r\n";
        fout.write(getLine(columnHead.length()) + getSpaces("| Таблица: <<<Тестировщики>>>", columnHead.length()) + " |\r\n");
        fout.write(getLine(columnHead.length()) + columnHead + getLine(columnHead.length()));
        for (int i = 0; i < model.size(); i++) {
            TesterModel tester = (TesterModel)model.get(i);
            fout.write("|" + tester.getId() + "\t|" + getSpaces(tester.getName(), 25) + "|"
                    + getSpaces(tester.getSpeciality(), 25) + "|" + getSpaces(tester.getDate_of_birth(), 25)
                    + "|\r\n");
        }
        fout.write(getLine(columnHead.length()));
    }

    private void saveUser(ArrayList<Model> model, FileWriter fout) throws IOException {
        String columnHead = "|" + "ИД\t|" + getSpaces("Логин", 25) + "|"
                + getSpaces("Паспорт", 25) + "|" + getSpaces("Роль", 25) + "|\r\n";
        fout.write(getLine(columnHead.length()) + getSpaces("| Таблица: <<<ПОЛЬЗОВТЕЛИ>>>", columnHead.length()) + " |\r\n");
        fout.write(getLine(columnHead.length()) + columnHead + getLine(columnHead.length()));
        for (int i = 0; i < model.size(); i++) {
            UserModel user = (UserModel)model.get(i);
            fout.write("|" + user.getId() + "\t|" + getSpaces(user.getLogin(), 25) + "|"
                    + getSpaces(user.getPassword(), 25) + "|" + user.getRole() + "   |\r\n");
        }
        fout.write(getLine(columnHead.length()));
    }

    private void saveTestmachine(ArrayList<Model> model, FileWriter fout) throws IOException {
        String columnHead = "|" + "ИД\t|" + getSpaces("Название", 25) + "|"
                + getSpaces("Адрес", 25) + "|\r\n";
        fout.write(getLine(columnHead.length()) + getSpaces("| Таблица: <<<Устройства>>>", columnHead.length()) + " |\r\n");
        fout.write(getLine(columnHead.length()) + columnHead + getLine(columnHead.length()));
        for (int i = 0; i < model.size(); i++) {
            TestmachineModel testmachine = (TestmachineModel)model.get(i);
            fout.write("|" + testmachine.getId() + "\t|" + getSpaces(testmachine.getName(), 25) + "|"
                    + getSpaces(testmachine.getIpipaddress(), 25) + "|\r\n");
        }
        fout.write(getLine(columnHead.length()));
    }

    private void saveTesterTestcase(ArrayList<Model> model, FileWriter fout) throws IOException {
        String columnHead = "|" + "ИД\t|" + getSpaces("ИД Поставщика", 15) + "|"
                + getSpaces("Поставщик", 25) + "|"+ getSpaces("ИД Продукт", 15) + "|"
                + getSpaces("Продукт", 25) + "|\r\n";
        fout.write(getLine(columnHead.length()) + getSpaces("| Таблица: <<<Тестировщик-Тест>>>", columnHead.length()) + " |\r\n");
        fout.write(getLine(columnHead.length()) + columnHead + getLine(columnHead.length()));
        for (int i = 0; i < model.size(); i++) {
            TesterTestcaseModel testerTestcase = (TesterTestcaseModel)model.get(i);
            fout.write("|" + testerTestcase.getId() + "\t|" + getSpaces(Validator.getIdFromKey(testerTestcase.getTester_id())+"", 15)
                    + "|" + getSpaces(testerTestcase.getTester_id(), 25) + "|" + getSpaces(Validator.getIdFromKey(testerTestcase.getTestcase_id())+"", 15)
                    + "|" + getSpaces(testerTestcase.getTestcase_id(), 25) + "|\r\n");
        }
        fout.write(getLine(columnHead.length()));
    }

    private void saveTesttypeUser(ArrayList<Model> model, FileWriter fout) throws IOException {
        String columnHead = "|" + "ИД\t|" + getSpaces("ИД Вида", 15) + "|"
                + getSpaces("Вид", 25) + "|"+ getSpaces("ИД Пользоват", 15) + "|"
                + getSpaces("Пользователь", 25) + "|\r\n";
        fout.write(getLine(columnHead.length()) + getSpaces("| Таблица: <<<ВидыТестирования-Пользователь>>>", columnHead.length()) + " |\r\n");
        fout.write(getLine(columnHead.length()) + columnHead + getLine(columnHead.length()));
        for (int i = 0; i < model.size(); i++) {
            UserTesttypeModel userTesttype = (UserTesttypeModel)model.get(i);
            fout.write("|" + userTesttype.getId() + "\t|" + getSpaces(Validator.getIdFromKey(userTesttype.getTesttype_id())+"", 15)
                    + "|" + getSpaces(userTesttype.getTesttype_id(), 25) + "|" + getSpaces(Validator.getIdFromKey(userTesttype.getUser_id())+"", 15)
                    + "|" + getSpaces(userTesttype.getUser_id(), 25) + "|\r\n");
        }
        fout.write(getLine(columnHead.length()));
    }
}
