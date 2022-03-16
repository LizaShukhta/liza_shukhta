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

public class BXMLListener implements ActionListener {
    private ArrayList<Model> models;
    private Class<? extends Model> currentClass;
    private CDialog dialog;

    public BXMLListener(ArrayList<Model> models, Class<? extends Model> currentClass, CDialog dialog) {
        this.models = models;
        this.currentClass = currentClass;
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new MyXMLFilter());
        fileChooser.setDialogTitle("XML-экспорт: '" + currentClass.getSimpleName() + "'");
        if (fileChooser.showSaveDialog(dialog) == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            saveDataInFile(models, getCorrectFileName(fileToSave.getAbsolutePath()));
        }
    }

    private String getCorrectFileName(String name) {
        return name.substring(name.length() - 4).equals(".xml") ? name : name + ".xml";
    }

    class MyXMLFilter extends FileFilter implements java.io.FileFilter {
        public boolean accept(File pathname) {
            if (pathname.isDirectory()) {
                return true;
            }
            if (pathname.getName().toLowerCase().endsWith(".xml")) {
                return true;
            }
            return false;
        }

        public String getDescription() {
            return "XML-файлы (*.xml)";
        }
    }

    private void saveDataInFile(ArrayList<Model> models, String file) {
        try (
                FileWriter fout = new FileWriter(file, false);
        ) {
            saveTestcase(models, fout);
            JOptionPane.showMessageDialog(dialog, "Данные экспортированы!", "Готово!", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(dialog, "Error создания экспорта!", "Error!", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private String getSpaces(String line, int length) {
        int size = line.length();
        if (size > length) {
            char[] simbols = new char[length];
            line.getChars(0, length - 2, simbols, 0);
            simbols[length - 2] = '.';
            simbols[length - 1] = '.';
            line = String.valueOf(simbols);

        } else {
            for (int i = 0; i < length - size; i++) {
                line += " ";
            }
        }
        return line;
    }

    private String getLine(int length) {
        String answer = "";
        for (int i = 0; i < length + 2; i++) {
            answer += "=";
        }
        return answer + "\r\n";
    }


    private void saveTestcase(ArrayList<Model> model, FileWriter fout) throws IOException {
        fout.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
        fout.write("<testsystem>\r\n");
        for (int i = 0; i < model.size(); i++) {
            fout.write("\t<testcase>\r\n");
            TestcaseModel testcase = (TestcaseModel) model.get(i);


            fout.write("\t\t<id>" + testcase.getId() + "</id>\r\n");
            fout.write("\t\t<name>" + testcase.getName() + "</name>\r\n");
            fout.write("\t\t<aromatization>" + testcase.getAurimatization() + "</aromatization>\r\n");
            fout.write("\t\t<description>" + testcase.getDescription() + "</description>\r\n");
            fout.write("\t\t<duration>" + testcase.getDuration() + "</duration>\r\n");
            fout.write("\t\t<price>" + testcase.getPrice() + "</price>\r\n");


            fout.write("\t\t<testers>\r\n");
            ArrayList<Model> testers = DataPreparer.getTestersTestcases("", "");
            for (Model tester : testers) {
                if (((TesterTestcaseModel) tester).getTestcase_id().split("\\)")[0].equals("" + testcase.getId())) {
                    fout.write("\t\t\t<tester>" + ((TesterTestcaseModel) tester).getTester_id().split("\\)")[1] + "</tester>\r\n");
                }
            }
            fout.write("\t\t<testers>\r\n");

            fout.write("\t</testcase>\r\n");
        }
        fout.write("</testsystem>\r\n");
    }
}
